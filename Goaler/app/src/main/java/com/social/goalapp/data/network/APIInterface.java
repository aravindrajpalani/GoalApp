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
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;



public interface APIInterface {

    @POST("users/")
    Call<LoginResponse> registerUsers(@Body LoginRequest accesstoken);

    @GET("allgoals/")
    Call<BlogResponse> getAllGoals(@Header("Authorization") String token);

    @GET("liked/{blogid}/")
    Call<Result> likeGoal(@Header("Authorization") String token, @Path("blogid") int id);

    @GET("mygoals/")
    Call<BlogResponse> getMyGoal(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("comment/")
    Call<Result> addComment(@Header("Authorization") String token, @Field("blogid") int id, @Field("comment") String comment);

    @DELETE("blogposts/{blogid}/")
    Call<ResponseBody> deleteGoal(@Header("Authorization") String token, @Path("blogid") int id);

    @FormUrlEncoded
    @POST("blogposts/")
    Call<Result> addGoal(@Header("Authorization") String token, @Field("content") String content, @Field("goaldays") int days);

    @GET("profile/{id}/")
    Call<Profile> getProfile(@Header("Authorization") String token, @Path("id") int id);

    @FormUrlEncoded
    @PATCH("blogposts/{blogid}/")
    Call<ResponseBody> updateGoalStatus(@Header("Authorization") String token, @Path("blogid") int id, @Field("status") String status);
}
