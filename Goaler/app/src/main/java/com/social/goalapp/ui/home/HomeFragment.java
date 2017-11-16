package com.social.goalapp.ui.home;

import android.annotation.TargetApi;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.social.goalapp.R;
import com.social.goalapp.data.AppDataManager;
import com.social.goalapp.data.network.AppApiHelper;
import com.social.goalapp.data.network.model.Result;
import com.social.goalapp.data.prefs.AppPreferencesHelper;
import com.social.goalapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aravindraj on 9/10/2017.
 */

public class HomeFragment extends Fragment implements HomeContract.View {


    private RecyclerView mGoalRView;
    private LinearLayoutManager mLayoutManager;
    private GoalAdapter mGoalAdapter;
    private ArrayList<Result> mGoalList = new ArrayList<>();
    private HomePresenter mHomePresenter;
    private ImageView mLoadingImgView;
    private TextView mLoadingTxtView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        init(v);
        setListeners();
        return v;
    }

    private void setListeners() {

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void init(View v) {
        mGoalRView = (RecyclerView) v.findViewById(R.id.goal_rview);

        mLoadingImgView = (ImageView) v.findViewById(R.id.loading_img);
        mLoadingTxtView = (TextView) v.findViewById(R.id.loading_txt);
        AppDataManager dataManager = AppDataManager.getInstance(getContext(), AppApiHelper.getInstance(), AppPreferencesHelper.getInstance(getContext(), AppConstants.PREF_NAME));
        mHomePresenter = new HomePresenter(dataManager, this);
        mLayoutManager = new LinearLayoutManager(getContext());
        mGoalRView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mGoalRView.getContext(), mLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.ic_divider));
        mGoalRView.addItemDecoration(dividerItemDecoration);
        mGoalAdapter = new GoalAdapter(getContext(), mGoalList, mHomePresenter);
        mGoalRView.setAdapter(mGoalAdapter);
        showLoading();
        mHomePresenter.callAllGoalsAPI();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showLoading() {
        mLoadingImgView.setVisibility(View.VISIBLE);
        mLoadingTxtView.setVisibility(View.VISIBLE);
        final AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.ic_loadinganim);
        mLoadingImgView.setImageDrawable(drawable);
        final Handler mainHandler = new Handler(Looper.getMainLooper());
        drawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
            }

            @Override
            public void onAnimationEnd(final Drawable drawable) {
                super.onAnimationEnd(drawable);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ((AnimatedVectorDrawable) drawable).start();
                    }
                });
            }
        });
        drawable.start();
    }


    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

    }

    @Override
    public void addGoalData(List<Result> list) {
        mLoadingImgView.setVisibility(View.GONE);
        mLoadingTxtView.setVisibility(View.GONE);
        mGoalRView.setVisibility(View.VISIBLE);
        mGoalList.clear();
        mGoalList.addAll(list);
        mGoalAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyAdapter(int pos,Result item) {
        mGoalList.set(pos,item);
        mGoalAdapter.notifyDataSetChanged();
    }
}
