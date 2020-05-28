package com.techm.optusdemo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding.view.RxView
import com.techm.optusdemo.BR
import com.techm.optusdemo.R
import com.techm.optusdemo.databinding.RowUserInfoBinding
import com.techm.optusdemo.network.ItemClickListener
import com.techm.optusdemo.model.userinfo.UserInfo
import com.techm.optusdemo.ui.UserInfoActivity

/** Class UserInfo Adapter to populate items */
class UserInfoAdapter(
    private var context: UserInfoActivity,
    private var mUserInfoList: ArrayList<UserInfo>,
    private val itemClick: ItemClickListener
) : RecyclerView.Adapter<UserInfoAdapter.UserViewHolder>() {

    companion object {
        var mItemClickListener: ItemClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<RowUserInfoBinding>(
            LayoutInflater.from(context),
            R.layout.row_user_info,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    /** Bind Data to ViewHolder */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        mItemClickListener = itemClick
        holder.bind(mUserInfoList[position])

        RxView.clicks(holder.binding.root).subscribe {
            //the whole view subscribed
            mItemClickListener!!.onItemClick(position, mUserInfoList[position].id.toString())
        }
    }

    /** Return item counts of Users. */
    override fun getItemCount(): Int {
        return mUserInfoList.size
    }

    /** This Class used to describe an item view & metadata about its place within the recyclerview */
    class UserViewHolder(val binding: RowUserInfoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: UserInfo) {
            binding.setVariable(
                BR.data,
                data
            )
            binding.data = data
            binding.executePendingBindings()
        }
    }
}