package com.social.goalapp.ui.login;

/**
 * Created by Aravindraj on 10/1/2017.
 */

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.social.goalapp.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class LoginPagerFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    ImageView img;

    int[] bgs = new int[]{R.drawable.ic_oneanim, R.drawable.ic_followanim, R.drawable.ic_logoanim};
String[] label=new String[]{"Post Your Goals","Follow Friends and Goals","Stay Motivated"};
    String[] labelDesc=new String[]{"Post goal you want to achieve and update progress","Stay connected with friends and get inspired by their goals","You get 2 GoalApp points for each completed goal"};
    public LoginPagerFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static LoginPagerFragment newInstance(int sectionNumber) {
        LoginPagerFragment fragment = new LoginPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loginpager, container, false);
        TextView labelTxt = (TextView) rootView.findViewById(R.id.section_label);
        TextView labelDescTxt = (TextView) rootView.findViewById(R.id.section_desc);


        img = (ImageView) rootView.findViewById(R.id.section_img);
        img.setImageResource(bgs[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);
        labelTxt.setText(label[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);
        labelDescTxt.setText(labelDesc[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);
        Drawable d = img.getDrawable();
        ((Animatable) d).start();

        return rootView;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // load data here
            if(img!=null) {
                Drawable d = img.getDrawable();
                ((Animatable) d).start();
            }
        }else{
            // fragment is no longer visible
        }
    }

}