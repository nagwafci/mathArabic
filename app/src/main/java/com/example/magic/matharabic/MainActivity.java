package com.example.magic.matharabic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer Hello;
    MediaPlayer Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inflater
        LayoutInflater myInflater=getLayoutInflater();
        View myView=myInflater.inflate(R.layout.welcome,(ViewGroup) findViewById(R.id.WelcomeLayout));

        Toast toast=new Toast(getApplicationContext());
        toast.setGravity(Gravity.RIGHT,20,20);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(myView);
        toast.show();
        //initilize sound
        Hello=MediaPlayer.create(MainActivity.this,R.raw.hello);
        Button=MediaPlayer.create(MainActivity.this,R.raw.button);
        Hello.start();

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
