package com.example.setting.ui.list_func

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.setting.databinding.ItemFuncBinding
import com.example.setting.databinding.ItemTittleBinding


class ListFuncAdapter(
    private val listener: (FuncData) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val TITTLE = 1

    var funcDatas = listOf<FuncData>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class FuncDataViewHolder(
        val binding: ItemFuncBinding,
        private val listener: (FuncData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(funcData: FuncData) {
            binding.subTitleTv.text = funcData.title
            binding.root.setOnClickListener {
                listener.invoke(funcData)
            }
        }
    }

    class FuncTitlteViewHolder(
        val binding: ItemTittleBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(funcData: FuncData) {
            binding.titleTV.setText(funcData.title)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == TITTLE) {
            val binding = ItemFuncBinding.inflate(inflater, parent, false)
            return FuncDataViewHolder(binding, listener)
        } else {
            val binding = ItemTittleBinding.inflate(inflater, parent, false)
            return FuncTitlteViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holderControl: RecyclerView.ViewHolder, position: Int) {
        if (holderControl is FuncTitlteViewHolder) {
            holderControl.bindData(funcDatas.get(position))
        } else if (holderControl is FuncDataViewHolder) {
            holderControl.bindData(funcDatas.get(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return funcDatas.get(position).type
    }

    override fun getItemCount(): Int {
        return funcDatas.size
    }
}