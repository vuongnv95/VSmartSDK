package com.example.setting.ui.list_home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.setting.databinding.ItemHomeBinding
import com.vht_iot.vsmartsdk.network.data.VOrganizationResponse

class OrganizationAdapter(
    private val listener: (VOrganizationResponse) -> Unit
) : RecyclerView.Adapter<OrganizationAdapter.HomeViewHolder>() {

    var homes = listOf<VOrganizationResponse>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class HomeViewHolder(
        val binding: ItemHomeBinding,
        private val listener: (VOrganizationResponse) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(home: VOrganizationResponse) {
            binding.nameTv.setText(home.name)
            binding.root.setOnClickListener {
                listener.invoke(home)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holderControl: HomeViewHolder, position: Int) {
        holderControl.bindData(home = homes.get(position))
    }

    override fun getItemCount(): Int {
        return homes.size
    }
}