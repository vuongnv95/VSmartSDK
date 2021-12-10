package com.example.setting.ui.list_home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.core.utils.toastMessage
import com.example.setting.HomeNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentListHomeBinding
import com.example.utils.Defines
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListHomeFragment : BaseFragment<FragmentListHomeBinding, ListHomeViewModel>() {

    @Inject
    lateinit var appNavigation: HomeNavigation

    override val layoutId = R.layout.fragment_list_home

    private val viewModel: ListHomeViewModel by viewModels()
    override fun getVM() = viewModel


    val organizationAdapter: OrganizationAdapter by lazy {
        OrganizationAdapter({
            if (!isDoubleClick) {
                val bundle = Bundle()
                bundle.putSerializable(Defines.BUNDLE_ITEM_HOME, it)
                appNavigation.openListHomeToListRoomFragment(bundle)
            }
        })
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.deviceRv.adapter = organizationAdapter
        viewModel.loadDataDevice()
    }

    override fun setOnClick() {
        super.setOnClick()
    }

    override fun bindingAction() {
        super.bindingAction()
        viewModel.listHomeResponse.observe(this) {
            organizationAdapter.homes = it
        }

        viewModel.errorResponse.observe(this) {
            toastMessage(it.exception ?: "")
        }
    }
}