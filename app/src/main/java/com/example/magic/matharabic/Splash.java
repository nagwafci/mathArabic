package com.example.magic.matharabic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MediaPlayer Hello;
        //initilize sound
        Hello=MediaPlayer.create(Splash.this,R.raw.hello);
        Hello.start();
        ImageView image = (ImageView)findViewById(R.id.imageView4);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        image.startAnimation(animation1);


        // final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);

       // final TextView tv = (TextView) findViewById(R.id.tv);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.mf_progress_bar);
        // Set the progress status zero on each button click
        progressStatus = 0;
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    while(progressStatus < 100){
                        // Update the progress status
                        progressStatus +=1;

                        // Try to sleep the thread for 20 milliseconds
                        try{
                            Thread.sleep(20);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }

                        // Update the progress bar
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                pb.setProgress(progressStatus);
                                // Show the progress on TextView
                               // tv.setText(progressStatus+"");
                                // If task execution completed
                               // if(progressStatus == 100){
                                    // Set a message of completion
                                //    tv.setText("100%");
                              //  }
                            }
                        });
                    }
                }
                 catch (Exception e) {  }
                finally {

                    Intent i = new Intent(Splash.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
        //animation

    }
}
