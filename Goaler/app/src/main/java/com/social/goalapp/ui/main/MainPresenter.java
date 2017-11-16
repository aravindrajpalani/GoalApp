package com.social.goalapp.ui.main;

import android.support.annotation.NonNull;

import com.social.goalapp.data.AppDataManager;

/**
 * Created by Aravindraj on 11/3/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private final AppDataManager mDataManager;

    private final MainContract.View mMainView;


    public MainPresenter(@NonNull AppDataManager dataManager, @NonNull MainContract.View tasksView) {
        mDataManager = dataManager;
        mMainView = tasksView;
        mMainView.setPresenter(this);
    }


    @Override
    public void start() {

    }


    @Override
    public boolean isWelcomeShown() {
        if(mDataManager.isWelcomeShown())
        {mMainView.openHomeFrament();
            return false;
        }
        else {
            mDataManager.setWelcomeShown(true);
            mMainView.openProfileFrament(true);
            return false;
        }

    }
}
