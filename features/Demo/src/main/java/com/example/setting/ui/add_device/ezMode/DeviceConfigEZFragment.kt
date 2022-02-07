package com.example.setting.ui.add_device.ezMode

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat
import androidx.fragment.app.viewModels
import com.espressif.iot.esptouch.util.ByteUtil
import com.espressif.iot.esptouch.util.TouchNetUtil
import com.example.core.base.BaseFragment
import com.example.core.utils.toastMessage
import com.example.setting.DeviceNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentDeviceConfigEzBinding
import com.example.setting.ui.add_device.apMode.ConfigDeviceAPViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.net.InetAddress
import javax.inject.Inject

@AndroidEntryPoint
class DeviceConfigEZFragment :
    BaseFragment<FragmentDeviceConfigEzBinding, DeviceConfigEZViewModel>() {

    @Inject
    lateinit var appNavigation: DeviceNavigation
    override val layoutId = R.layout.fragment_device_config_ez

    private val viewModel: DeviceConfigEZViewModel by viewModels()
    override fun getVM() = viewModel
    private var ssid: String? = null
    private var password: String? = null
    private var task: EsptouchAsyncTask? = null

    private var wifiManager: WifiManager? = null
    private var ssidBytes: ByteArray? = null
    private var bssid: String? = null
    private val REQUEST_PERMISSION = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(requireActivity(), permissions, REQUEST_PERMISSION)
        }
        wifiManager = requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.isNotEmpty()) {
                executeEspTouch()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.btnDone.setOnClickListener {
            if (!isDoubleClick) {
                if (binding.etSsid.text.isNullOrEmpty()) {
                    toastMessage("Nhập ssid")
                    return@setOnClickListener
                }
                if (binding.etPassword.text.isNullOrEmpty()) {
                    toastMessage("Nhập password")
                    return@setOnClickListener
                }
            }
        }
    }

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.responseSuccess.observe(viewLifecycleOwner) {
            toastMessage("Tạo device thành công")
            appNavigation.navigateUp()
        }

        viewModel.responseError.observe(viewLifecycleOwner) {
            toastMessage(it.exception ?: "")
        }
    }

    fun openErrorScreen(error: String) {
        toastMessage(error ?: "")
    }

    fun initAddDevice(host: String) {
        context?.let { viewModel.addDevice(it, host) }
    }

    private fun executeEspTouch() {
        val stateResult: StateResult? = check()
        ssid = stateResult?.ssid
        ssidBytes = stateResult?.ssidBytes
        bssid = stateResult?.bssid
        val ssid = ByteUtil.getBytesByString(binding.etSsid.text.toString())
        val password: ByteArray? =
            ByteUtil.getBytesByString(binding.etPassword.text.toString())
        val bssid = TouchNetUtil.parseBssid2bytes(bssid)

        task?.let { task -> task.cancelEsptouch() }
        task = EsptouchAsyncTask(this)
        task?.let { task ->
            task.execute(ssid, bssid, password)
        }
    }

    private fun check(): StateResult? {
        var result = checkPermission()
        if (!result!!.permissionGranted) {
            return result
        }
        result = checkLocation()
        result!!.permissionGranted = true
        if (result.locationRequirement) {
            return result
        }
        result = checkWifi()
        result!!.permissionGranted = true
        result.locationRequirement = false
        return result
    }

    private fun checkPermission(): StateResult? {
        val result = StateResult()
        result.permissionGranted = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val locationGranted = (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
            if (!locationGranted) {
                val splits =
                    getString(R.string.message_permission).split("\n").toTypedArray()
                require(splits.size == 2) { "Invalid String @RES esptouch_message_permission" }
                val ssb = SpannableStringBuilder(splits[0])
                ssb.append('\n')
                val clickMsg = SpannableString(splits[1])
                val clickSpan = ForegroundColorSpan(-0xffdd01)
                clickMsg.setSpan(clickSpan, 0, clickMsg.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                ssb.append(clickMsg)
                result.message = ssb
                return result
            }
        }
        result.permissionGranted = true
        return result
    }

    private fun checkLocation(): StateResult? {
        val result = StateResult()
        result.locationRequirement = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val manager: LocationManager? =
                ContextCompat.getSystemService<LocationManager>(
                    requireContext(),
                    LocationManager::class.java
                )
            val enable = manager != null && LocationManagerCompat.isLocationEnabled(manager)
            if (!enable) {
                result.message = getString(R.string.message_location)
                return result
            }
        }
        result.locationRequirement = false
        return result
    }

    private fun checkWifi(): StateResult? {
        val result = StateResult()
        result.wifiConnected = false
        val wifiInfo = wifiManager!!.connectionInfo
        val connected: Boolean =
            com.espressif.iot.esptouch2.provision.TouchNetUtil.isWifiConnected(wifiManager)
        if (!connected) {
            result.message = getString(R.string.message_wifi_connection)
            return result
        }
        val ssid: String =
            com.espressif.iot.esptouch2.provision.TouchNetUtil.getSsidString(wifiInfo)
        val ipValue = wifiInfo.ipAddress
        if (ipValue != 0) {
            result.address =
                com.espressif.iot.esptouch2.provision.TouchNetUtil.getAddress(wifiInfo.ipAddress)
        } else {
            result.address = com.espressif.iot.esptouch2.provision.TouchNetUtil.getIPv4Address()
            if (result.address == null) {
                result.address = com.espressif.iot.esptouch2.provision.TouchNetUtil.getIPv6Address()
            }
        }
        result.wifiConnected = true
        result.message = ""
        result.is5G = com.espressif.iot.esptouch2.provision.TouchNetUtil.is5G(wifiInfo.frequency)
        if (result.is5G) {
            result.message = getString(R.string.message_wifi_frequency)
        }
        result.ssid = ssid
        result.ssidBytes = com.espressif.iot.esptouch2.provision.TouchNetUtil.getRawSsidBytesOrElse(
            wifiInfo,
            ssid.toByteArray()
        )
        result.bssid = wifiInfo.bssid
        return result
    }
}

class StateResult {
    var message: CharSequence? = null
    var permissionGranted = false
    var locationRequirement = false
    var wifiConnected = false
    var is5G = false
    var address: InetAddress? = null
    var ssid: String? = null
    var ssidBytes: ByteArray? = null
    var bssid: String? = null
}