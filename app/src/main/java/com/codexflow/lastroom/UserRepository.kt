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

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.codexflow.lastroom.User
import com.codexflow.lastroom.UserDao


class UserRepository(private val userDao: UserDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val alluserssaved: LiveData<List<User>> = userDao.getAllUsers()

    val name : LiveData<List<String>> = userDao.getUsersName()
    val surname : LiveData<List<String>> = userDao.getUsersSurname()
    val age : LiveData<List<Int>> = userDao.getUsersAge()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}
