/*
 * Copyright 2016, The Android Open Source Project
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

package com.social.goalapp.ui.home;

import com.social.goalapp.data.network.model.Result;
import com.social.goalapp.ui.base.BasePresenter;
import com.social.goalapp.ui.base.BaseView;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface HomeContract {

    interface View extends BaseView<Presenter> {

        void addGoalData(List<Result> list);

       void notifyAdapter(int itemPos,Result result);



    }

    interface Presenter extends BasePresenter {

        void callAllGoalsAPI();

        void callLikeAPI(int blogid,int pos);


    }
}
