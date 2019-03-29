package com.example.jeanbernuycharvet.intercorpretail.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jeanbernuycharvet.intercorpretail.LoginActivity;
import com.example.jeanbernuycharvet.intercorpretail.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    /**
     * Timer task used to splash duration
     */
    private SplashTimerTask timerTask;

    /**
     * Timer used to control the splash duration
     */
    private Timer timer;

    private boolean splashFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        doStartup();
    }

    /**
     * Starts the timer that must control the SplashActivity duration
     */
    private void startSplashTimer() {

        if (timer == null) {
            timerTask = new SplashTimerTask();
            timer = new Timer();
            timer.schedule(timerTask, 2 * 1000);
        }

    }

    /**
     * Method that will be called both when the splash timer has finished and when
     * the network operations have finished, guaranteeing the mutual exclusion
     */
    private synchronized void splashTimeEnded() {
        splashFinished = true;
        openNextScreen();
    }

    public void openNextScreen() {
        showLoginActivity();
    }

    /**
     * Show the login activity
     */
    private void showLoginActivity() {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    /**
     * Invoke startup sequence
     */
    private void doStartup() {
        //only counting for the first time (not in the retries)
        if (!splashFinished) {
            startSplashTimer();
        }
    }

    /**
     * Internal class
     * Timer task for controlling the splash duration
     */
    public class SplashTimerTask extends TimerTask {

        /**
         * @see java.util.TimerTask#run()
         */
        public void run() {
            splashTimeEnded();
        }
    }
}
