package com.example.setting.ui.list_device

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.setting.databinding.ItemDeviceBinding
import com.vht_iot.vsmartsdk.network.data.Device

class DeviceAdapter(
    private val listener: (Device) -> Unit
) : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    var devices = listOf<Device>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class DeviceViewHolder(
        val binding: ItemDeviceBinding,
        private val listener: (Device) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(device: Device) {
            binding.nameTv.setText(device.name)
            binding.productNameTv.setText(device.groupName)
            binding.root.setOnClickListener {
                listener.invoke(device)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDeviceBinding.inflate(inflater, parent, false)
        return DeviceViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holderControl: DeviceViewHolder, position: Int) {
        holderControl.bindData(device = devices.get(position))
    }

    override fun getItemCount(): Int {
        return devices.size
    }
}