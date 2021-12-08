package com.example.setting.ui.setting

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.setting.databinding.ItemSettingBinding

class SettingAdapter(
    private val listener: (SettingData) -> Unit
) : RecyclerView.Adapter<SettingAdapter.SettingViewHolder>() {

    var settingDatas = listOf<SettingData>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class SettingViewHolder(
        val binding: ItemSettingBinding,
        private val listener: (SettingData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(settingData: SettingData) {
            binding.nameTv.setText(settingData.name)
            binding.root.setOnClickListener {
                listener.invoke(settingData)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSettingBinding.inflate(inflater, parent, false)
        return SettingViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holderControl: SettingViewHolder, position: Int) {
        holderControl.bindData(settingData = settingDatas.get(position))
    }

    override fun getItemCount(): Int {
        return settingDatas.size
    }
}