/*
 * Copyright 2021 Pavlo Stavytskyi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.morfly.airin.sample.imagelist.impl.di

import dagger.Binds
import dagger.Module
import org.morfly.airin.sample.imagelist.ImageListEntry
import org.morfly.airin.sample.imagelist.impl.ImageListEntryImpl


@Module
interface ImageListEntryModule {

    @Binds
    fun imageListEntry(impl: ImageListEntryImpl): ImageListEntry
}