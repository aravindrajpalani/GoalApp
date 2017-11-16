package com.social.goalapp.ui.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.social.goalapp.data.AppDataManager;
import com.social.goalapp.data.network.model.LoginRequest;
import com.social.goalapp.data.network.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Aravindraj on 11/3/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final AppDataManager mDataManager;

    private final LoginContract.View mTasksView;


    public LoginPresenter(@NonNull AppDataManager dataManager, @NonNull LoginContract.View tasksView) {
        mDataManager = dataManager;
        mTasksView = tasksView;
        mTasksView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void callRegisterUserAPI(String accesstoken) {

        Log.e("jfjfjf", "" + accesstoken);

        LoginRequest a=new LoginRequest(accesstoken);
        mDataManager.registerUsers(a).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                mDataManager.setAccessToken("Token "+response.body().getToken());

                mDataManager.setIsLogin(true);
                mDataManager.setCurrentUserId(response.body().getUser().getId().longValue());
                mDataManager.setCurrentFBId(Long.parseLong(response.body().getUser().getFbid()));
                mTasksView.openMainActivity();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("jfjfjf" + call.request().body(), "" + t.getMessage());
            }
        });

    }

    @Override
    public void isLogin() {
        if((mDataManager.isLogin()))
        {
            mTasksView.openMainActivity();
        }
    }
}
