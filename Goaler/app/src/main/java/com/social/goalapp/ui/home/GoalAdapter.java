package com.social.goalapp.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.social.goalapp.R;
import com.social.goalapp.data.network.model.Result;
import com.social.goalapp.utils.CommonUtils;
import com.social.goalapp.utils.animutil.ProgressBarAnimation;
import java.util.ArrayList;

/**
 * Created by Aravindraj on 11/6/2017.
 */

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.MyViewHolder> {
    private HomePresenter mHomePresenter;
    private Context mContext;
    private ArrayList<Result> mBlogList;

    public GoalAdapter(Context context, ArrayList<Result> list,HomePresenter presenter) {
        this.mContext = context;
        this.mBlogList = list;
        this.mHomePresenter=presenter;
    }

    @Override
    public GoalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_goal_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GoalAdapter.MyViewHolder holder, final int position) {
        final Result item = mBlogList.get(position);

        holder.displayNameTxt.setText(item.getOwnerDisplayname());
        holder.goalContentTxt.setText(item.getContent());
        holder.goalDaysTxt.setText(""+item.getGoaldays());
        holder.goalPointsTxt.setText(""+item.getPoints());
        holder.commentCountTxt.setText(""+item.getCommentt().size());
        holder.likesCountTxt.setText(""+item.getLikes().size());
        holder.startDayTxt.setText(CommonUtils.getFormattedDate(item.getDateCreated()));
        holder.endDayTxt.setText(CommonUtils.getFormattedDate(item.getExpiryDate()));
        ProgressBarAnimation anim = new ProgressBarAnimation(holder.progress, 0, 80);
        anim.setDuration(1000);
        holder.progress.startAnimation(anim);
        Glide.with(mContext).load("https://graph.facebook.com/" + item.getOwner() + "/picture?type=large").apply(RequestOptions.circleCropTransform()).into(holder.profileImg);
        for(int i=0;i<item.getLikes().size();i++)
        {

            if(Long.parseLong(item.getLikes().get(i).getFbid())==(mHomePresenter.getDataManager().getCurrentFBId()))
            {
                holder.checkBox.setChecked(true);
                Log.e("cecked","forif");
            }

        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("cecked",""+isChecked);
                mHomePresenter.callLikeAPI(item.getId(),position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mBlogList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView goalContentTxt, goalDaysTxt, goalPointsTxt, commentCountTxt, likesCountTxt,displayNameTxt,endDayTxt,startDayTxt;
        public ProgressBar progress;
        public ImageView profileImg;
        public CheckBox checkBox;
        public MyViewHolder(View itemView) {
            super(itemView);
            displayNameTxt = (TextView) itemView.findViewById(R.id.displayname_txt);
            profileImg = (ImageView) itemView.findViewById(R.id.profile_img);
            goalContentTxt = (TextView) itemView.findViewById(R.id.goal_content_txt);
            goalDaysTxt = (TextView) itemView.findViewById(R.id.goal_days_txt);
            goalPointsTxt = (TextView) itemView.findViewById(R.id.goal_points_txt);
            commentCountTxt = (TextView) itemView.findViewById(R.id.comment_count_txt);
            likesCountTxt = (TextView) itemView.findViewById(R.id.likes_count_txt);
            progress=(ProgressBar)itemView.findViewById(R.id.progress);
            checkBox=(CheckBox)itemView.findViewById(R.id.favorite_checkbox);
            startDayTxt = (TextView) itemView.findViewById(R.id.startday_txt);
            endDayTxt = (TextView) itemView.findViewById(R.id.endday_txt);
        }
    }
}
