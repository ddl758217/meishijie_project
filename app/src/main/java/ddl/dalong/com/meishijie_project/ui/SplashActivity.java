package ddl.dalong.com.meishijie_project.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ddl.dalong.com.meishijie_project.MainActivity;
import ddl.dalong.com.meishijie_project.R;

/**
 * 闪屏界面
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private SharedPreferences mSharedPreferences;
    public static final List<Activity> activityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activityList.add(this);
        initSharedPreference();
        if(isFirstTime()) {
            SharedPreferences.Editor edit = mSharedPreferences.edit();
            edit.putString("flag","false");
            edit.apply();
            BedStart();
        }else {
            BedRestart();
        }

    }

    private void BedStart() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"firstTime");
                Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
                startActivity(intent);
            }
        }).start();
    }


    private void BedRestart() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }).start();
    }

    private void initSharedPreference() {
        mSharedPreferences = this.getSharedPreferences("mSharedPreferences", Context.MODE_PRIVATE);
    }

    private boolean isFirstTime(){
        if(!mSharedPreferences.getString("flag","true").equals("true")) return false;
        else return true;
    }

}
