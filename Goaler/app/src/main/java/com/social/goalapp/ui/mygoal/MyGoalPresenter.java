package com.social.goalapp.ui.mygoal;

import android.support.annotation.NonNull;

import com.social.goalapp.data.AppDataManager;
import com.social.goalapp.data.network.model.BlogResponse;
import com.social.goalapp.data.network.model.Result;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aravindraj on 11/7/2017.
 */

public class MyGoalPresenter implements MyGoalContract.Presenter {


    private final AppDataManager mDataManager;

    private final MyGoalContract.View mMyGoalView;


    public MyGoalPresenter(@NonNull AppDataManager dataManager, @NonNull MyGoalContract.View view) {
        mDataManager = dataManager;
        mMyGoalView = view;
        mMyGoalView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    public AppDataManager getDataManager() {
        return mDataManager;
    }

    @Override
    public void callMyGoalAPI() {
        mDataManager.getMyGoal(mDataManager.getAccessToken()).enqueue(new Callback<BlogResponse>() {
            @Override
            public void onResponse(Call<BlogResponse> call, Response<BlogResponse> response) {

                if (response.body() != null) {
                    List<Result> list = response.body().getResults();
                    if (list.size() > 0) {
                        mMyGoalView.addMyGoalData(list.get(0));
                    } else {
mMyGoalView.showAddGoalUI();
                    }
                }
            }

            @Override
            public void onFailure(Call<BlogResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void calladdCommentAPI(int bloid, String comment) {
        mDataManager.addComment(mDataManager.getAccessToken(), bloid, comment).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
               callMyGoalAPI();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    @Override
    public void callDeleteGoalAPI(int bloid) {
        mDataManager.deleteGoal(mDataManager.getAccessToken(), bloid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mMyGoalView.showAddGoalUI();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void callAddGoalAPI(int goaldays, String content) {
        mDataManager.addGoal(mDataManager.getAccessToken(), content,goaldays).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                mMyGoalView.addMyGoalData(response.body());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    @Override
    public void callUpdateGoalAPI(int bloid, String status) {
        mDataManager.patchGoalStatus(mDataManager.getAccessToken(), bloid,status).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mMyGoalView.showAddGoalUI();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
