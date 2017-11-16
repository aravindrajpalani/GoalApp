package com.social.goalapp.ui.profile;

import android.support.annotation.NonNull;

import com.social.goalapp.data.AppDataManager;
import com.social.goalapp.data.network.model.Profile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aravindraj on 11/7/2017.
 */

public class ProfilePresenter implements ProfileContract.Presenter {


    private final AppDataManager mDataManager;

    private final ProfileContract.View mProfileView;


    public ProfilePresenter(@NonNull AppDataManager dataManager, @NonNull ProfileContract.View view) {
        mDataManager = dataManager;
        mProfileView = view;
        mProfileView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    public AppDataManager getDataManager() {
        return mDataManager;
    }





    @Override
    public void callProfileAPI() {
        mDataManager.getProfile(mDataManager.getAccessToken(), mDataManager.getCurrentUserId().intValue()).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                mProfileView.addProfileData(response.body());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }
}
