package com.social.goalapp.ui.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.social.goalapp.R;
import com.social.goalapp.data.AppDataManager;
import com.social.goalapp.data.network.AppApiHelper;
import com.social.goalapp.data.prefs.AppPreferencesHelper;
import com.social.goalapp.ui.main.MainActivity;
import com.social.goalapp.utils.AppConstants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {


    private CallbackManager callbackManager;
    private LoginPresenter mLoginPresenter;
    private TextView mLoginTxtView;
    private ImageView zero,one,two;
    private LoginPagerAdapter mSectionsPagerAdapter;
    private ImageView[] indicators;
    private ViewPager mViewPager;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        AppDataManager dataManager = AppDataManager.getInstance(this, AppApiHelper.getInstance(), AppPreferencesHelper.getInstance(this, AppConstants.PREF_NAME));
        mLoginPresenter = new LoginPresenter(dataManager, this);
        mLoginPresenter.isLogin();
        setContentView(R.layout.activity_login);

        init();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.social.goalapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    private void init() {
        setupViewPager();
        mLoginTxtView = (TextView)findViewById(R.id.fb_txt);
        mLoginTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends"));

            }
        });
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        AccessToken token = loginResult.getAccessToken();
                        Log.e("s", "" + token.getToken());
                        mLoginPresenter.callRegisterUserAPI(token.getToken());
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void setupViewPager() {
        mSectionsPagerAdapter = new LoginPagerAdapter(getSupportFragmentManager());


        zero = (ImageView) findViewById(R.id.intro_indicator_0);
        one = (ImageView) findViewById(R.id.intro_indicator_1);
        two = (ImageView) findViewById(R.id.intro_indicator_2);



        indicators = new ImageView[]{zero, one, two};

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(page);
        mViewPager.setOffscreenPageLimit(1);
        updateIndicators(page);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                page = position;
                updateIndicators(page);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }
    @Override
    public void openMainActivity() {
        Log.e("s", "openin");
        Intent i=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }
}
