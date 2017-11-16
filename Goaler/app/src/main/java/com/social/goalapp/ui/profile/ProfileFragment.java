package com.social.goalapp.ui.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.social.goalapp.R;
import com.social.goalapp.data.AppDataManager;
import com.social.goalapp.data.network.AppApiHelper;
import com.social.goalapp.data.network.model.Profile;
import com.social.goalapp.data.prefs.AppPreferencesHelper;
import com.social.goalapp.utils.AppConstants;

/**
 * Created by Aravindraj on 9/10/2017.
 */

public class ProfileFragment extends Fragment implements ProfileContract.View {


    private ProfilePresenter mProfilePresenter;
    private TextView mPointsTxt, mFollowersTxt, mFollowingTxt, mDisplayNameTxt;
    private ImageView mProfileImgView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        init(v);
        setListeners();
        return v;
    }

    private void setListeners() {

    }

    private void init(View v) {
        mDisplayNameTxt = (TextView) v.findViewById(R.id.displayname_txt);
        mFollowersTxt = (TextView) v.findViewById(R.id.followers_txt);
        mFollowingTxt = (TextView) v.findViewById(R.id.following_txt);
        mPointsTxt = (TextView) v.findViewById(R.id.points_txt);
        mProfileImgView = (ImageView) v.findViewById(R.id.profile_img);
        AppDataManager dataManager = AppDataManager.getInstance(getContext(), AppApiHelper.getInstance(), AppPreferencesHelper.getInstance(getContext(), AppConstants.PREF_NAME));
        mProfilePresenter = new ProfilePresenter(dataManager, this);
        mProfilePresenter.callProfileAPI();

    }


    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {

    }

    @Override
    public void addProfileData(Profile profile) {
        Glide.with(getContext()).load("https://graph.facebook.com/" + profile.getFbid() + "/picture?type=large").apply(RequestOptions.circleCropTransform()).into(mProfileImgView);
        mDisplayNameTxt.setText(profile.getDisplayname());
        mPointsTxt.setText("" + profile.getGoalpoints());
        mFollowersTxt.setText("" + profile.getFollowerslist().size());
        mFollowingTxt.setText("" + profile.getFollowinglist().size());
    }
}
