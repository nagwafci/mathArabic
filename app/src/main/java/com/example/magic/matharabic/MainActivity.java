package com.example.magic.matharabic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    MediaPlayer Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button=MediaPlayer.create(MainActivity.this,R.raw.button);



    }

    public void sum(View view)
    {
        Button.start();
        Intent SumIntent=new Intent(this,sum.class);
        startActivity(SumIntent);
    }
    public void subtracion(View view)
    {
        Button.start();
        Intent SubtractionIntent=new Intent(this,Subtract.class);
        startActivity(SubtractionIntent);
    }


    public void count(View view) {
        Button.start();
        Intent CountIntent=new Intent(this,count.class);
        startActivity(CountIntent);

    }
}
