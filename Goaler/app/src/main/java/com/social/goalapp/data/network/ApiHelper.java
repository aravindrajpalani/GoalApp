package com.social.goalapp.data.network;



import com.social.goalapp.data.network.model.BlogResponse;
import com.social.goalapp.data.network.model.LoginRequest;
import com.social.goalapp.data.network.model.LoginResponse;
import com.social.goalapp.data.network.model.Profile;
import com.social.goalapp.data.network.model.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Aravindraj on 11/3/2017.
 */

public interface ApiHelper {
    Call<LoginResponse> registerUsers(LoginRequest params);

    Call<BlogResponse> getAllGoals(String token);

    Call<Result> likeGoal(String token, int id);

    Call<BlogResponse> getMyGoal(String token);

    Call<Result> addComment(String token, int id, String comment);

    Call<ResponseBody> deleteGoal(String token,int id);

    Call<Result> addGoal(String token,String content,int goaldays);

    Call<Profile> getProfile(String token, int id);

    Call<ResponseBody> patchGoalStatus(String token,int id,String status);
}
