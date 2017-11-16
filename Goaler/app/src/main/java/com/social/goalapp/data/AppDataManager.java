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

package com.social.goalapp.data;


import android.content.Context;

import com.social.goalapp.data.network.ApiHelper;
import com.social.goalapp.data.network.AppApiHelper;
import com.social.goalapp.data.network.model.BlogResponse;
import com.social.goalapp.data.network.model.LoginRequest;
import com.social.goalapp.data.network.model.LoginResponse;
import com.social.goalapp.data.network.model.Profile;
import com.social.goalapp.data.network.model.Result;
import com.social.goalapp.data.prefs.AppPreferencesHelper;
import com.social.goalapp.data.prefs.PreferencesHelper;

import okhttp3.ResponseBody;
import retrofit2.Call;


public class AppDataManager implements PreferencesHelper, ApiHelper {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private AppApiHelper mAppApiHelper;
    private AppPreferencesHelper mAppPreferencesHelper;

    public static AppDataManager mAppDataManager;

    public static AppDataManager getInstance(Context context, AppApiHelper appApiHelper, AppPreferencesHelper appPreferenceHelper) {
        if (mAppDataManager == null) {
            mAppDataManager = new AppDataManager(context, appApiHelper, appPreferenceHelper);
            return mAppDataManager;
        } else {
            return mAppDataManager;
        }
    }

    public AppDataManager(Context context, AppApiHelper appApiHelper, AppPreferencesHelper appPreferenceHelper) {
        mContext = context;
        mAppApiHelper = appApiHelper;
        mAppPreferencesHelper = appPreferenceHelper;

    }




    @Override
    public Long getCurrentUserId() {
        return mAppPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mAppPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public Long getCurrentFBId() {
        return mAppPreferencesHelper.getCurrentFBId();
    }

    @Override
    public void setCurrentFBId(Long fbId) {
        mAppPreferencesHelper.setCurrentFBId(fbId);
    }

    @Override
    public String getCurrentUserName() {
        return null;
    }

    @Override
    public void setCurrentUserName(String userName) {

    }

    @Override
    public String getCurrentUserEmail() {
        return null;
    }

    @Override
    public void setCurrentUserEmail(String email) {

    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return null;
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {

    }

    @Override
    public String getAccessToken() {
        return mAppPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mAppPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public void setIsLogin(boolean isLogin) {
        mAppPreferencesHelper.setIsLogin(isLogin);
    }

    @Override
    public boolean isLogin() {
        return mAppPreferencesHelper.isLogin();
    }

    @Override
    public void setWelcomeShown(boolean welcomeShown) {
        mAppPreferencesHelper.setWelcomeShown(welcomeShown);
    }

    @Override
    public boolean isWelcomeShown() {
        return mAppPreferencesHelper.isWelcomeShown();
    }




    @Override
    public Call<LoginResponse> registerUsers(LoginRequest accesstoken) {
        return AppApiHelper.getInstance().registerUsers(accesstoken);
    }

    @Override
    public Call<BlogResponse> getAllGoals(String token) {
        return AppApiHelper.getInstance().getAllGoals(token);
    }

    @Override
    public Call<Result> likeGoal(String token, int id) {
        return AppApiHelper.getInstance().likeGoal( token,id);
    }

    @Override
    public Call<BlogResponse> getMyGoal(String token) {
        return AppApiHelper.getInstance().getMyGoal(token);
    }

    @Override
    public Call<Result> addComment(String token, int id, String comment) {
        return AppApiHelper.getInstance().addComment( token,id,comment);
    }

    @Override
    public Call<ResponseBody> deleteGoal(String token, int id) {
        return AppApiHelper.getInstance().deleteGoal( token,id);
    }

    @Override
    public Call<Result> addGoal(String token, String content, int goaldays) {
        return AppApiHelper.getInstance().addGoal( token,content,goaldays);
    }

    @Override
    public Call<Profile> getProfile(String token, int id) {
        return AppApiHelper.getInstance().getProfile( token,id);
    }

    @Override
    public Call<ResponseBody> patchGoalStatus(String token, int id, String status) {
        return AppApiHelper.getInstance().patchGoalStatus( token,id,status);
    }


}
