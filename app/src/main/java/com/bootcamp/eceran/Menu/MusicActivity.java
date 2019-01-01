package com.bootcamp.eceran.Menu;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bootcamp.eceran.R;

public class MusicActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private int sound1,sound2,sound3,sound4,sound5,sound6,sound7,sound8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();

        }
        else {
            soundPool = new SoundPool (6, AudioManager.STREAM_MUSIC,0);
        }

        sound1 = soundPool.load(this,R.raw.suara1,1);
        sound2 = soundPool.load(this,R.raw.suara2,1);
        sound3 = soundPool.load(this,R.raw.suara3,1);
        sound4 = soundPool.load(this,R.raw.suara4,1);
        sound5 = soundPool.load(this,R.raw.suara5,1);
        sound6 = soundPool.load(this,R.raw.suara6,1);
        sound7 = soundPool.load(this,R.raw.suara8,1);
        sound8 = soundPool.load(this,R.raw.suara7,1);
    }


    public void klikdo(View view) {soundPool.play(sound1,1,1,0,0,1);}


    public void klikre(View view) {soundPool.play(sound2,1,1,0,0,1);}


    public void klikmi(View view) {soundPool.play(sound3,1,1,0,0,1);}


    public void klikfa(View view) {soundPool.play(sound4,1,1,0,0,1);
    }

    public void kliksol(View view) {soundPool.play(sound5,1,1,0,0,1);
    }

    public void klikla(View view) {soundPool.play(sound6,1,1,0,0,1);
    }

    public void kliksi(View view) {soundPool.play(sound7,1,1,0,0,1);
    }

    public void klikdo1(View view) {soundPool.play(sound8,1,1,0,0,1);
    }

    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool=null;

    }

}

