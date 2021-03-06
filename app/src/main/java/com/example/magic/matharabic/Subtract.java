package com.example.magic.matharabic;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Subtract extends AppCompatActivity {

    MediaPlayer Buttonsound;
    MediaPlayer correctsound;
    MediaPlayer WrongAnswer;

    Bitmap Correct;
    static int count;
    static int id=1;
    int finish=0;
    int destroy=0;
    int result;
    counter ct;
    NotificationChannel mychannel;
    Toast toastR;
    Toast toastwrong;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtract);
        LayoutInflater myInflater=getLayoutInflater();

        //Gift View
        View myView=myInflater.inflate(R.layout.gift,(ViewGroup) findViewById(R.id.GiftLayout));
        toast=new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(myView);

        //right answer View
        View rightView=myInflater.inflate(R.layout.gift,(ViewGroup) findViewById(R.id.GiftLayout));
        toastR=new Toast(getApplicationContext());
        toastR.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        toastR.setDuration(Toast.LENGTH_LONG);
        toastR.setView(rightView);

        //wrong answer View
        View wrongView=myInflater.inflate(R.layout.wrong,(ViewGroup) findViewById(R.id.wrong));
        toastwrong=new Toast(getApplicationContext());
        toastwrong.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        toastwrong.setDuration(Toast.LENGTH_SHORT);
        toastwrong.setView(wrongView);

        //initialize sound
        Buttonsound=MediaPlayer.create(Subtract.this,R.raw.button);
        correctsound=MediaPlayer.create(Subtract.this,R.raw.e);
        WrongAnswer=MediaPlayer.create(Subtract.this,R.raw.wrong2);



        //create counter
        ct =new counter(10000,100);
        ct.start();

        //view Equation
        getEquation();



    }
    public void getEquation(){
        //initilize number images
        int[] NumberImage={R.drawable.onear,R.drawable.twoar,R.drawable.threear,R.drawable.fourar,
                R.drawable.fivear,R.drawable.sixar,R.drawable.sevenar,R.drawable.eightar,R.drawable.ninear,
                R.drawable.tenar,R.drawable.eleven,R.drawable.tweelf,R.drawable.therteen,R.drawable.fourteen,
                R.drawable.fivteen,R.drawable.sixteen,R.drawable.seventeen,R.drawable.eighteen,
                R.drawable.ninteen,R.drawable.twenty};



        //get random numbers for equation
        Random rand= new Random();
        int num1= rand.nextInt(10)+1;
        int num2= rand.nextInt(10)+1;
        //check num1>num2
        if( num1<num2) {
            int x = num1;
            num1 = num2;
            num2 = x;
        }
        ImageView N1=findViewById(R.id.n1);
        N1.setImageResource(NumberImage[num1-1]);

        ImageView N2=findViewById(R.id.n2);
        N2.setImageResource(NumberImage[num2-1]);


        //get random numbers for result
        int RandomResult1= rand.nextInt(20)+1;
        int RandomResult2= rand.nextInt(20)+1;

        //find correct result
        result=num1-num2;

        //check if result equal any random number
        if(result==RandomResult1)
            RandomResult1= rand.nextInt(20)+1;
        else if (result==RandomResult2)
            RandomResult2= rand.nextInt(20)+1;


        Drawable myDrawable = getResources().getDrawable(NumberImage[result-1]);
        Correct= ((BitmapDrawable) myDrawable).getBitmap();
        //view random results
        ArrayList<Integer> asd = new ArrayList<Integer>();
        asd.add( NumberImage[RandomResult1-1]);
        asd.add(NumberImage[RandomResult2-1]);
        asd.add( NumberImage[result-1]);
        Collections.shuffle(asd);
        ImageView R1=findViewById(R.id.R1);
        ImageView R2=findViewById(R.id.R2);
        ImageView R3=findViewById(R.id.R3);
        R1.setImageResource(asd.get(0));
        R2.setImageResource(asd.get(1));
        R3.setImageResource(asd.get(2));

    }
    @Override
    protected void onDestroy() {

        destroy=1;
        stopAllSounds();
        super.onDestroy();

    }






    public void R1click(View view) {

        ImageView R1=findViewById(R.id.R1);
        ImageView q=findViewById(R.id.question);

        Bitmap bmap = ((BitmapDrawable)R1.getDrawable()).getBitmap();
        if(bmap.sameAs(Correct))
        {
            toastR.show();
            q.setImageBitmap(bmap);
            correctsound.start();
            PlayAgainSubtract();
            finish=1;}
        else
        {
            toastwrong.show();
            WrongAnswer.start();}

    }

    public void R2click(View view) {

        ImageView R2=findViewById(R.id.R2);
        ImageView q=findViewById(R.id.question);
        Bitmap bmap = ((BitmapDrawable)R2.getDrawable()).getBitmap();
        if(bmap.sameAs(Correct))
        {
            toastR.show();
            q.setImageBitmap(bmap);
            correctsound.start();

            PlayAgainSubtract();
            finish=1;}
        else
        {
            toastwrong.show();
            WrongAnswer.start();}
    }
    public void R3click(View view) {
        ImageView R3=findViewById(R.id.R3);
        ImageView q=findViewById(R.id.question);
        Bitmap bmap = ((BitmapDrawable)R3.getDrawable()).getBitmap();
        if(bmap.sameAs(Correct))
        {
            toastR.show();
            q.setImageBitmap(bmap);
            correctsound.start();

            PlayAgainSubtract();
            finish=1;}
        else
        {
            toastwrong.show();
            WrongAnswer.start();}
    }

    public void PlayAgainSubtract() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(finish==1)
            count++;

        //create notifcation after 4 true results
        String message=getResources().getString(R.string.good);
        NotificationCompat.Builder notif=new NotificationCompat.Builder(this,"1")
                .setSmallIcon(R.drawable.redbox)
                .setContentText(message)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(count==4)
        {
            toast.show();
            manager.notify(id,notif.build());
            count=0;
            id++;
        }


        Intent i = new Intent(this,Subtract.class);
        //the following 2 tags are for clearing the backStack and start fresh
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        startActivity(i);

    }

    public void Home(View view) {
        Buttonsound.start();
        stopAllSounds();

        Intent SumIntent=new Intent(this,MainActivity.class);
        startActivity(SumIntent);
    }



    public class counter extends CountDownTimer {

        public counter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // if(finish==0&&destroy==0)countersound.start();
        }

        @Override
        public void onFinish() {
           // if(finish==0)
            //    Cry.start();
        }
    }

    public void stopAllSounds(){
        Buttonsound.stop();
        correctsound.stop();
        WrongAnswer.stop();
       // Cry.stop();

    }
}
