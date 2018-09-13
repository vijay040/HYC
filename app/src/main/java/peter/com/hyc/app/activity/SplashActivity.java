package peter.com.hyc.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler h = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                return false;
            }
        });

        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, LandingActivity.class));
                finish();
            }
        }, 3000);

    }
}
