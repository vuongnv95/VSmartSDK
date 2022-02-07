package com.example.setting.ui.add_device.ezMode

import android.util.Log
import android.widget.Toast
import com.espressif.iot.esptouch.EsptouchTask
import com.espressif.iot.esptouch.IEsptouchListener
import com.espressif.iot.esptouch.IEsptouchResult
import com.espressif.iot.esptouch.IEsptouchTask

class EsptouchAsyncTask(private val deviceConfigEZFragment: DeviceConfigEZFragment):
    CoroutineAsyncTask<ByteArray?, IEsptouchResult?, List<IEsptouchResult>?>() {
    companion object {
        const val TAG = "EsptouchAsyncTask"
    }
    private val mLock = Any()
    private var mEsptouchTask: IEsptouchTask? = null

    private fun encryptPassword(password: ByteArray) {
        val intDate = IntArray(password.size)
        val seven: Byte = 7
        val two: Byte = 2
        for (i in password.indices) {
            if (i % 2 == 0) {
                intDate[i] = password.get(i) + seven
            } else {
                intDate[i] = password.get(i) + two
            }
            password[i]= (intDate[i] and 0xff).toByte()
        }
    }

    fun cancelEsptouch() {
        cancel(true)
        if (deviceConfigEZFragment != null) {
            //pairFragment.showHideLoading(false)
        }

        if (mEsptouchTask != null) {
            mEsptouchTask!!.interrupt()
        }
    }

    override fun onPreExecute() {
        //pairFragment.showHideLoading(true)
    }

    override fun onProgressUpdate(vararg values: IEsptouchResult?) {
        super.onProgressUpdate(*values)
        if (deviceConfigEZFragment !=null) {
            val result = values[0]!!
            val text = result.bssid + " is connected to the wifi"
            Toast.makeText(deviceConfigEZFragment.requireContext(), text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun doInBackground(vararg params: ByteArray?): List<IEsptouchResult>? {
        var taskResultCount: Int
        synchronized(mLock) {
            val apSsid = params[0]!!
            val apBssid = params[1]!!
            val apPassword = params[2]!!
            encryptPassword(apPassword)
            taskResultCount = 1
            mEsptouchTask = EsptouchTask(apSsid, apBssid, apPassword, deviceConfigEZFragment.requireContext())
            mEsptouchTask?.setPackageBroadcast(false)
            mEsptouchTask?.setEsptouchListener(IEsptouchListener { values: IEsptouchResult? ->
                publishProgress(
                    values
                )
            })
        }
        return mEsptouchTask!!.executeForResults(taskResultCount)
    }

    override fun onPostExecute(result: List<IEsptouchResult>?) {
        if (result == null) {
            Log.d(TAG, "Create EspTouch task failed, the EspTouch port could be used by other thread")
            return
        }

        val firstResult = result[0]
        if (firstResult.isCancelled) {
            return
        }

        if (!firstResult.isSuc) {
            Log.d(TAG, "Pair Error")
            deviceConfigEZFragment.openErrorScreen("Không tìm thấy thiết bị")
            return
        }
        deviceConfigEZFragment.initAddDevice(result[0].inetAddress.hostAddress)
    }
}