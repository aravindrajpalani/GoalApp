package com.social.goalapp.ui.mygoal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.social.goalapp.R;
import com.social.goalapp.data.AppDataManager;
import com.social.goalapp.data.network.AppApiHelper;
import com.social.goalapp.data.network.model.Commentt;
import com.social.goalapp.data.network.model.Result;
import com.social.goalapp.data.prefs.AppPreferencesHelper;
import com.social.goalapp.utils.AppConstants;
import com.social.goalapp.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by Aravindraj on 9/10/2017.
 */

public class MyGoalFragment extends Fragment implements MyGoalContract.View, View.OnClickListener {


    private MyGoalPresenter mMyGoalPresenter;
    private TextView mGoalContentTxt, mGoalDaysTxt, mStartDayTxt, mEndDayTxt, mLikesCountTxt, mCommentCountTxt, mGoalPointsTxt;
    private RecyclerView mCommentRView;
    private LinearLayoutManager mLayoutManager;
    private CommentAdapter mCommentAdapter;
    private ArrayList<Commentt> mCommentList = new ArrayList<>();
    private EditText mAddCommentEText, mGoalContentEText, mGoalDaysEText;
    private ImageView mSendCommentBtn, mSettingsBtn;
    private int bloid = 0;
    private NestedScrollView mScrollView;
    private RelativeLayout mFooterLayout, mAddGoalLayout;
    private FloatingActionButton mAddGoalFAB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mygoal, container, false);
        init(v);
        setListeners();
        return v;
    }

    private void setListeners() {
        mSendCommentBtn.setOnClickListener(this);
        mSettingsBtn.setOnClickListener(this);
        mAddGoalFAB.setOnClickListener(this);
    }

    private void init(View v) {
        mGoalContentTxt = (TextView) v.findViewById(R.id.goal_content_txt);
        mGoalDaysTxt = (TextView) v.findViewById(R.id.goal_days_txt);
        mStartDayTxt = (TextView) v.findViewById(R.id.startday_txt);
        mEndDayTxt = (TextView) v.findViewById(R.id.endday_txt);
        mLikesCountTxt = (TextView) v.findViewById(R.id.likes_count_txt);
        mCommentCountTxt = (TextView) v.findViewById(R.id.comment_count_txt);
        mGoalPointsTxt = (TextView) v.findViewById(R.id.goal_points_txt);
        mCommentRView = (RecyclerView) v.findViewById(R.id.comment_rview);
        mAddCommentEText = (EditText) v.findViewById(R.id.add_comment_etext);
        mSendCommentBtn = (ImageView) v.findViewById(R.id.send_comment_btn);
        mSettingsBtn = (ImageView) v.findViewById(R.id.settings_img);
        mScrollView = (NestedScrollView) v.findViewById(R.id.scroll_view);
        mFooterLayout = (RelativeLayout) v.findViewById(R.id.footer);
        mAddGoalLayout = (RelativeLayout) v.findViewById(R.id.add_goal_layout);
        mAddGoalFAB = (FloatingActionButton) v.findViewById(R.id.add_goal_fab);
        mGoalContentEText = (EditText) v.findViewById(R.id.goal_content_etxt);
        mGoalDaysEText = (EditText) v.findViewById(R.id.goal_duration_etxt);
        mLayoutManager = new LinearLayoutManager(getContext());
        mCommentRView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mCommentRView.getContext(), mLayoutManager.getOrientation());
        mCommentRView.addItemDecoration(dividerItemDecoration);
        mCommentAdapter = new CommentAdapter(getContext(), mCommentList, mMyGoalPresenter);
        mCommentRView.setAdapter(mCommentAdapter);
        AppDataManager dataManager = AppDataManager.getInstance(getContext(), AppApiHelper.getInstance(), AppPreferencesHelper.getInstance(getContext(), AppConstants.PREF_NAME));
        mMyGoalPresenter = new MyGoalPresenter(dataManager, this);
        mMyGoalPresenter.callMyGoalAPI();



    }


    @Override
    public void setPresenter(MyGoalContract.Presenter presenter) {

    }

    @Override
    public void addMyGoalData(Result list) {
        mAddGoalFAB.setEnabled(true);
        mAddGoalFAB.setAlpha(1f);
        mScrollView.setVisibility(View.VISIBLE);
        mFooterLayout.setVisibility(View.VISIBLE);
        mAddGoalLayout.setVisibility(View.GONE);
        bloid = list.getId().intValue();
        mGoalContentTxt.setText(list.getContent());
        mGoalDaysTxt.setText("" + list.getGoaldays() + " DAYS");
        mStartDayTxt.setText(CommonUtils.getFormattedDate(list.getDateCreated()));
        mEndDayTxt.setText(CommonUtils.getFormattedDate(list.getExpiryDate()));
        mLikesCountTxt.setText("" + list.getLikes().size());
        mCommentCountTxt.setText("PRORESS UPDATES (" + list.getCommentt().size() + ")");
        mGoalPointsTxt.setText("" + list.getPoints());
        mCommentList.clear();
        mCommentList.addAll(list.getCommentt());
        mCommentAdapter.notifyDataSetChanged();
        mSendCommentBtn.setEnabled(true);
        mSendCommentBtn.setAlpha(1f);
    }

    @Override
    public void showAddGoalUI() {
        mAddGoalLayout.setVisibility(View.VISIBLE);
        mFooterLayout.setVisibility(View.GONE);
        mScrollView.setVisibility(View.GONE);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_comment_btn:
                if (mAddCommentEText.getText().toString().trim().length() > 0) {
                    mSendCommentBtn.setEnabled(false);
                    mSendCommentBtn.setAlpha(0.5f);
                    mMyGoalPresenter.calladdCommentAPI(bloid, mAddCommentEText.getText().toString().trim());
                }
                break;
            case R.id.settings_img:
                openPopupMenu();
                break;
            case R.id.add_goal_fab:
                String num = mGoalDaysEText.getText().toString().trim();
             String content=mGoalContentEText.getText().toString().trim();
             if(num.length()>0&&content.length()>0) {
                 mMyGoalPresenter.callAddGoalAPI(Integer.parseInt(num), mGoalContentEText.getText().toString().trim());
                 mAddGoalFAB.setEnabled(false);
                 mAddGoalFAB.setAlpha(0.3f);
             }
                break;
        }
    }

    private void openPopupMenu() {
        PopupMenu popup = new PopupMenu(getContext(), mSettingsBtn);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.goal_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_deletegoal:
                        mMyGoalPresenter.callDeleteGoalAPI(bloid);
                        break;
                    case R.id.menu_goalcompleted:
                        mMyGoalPresenter.callUpdateGoalAPI(bloid, "done");
                        break;
                }
                return true;
            }
        });

        popup.show();//showing popup menu
    }


}
