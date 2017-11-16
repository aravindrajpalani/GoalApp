package com.social.goalapp.ui.mygoal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.social.goalapp.R;
import com.social.goalapp.data.network.model.Commentt;
import com.social.goalapp.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by Aravindraj on 11/6/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private MyGoalPresenter mMyGoalPresenter;
    private Context mContext;
    private ArrayList<Commentt> mCommentList;

    public CommentAdapter(Context context, ArrayList<Commentt> list, MyGoalPresenter presenter) {
        this.mContext = context;
        this.mCommentList = list;
        this.mMyGoalPresenter=presenter;
    }

    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_comment_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.MyViewHolder holder, int position) {
        final Commentt item = mCommentList.get(position);
        holder.commentTxt.setText(item.getComment());
        holder.commentDateTxt.setText(CommonUtils.getFormattedDate(item.getDateCreated()));

    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView commentTxt,commentDateTxt;

        public MyViewHolder(View itemView) {
            super(itemView);
            commentTxt = (TextView) itemView.findViewById(R.id.comment_txt);
            commentDateTxt = (TextView) itemView.findViewById(R.id.commentdate_txt);

        }
    }
}
