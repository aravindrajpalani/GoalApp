/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.social.goalapp.data.network;

import com.social.goalapp.data.network.model.BlogResponse;
import com.social.goalapp.data.network.model.LoginRequest;
import com.social.goalapp.data.network.model.LoginResponse;
import com.social.goalapp.data.network.model.Profile;
import com.social.goalapp.data.network.model.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class AppApiHelper implements ApiHelper {

    public static AppApiHelper INSTANCE;

    public static AppApiHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppApiHelper();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }


    @Override
    public Call<LoginResponse> registerUsers(LoginRequest params) {


        return APIClient.getClient().create(APIInterface.class).registerUsers(params);
    }

    @Override
    public Call<BlogResponse> getAllGoals(String token) {
        return APIClient.getClient().create(APIInterface.class).getAllGoals(token);
    }

    @Override
    public Call<Result> likeGoal(String token, int id) {
        return APIClient.getClient().create(APIInterface.class).likeGoal(token,id);
    }

    @Override
    public Call<BlogResponse> getMyGoal(String token) {
        return APIClient.getClient().create(APIInterface.class).getMyGoal(token);
    }

    @Override
    public Call<Result> addComment(String token, int id, String comment) {
        return APIClient.getClient().create(APIInterface.class).addComment(token,id,comment);
    }

    @Override
    public Call<ResponseBody> deleteGoal(String token, int id) {
        return APIClient.getClient().create(APIInterface.class).deleteGoal(token,id);
    }

    @Override
    public Call<Result> addGoal(String token, String content, int goaldays) {
        return APIClient.getClient().create(APIInterface.class).addGoal(token,content,goaldays);
    }

    @Override
    public Call<Profile> getProfile(String token, int id) {
        return APIClient.getClient().create(APIInterface.class).getProfile(token,id);
    }

    @Override
    public Call<ResponseBody> patchGoalStatus(String token, int id, String status) {
        return APIClient.getClient().create(APIInterface.class).updateGoalStatus(token,id,status);
    }
}

