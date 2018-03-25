package com.example.magic.matharabic;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class count extends AppCompatActivity {

    Activity myActivity;
    MediaPlayer Buttonsound;
    MediaPlayer correctsound;
    MediaPlayer WrongAnswer;

    static int count;
    static int id=1;
    int finish=0;
    int destroy=0;
    int correctResult;


    Toast toastR;
    Toast toastwrong;

    Bitmap Correct;
    Boolean trueAnswer=false;

    ImageView[] stars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);


        //Gift inflater
        LayoutInflater myInflater=getLayoutInflater();
        View myView=myInflater.inflate(R.layout.gift,(ViewGroup) findViewById(R.id.GiftLayout));
        Toast toast=new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(myView);

        //right answer inflater
        View rightView=myInflater.inflate(R.layout.gift,(ViewGroup) findViewById(R.id.GiftLayout));
        toastR=new Toast(getApplicationContext());
        toastR.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        toastR.setDuration(Toast.LENGTH_SHORT);
        toastR.setView(rightView);

        //wrong answer inflater
        View wrongView=myInflater.inflate(R.layout.wrong,(ViewGroup) findViewById(R.id.wrong));
        toastwrong=new Toast(getApplicationContext());
        toastwrong.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        toastwrong.setDuration(Toast.LENGTH_SHORT);
        toastwrong.setView(wrongView);


        //initialize sound
        Buttonsound=MediaPlayer.create(count.this,R.raw.button);
        correctsound=MediaPlayer.create(count.this,R.raw.e);
        WrongAnswer=MediaPlayer.create(count.this,R.raw.wrong2);


        //initilize number images
        int[] NumberImage={R.drawable.onear,R.drawable.twoar,R.drawable.threear,R.drawable.fourar,
                R.drawable.fivear,R.drawable.sixar,R.drawable.sevenar,R.drawable.eightar,R.drawable.ninear,
                R.drawable.tenar,R.drawable.eleven,R.drawable.tweelf,R.drawable.therteen,R.drawable.fourteen,
                R.drawable.fivteen,R.drawable.sixteen,R.drawable.seventeen,R.drawable.eighteen,
                R.drawable.ninteen,R.drawable.twenty};//get randum number
        Random rand= new Random();
        int randomNum= rand.nextInt(10)+1;
        correctResult=NumberImage[ (randomNum-1)];
        Drawable myDrawable = getResources().getDrawable(correctResult);
        Correct= ((BitmapDrawable) myDrawable).getBitmap();

        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = 1; i <= 10; ++i)
            number.add(i);
        Collections.shuffle(number);


        //get random numbers for result
        int RandomResult1=number.get(1);
        int RandomResult2=number.get(5);
        //check if result equal any random number
        if(randomNum==RandomResult1)
            RandomResult1= number.get(2);
        if(randomNum==RandomResult2)
            RandomResult2= number.get(9);




        //view random results
        ArrayList<Integer> asd = new ArrayList<Integer>();

        asd.add( RandomResult1);
        asd.add( RandomResult2);
        asd.add( randomNum);
        Collections.shuffle(asd);

        ImageView Number1=findViewById(R.id.R1);
        Number1.setImageResource( NumberImage[asd.get(0)-1]);
        ImageView Number2=findViewById(R.id.R2);
        Number2.setImageResource(NumberImage[asd.get(1)-1]);
        ImageView Number3=findViewById(R.id.R3);
        Number3.setImageResource(NumberImage[asd.get(2)-1]);

        //ducks inflater
        GridView list=findViewById(R.id.grid);
        myAdapter duckAdapter=new myAdapter(randomNum);
        list.setAdapter(duckAdapter);

    }





    class  myAdapter   extends BaseAdapter {
        int ducksNumber;
        public myAdapter(int ducksNumber) {
            this.ducksNumber=ducksNumber;
        }

        @Override
        public int getCount() {
            return ducksNumber;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater myInflate=getLayoutInflater();
            View myView=myInflate.inflate(R.layout.ducklayout,parent,false);
            ImageView duckView=myView.findViewById(R.id.duckimage);
            duckView.setImageResource(R.drawable.miniduck);
            return  myView;
        }
    }

        @Override
    protected void onDestroy() {

        destroy=1;
        stopAllSounds();
        super.onDestroy();

    }
    public void R1click(View view) {
        ImageView R1=findViewById(R.id.R1);
        Bitmap bmap = ((BitmapDrawable)R1.getDrawable()).getBitmap();
        if(bmap.sameAs(Correct))
        {

            TrueAnswer();}
        else
        {
            toastwrong.show();
            WrongAnswer.start();
            trueAnswer=false;
        }

    }
    public void R2click(View view) {

        ImageView R2=findViewById(R.id.R2);
        Bitmap bmap = ((BitmapDrawable)R2.getDrawable()).getBitmap();
        if(bmap.sameAs(Correct))
        {


            TrueAnswer();}
        else
        {
            toastwrong.show();
            WrongAnswer.start();
            trueAnswer=false;
        }

    }
    public void R3click(View view) {
        ImageView R3=findViewById(R.id.R3);
        Bitmap bmap = ((BitmapDrawable)R3.getDrawable()).getBitmap();
        if(bmap.sameAs(Correct))
        {


            TrueAnswer();}
        else
        {
            toastwrong.show();
            WrongAnswer.start();
            trueAnswer=false;}

    }

    public void TrueAnswer(){
        toastR.show();
        count++;
        correctsound.start();

        PlayAgain();
    }

    public void PlayAgain() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(this,count.class);

         //the following 2 tags are for clearing the backStack and start fresh
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(i);

    }
    public void Home(View view) {

        stopAllSounds();
        Buttonsound.start();
        Intent SumIntent=new Intent(this,MainActivity.class);
        startActivity(SumIntent);
    }

    public void stopAllSounds(){
        Buttonsound.stop();
        correctsound.stop();
        WrongAnswer.stop();


    }
}
