package com.phamhien.musicplay.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.phamhien.musicplay.Adapter.ViewPagerPlaynhac;
import com.phamhien.musicplay.Fragment.Fragment_dianhac;
import com.phamhien.musicplay.Fragment.Fragment_phatnhac;
import com.phamhien.musicplay.Model.Baihat;
import com.phamhien.musicplay.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PhatnhacActivity extends AppCompatActivity {


    Toolbar toolbarphatnhac;
    TextView txttime,txttiletime;
    SeekBar sktime;
    ImageButton img1,img2,img3,img4,img5,img6;
    ViewPager viewPagerphatnhac;
    public static ArrayList<Baihat> baihatphatnhac = new ArrayList<>();
    public static ViewPagerPlaynhac adapteenhac;
    Fragment_dianhac fragment_dianhac;
    Fragment_phatnhac fragment_phatnhac;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phatnhac);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy. Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        init();
        getDataFromInter();
        Clicck();

    }

    private void Clicck() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapteenhac.getItem(1) != null){
                    if (baihatphatnhac.size() > 0){
                    fragment_dianhac.Playnhac(baihatphatnhac.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,300);
                    }
                }

            }
        },500);
       img3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (mediaPlayer.isPlaying()){
                   mediaPlayer.pause();
                   img3.setImageResource(R.drawable.iconplay);
               }else{
                   mediaPlayer.start();
                   img3.setImageResource(R.drawable.iconpause);



               }

           }
       });
       img5.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  if ( repeat == false){
                      if (checkrandom == true){
                          checkrandom = false;
                          img5.setImageResource(R.drawable.iconsyned);
                          img1.setImageResource(R.drawable.iconsuffle);
                      }
                      img5.setImageResource(R.drawable.iconsyned);
                      repeat = true;
                  }else {
                      img5.setImageResource(R.drawable.iconrepeat);
                      repeat= false;
                  }
               }
       });
       img1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if ( checkrandom == false){
                   if (repeat == true){
                       repeat = false;
                       img1.setImageResource(R.drawable.iconshuffled);
                       img5.setImageResource(R.drawable.iconrepeat);
                   }
                   img1.setImageResource(R.drawable.iconshuffled);
                   checkrandom = true;
               }else {
                   img1.setImageResource(R.drawable.iconsuffle);
                   checkrandom= false;
               }
           }
       });
       sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

               mediaPlayer.seekTo(seekBar.getProgress());
           }
       });
       img4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (baihatphatnhac.size() > 0) {
                   if (mediaPlayer.isPlaying()  || mediaPlayer != null){
                       mediaPlayer.stop();
                       mediaPlayer.release();
                       mediaPlayer = null ;

                   }
                   if (position < (baihatphatnhac.size())){
                       img3.setImageResource(R.drawable.iconpause);
                       position++;
                       if (repeat == true ){
                           if (position == 0){
                               position= baihatphatnhac.size();

                           }
                           position -=1;
                       }
                       if (checkrandom == true){
                           Random random = new Random();
                           int index = random.nextInt(baihatphatnhac.size());
                           if (index == position){
                               position = index - 1;
                           }
                           position = index;
                       }
                       if (position> (baihatphatnhac.size()-1)){
                           position = 0;
                       }
                       new PlayMp3().execute(baihatphatnhac.get(position).getLinkbaihat());
                       fragment_dianhac.Playnhac(baihatphatnhac.get(position).getHinhbaihat());
                       getSupportActionBar().setTitle(baihatphatnhac.get(position).getTenbaihat());
                       UpdateTime();
                   }
               }
               img2.setClickable(false);
               img4.setClickable(false);
               Handler handler1 = new Handler();
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       img2.setClickable(true);
                       img4.setClickable(true);
                   }
               },500);
           }
       });
       img2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (baihatphatnhac.size() > 0) {
                   if (mediaPlayer.isPlaying()  || mediaPlayer != null){
                       mediaPlayer.stop();
                       mediaPlayer.release();
                       mediaPlayer = null ;

                   }
                   if (position < (baihatphatnhac.size())){
                       img3.setImageResource(R.drawable.iconpause);
                       position--;
                       if (position < 0){
                           position = baihatphatnhac.size() -1;
                       }
                       if (repeat == true ){

                           position +=1;
                       }
                       if (checkrandom == true){
                           Random random = new Random();
                           int index = random.nextInt(baihatphatnhac.size());
                           if (index == position){
                               position = index - 1;
                           }
                           position = index;
                       }

                       new PlayMp3().execute(baihatphatnhac.get(position).getLinkbaihat());
                       fragment_dianhac.Playnhac(baihatphatnhac.get(position).getHinhbaihat());
                       getSupportActionBar().setTitle(baihatphatnhac.get(position).getTenbaihat());
                       UpdateTime();
                   }
               }
               img2.setClickable(false);
               img4.setClickable(false);
               Handler handler1 = new Handler();
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       img2.setClickable(true);
                       img4.setClickable(true);
                   }
               },500);
           }
       });

    img6.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Toast.makeText(PhatnhacActivity.this, "Đang dowload", Toast.LENGTH_SHORT).show();

        }



    });
    }





    private void getDataFromInter() {
        Intent intent = getIntent();
        baihatphatnhac.clear();
        if (intent != null){
            if (intent.hasExtra("cakhuc")){
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                baihatphatnhac.add(baihat);
            }
            if (intent.hasExtra("caccakhuc")){
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("caccakhuc");
                baihatphatnhac = baihatArrayList;
            }
        }

    }

    private void init() {
        toolbarphatnhac = findViewById(R.id.toolbarphatnha);
        txttime = findViewById(R.id.textViewtime);
        txttiletime = findViewById(R.id.textViewtitlephatnha);
        img1 = findViewById(R.id.imagebuttonanh1);
        img2 = findViewById(R.id.imagebuttonanh2);
        img3 = findViewById(R.id.imagebuttonanh3);
        img4 = findViewById(R.id.imagebuttonanh4);
        img5= findViewById(R.id.imagebuttonanh5);
        img6= findViewById(R.id.imagebuttonanh6);
        sktime = findViewById(R.id.seekbarphatnhac);
        viewPagerphatnhac = findViewById(R.id.viewpagerphatnha);
        setSupportActionBar(toolbarphatnhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarphatnhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//
                baihatphatnhac.clear();

            }
        });
        toolbarphatnhac.setTitleTextColor(Color.WHITE);

        fragment_dianhac = new Fragment_dianhac();
        fragment_phatnhac = new Fragment_phatnhac();

        adapteenhac = new ViewPagerPlaynhac(getSupportFragmentManager());
        adapteenhac.add(fragment_phatnhac);
        adapteenhac.add(fragment_dianhac);

        viewPagerphatnhac.setAdapter(adapteenhac);

        fragment_dianhac = (Fragment_dianhac) adapteenhac.getItem(1);
        if (baihatphatnhac.size() >0){
            getSupportActionBar().setTitle(baihatphatnhac.get(0).getTenbaihat());
            new PlayMp3().execute(baihatphatnhac.get(0).getLinkbaihat());
            img3.setImageResource(R.drawable.iconpause);
        }
    }
   class PlayMp3 extends AsyncTask<String,Void,String>{


       @Override
       protected String doInBackground(String... strings) {
           return strings[0];
       }

       @Override
       protected void onPostExecute(String baihat) {
           super.onPostExecute(baihat);
           try {
           mediaPlayer = new MediaPlayer();
           mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
           mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
               @Override
               public void onCompletion(MediaPlayer mp) {

                   mediaPlayer.stop();
                   mediaPlayer.release();
               }

           });

               mediaPlayer.setDataSource(baihat);
               mediaPlayer.prepare();
           } catch (IOException e) {
               e.printStackTrace();
           }
           mediaPlayer.start();
           TimeSong();
           UpdateTime();
       }
   }


    private void TimeSong() {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
       txttiletime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
       sktime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime (){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txttime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (next == true){

                    if (position < (baihatphatnhac.size())){
                        img3.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true ){
                            if (position == 0){
                                position= baihatphatnhac.size();

                            }
                            position -=1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(baihatphatnhac.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position> (baihatphatnhac.size()-1)){
                            position = 0;
                        }
                        new PlayMp3().execute(baihatphatnhac.get(position).getLinkbaihat());
                        fragment_dianhac.Playnhac(baihatphatnhac.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(baihatphatnhac.get(position).getTenbaihat());
                    }

                img2.setClickable(false);
                img4.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        img2.setClickable(true);
                        img4.setClickable(true);
                    }
                },500);
                next = false;
                handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
