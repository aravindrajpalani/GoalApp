package com.social.goalapp.ui.home;

import android.support.annotation.NonNull;
import android.util.Log;

import com.social.goalapp.data.AppDataManager;
import com.social.goalapp.data.network.model.BlogResponse;
import com.social.goalapp.data.network.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aravindraj on 11/7/2017.
 */

public class HomePresenter implements HomeContract.Presenter {


    private final AppDataManager mDataManager;
    private final HomeContract.View mHomeView;


    public HomePresenter(@NonNull AppDataManager dataManager, @NonNull HomeContract.View view) {
        mDataManager = dataManager;
        mHomeView = view;
        mHomeView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    public AppDataManager getDataManager() {
        return mDataManager;
    }

    @Override
    public void callAllGoalsAPI() {
        mDataManager.getAllGoals(mDataManager.getAccessToken()).enqueue(new Callback<BlogResponse>() {
            @Override
            public void onResponse(Call<BlogResponse> call, Response<BlogResponse> response) {
                Log.e("ff" + response.message(), "s");
                if (response.body() != null) {
                    Log.e("ff" + response.message(), "s" + response.body().getResults().size());
                    mHomeView.addGoalData(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<BlogResponse> call, Throwable t) {
                Log.e("ff", "s" + t.getMessage());
            }
        });
    }

    @Override
    public void callLikeAPI(int blogid, final int itemPosition) {
        mDataManager.likeGoal(mDataManager.getAccessToken(), blogid).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.e("callLikeAPI", "s" + response.message());
                mHomeView.notifyAdapter(itemPosition,response.body());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("ff", "s" + t.getMessage());
            }
        });
    }
}
