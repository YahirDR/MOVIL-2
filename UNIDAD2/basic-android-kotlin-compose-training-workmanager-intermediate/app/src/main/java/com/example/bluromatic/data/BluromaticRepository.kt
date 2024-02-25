/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bluromatic.data

import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

interface BluromaticRepository {
    val outputWorkInfo: Flow<WorkInfo?>
    fun applyBlur(blurLevel: Int)
    fun cancelWork()
}
override val outputWorkInfo: Flow<WorkInfo?> =
    workManager.getWorkInfosByTagLiveData(TAG_OUTPUT).asFlow().mapNotNull {
        if (it.isNotEmpty()) it.first() else null
    }
override val outputWorkInfo: Flow<WorkInfo> = interface BluromaticRepository {
    //    val outputWorkInfo: Flow<WorkInfo?>
    val outputWorkInfo: Flow<WorkInfo>

