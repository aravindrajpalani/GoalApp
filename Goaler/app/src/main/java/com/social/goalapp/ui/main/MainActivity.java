package com.social.goalapp.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.social.goalapp.R;
import com.social.goalapp.data.AppDataManager;
import com.social.goalapp.data.network.AppApiHelper;
import com.social.goalapp.data.prefs.AppPreferencesHelper;
import com.social.goalapp.ui.home.HomeFragment;
import com.social.goalapp.ui.mygoal.MyGoalFragment;
import com.social.goalapp.ui.profile.ProfileFragment;
import com.social.goalapp.utils.AppConstants;

public class MainActivity extends AppCompatActivity implements MainContract.View, View.OnClickListener {


    private MainPresenter mMainPresenter;
    private TextView mHomeBtn, mMyGoalBtn, mProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListeners();
    }

    private void setListeners() {
        mMyGoalBtn.setOnClickListener(this);
        mHomeBtn.setOnClickListener(this);
        mProfileBtn.setOnClickListener(this);
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mHomeBtn = (TextView) findViewById(R.id.home_btn);
        mMyGoalBtn = (TextView) findViewById(R.id.mygoal_btn);
        mProfileBtn = (TextView) findViewById(R.id.profile_btn);
        setSupportActionBar(toolbar);
        AppDataManager dataManager = AppDataManager.getInstance(this, AppApiHelper.getInstance(), AppPreferencesHelper.getInstance(this, AppConstants.PREF_NAME));
        mMainPresenter = new MainPresenter(dataManager, this);
        mMainPresenter.isWelcomeShown();


    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @Override
    public void openProfileFrament(boolean firstOpen) {
        openFrag(1);
    }

    @Override
    public void openHomeFrament() {
        openFrag(1);
    }


    private void openFrag(int i) {
        Fragment firstFragment = null;
        switch (i) {
            case 1:
                firstFragment = new HomeFragment();
                break;

            case 2:
                firstFragment = new MyGoalFragment();
                break;

            case 3:
                firstFragment = new ProfileFragment();
                break;

        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.frame, firstFragment, "h");
        fragmentTransaction.addToBackStack("h");
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_btn:
                mHomeBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                mMyGoalBtn.setTextColor(getResources().getColor(R.color.grey_78));
                mProfileBtn.setTextColor(getResources().getColor(R.color.grey_78));
                DrawableCompat.setTint(mHomeBtn.getCompoundDrawables()[1], ContextCompat.getColor(this, R.color.colorPrimary));
                DrawableCompat.setTint(mMyGoalBtn.getCompoundDrawables()[1], ContextCompat.getColor(this, R.color.grey_78));
                DrawableCompat.setTint(mProfileBtn.getCompoundDrawables()[1], ContextCompat.getColor(this, R.color.grey_78));

                openFrag(1);
                break;
            case R.id.mygoal_btn:
                mHomeBtn.setTextColor(getResources().getColor(R.color.grey_78));
                mMyGoalBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                mProfileBtn.setTextColor(getResources().getColor(R.color.grey_78));
                DrawableCompat.setTint(mMyGoalBtn.getCompoundDrawables()[1], ContextCompat.getColor(this, R.color.colorPrimary));
                DrawableCompat.setTint(mHomeBtn.getCompoundDrawables()[1], ContextCompat.getColor(this, R.color.grey_78));
                DrawableCompat.setTint(mProfileBtn.getCompoundDrawables()[1], ContextCompat.getColor(this, R.color.grey_78));

                openFrag(2);
                break;
            case R.id.profile_btn:
                mHomeBtn.setTextColor(getResources().getColor(R.color.grey_78));
                mMyGoalBtn.setTextColor(getResources().getColor(R.color.grey_78));
                mProfileBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                DrawableCompat.setTint(mProfileBtn.getCompoundDrawables()[1], ContextCompat.getColor(this, R.color.colorPrimary));
                DrawableCompat.setTint(mMyGoalBtn.getCompoundDrawables()[1], ContextCompat.getColor(this, R.color.grey_78));
                DrawableCompat.setTint(mHomeBtn.getCompoundDrawables()[1], ContextCompat.getColor(this, R.color.grey_78));
                openFrag(3);
                break;
        }
    }
}
