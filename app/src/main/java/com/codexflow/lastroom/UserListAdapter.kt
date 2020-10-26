/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codexflow.lastroom

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codexflow.lastroom.databinding.RecyclerviewItemBinding


class UserListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {


    private var userlist = emptyList<User>() // Cached copy of words

    class ViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.userv, data)
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecyclerviewItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recyclerview_item, parent, false)
        return ViewHolder(binding)
    }

    internal fun setWords(users: List<User>) {
        this.userlist = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = userlist.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userlist.get(position))
    }



}


