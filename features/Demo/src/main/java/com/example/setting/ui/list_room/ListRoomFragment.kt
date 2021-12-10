package com.example.setting.ui.list_room

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.core.base.BaseFragment
import com.example.core.utils.toastMessage
import com.example.setting.DeviceNavigation
import com.example.setting.HomeNavigation
import com.example.setting.R
import com.example.setting.databinding.FragmentListRoomBinding
import com.example.setting.ui.list_home.OrganizationAdapter
import com.example.utils.Defines
import com.vht_iot.vsmartsdk.network.data.VOrganizationResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListRoomFragment : BaseFragment<FragmentListRoomBinding, ListHomeViewModel>() {

    private var vOrganizationResponse: VOrganizationResponse? = null

    @Inject
    lateinit var appNavigation: HomeNavigation

    override val layoutId = R.layout.fragment_list_room

    private val viewModel: ListHomeViewModel by viewModels()
    override fun getVM() = viewModel


    val roomAdapter: OrganizationAdapter by lazy {
        OrganizationAdapter({
            if (!isDoubleClick) {
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.deviceRv.adapter = roomAdapter
        arguments?.let {
            vOrganizationResponse =  it.getSerializable(Defines.BUNDLE_ITEM_HOME) as VOrganizationResponse
            viewModel.loadDataRoom(vOrganizationResponse?.id?:"")
        }
    }

    override fun setOnClick() {
        super.setOnClick()

        binding.createRoomIv.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(Defines.BUNDLE_ITEM_HOME,vOrganizationResponse)
            appNavigation.openListRoomToCreateRoomFragment(bundle)
        }
    }

    override fun bindingAction() {
        super.bindingAction()
        viewModel.listHomeResponse.observe(this) {
            roomAdapter.homes = it
        }

        viewModel.errorResponse.observe(this) {
            toastMessage(it.exception ?: "")
        }
    }
}