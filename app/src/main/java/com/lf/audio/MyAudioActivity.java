package com.lf.audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.audio.AudioRecorder;
import com.lf.main.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyAudioActivity extends AppCompatActivity {

    //申请录音权限
    private static final int GET_RECODE_AUDIO = 1;

    private static String[] PERMISSION_ALL = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    private AudioRecorder audioRecorder;

    //1. MediaPlayer支持：AAC、AMR、FLAC、MP3、MIDI、OGG、PCM等格式
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_audio);

        audioRecorder = AudioRecorder.getInstance();

        verifyPermissions(this);

        Button startAudio = findViewById(R.id.start);
        Button goonAudio = findViewById(R.id.goon);
        Button pauseAudio = findViewById(R.id.pause);
        Button stopAudio = findViewById(R.id.stop);

        Button startPlay = findViewById(R.id.start_play);
        Button pausePlay = findViewById(R.id.pause_play);
        Button stopPlay = findViewById(R.id.stop_play);
        Button goonPlay = findViewById(R.id.goon_play);


        startAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audioRecorder.getStatus() == AudioRecorder.Status.STATUS_NO_READY) {
                    //初始化录音
                    String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    audioRecorder.createDefaultAudio(fileName);
                    audioRecorder.startRecord(null);

                }
            }
        });

        pauseAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //暂停录音
                audioRecorder.pauseRecord();
            }
        });

        goonAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecorder.startRecord(null);
            }
        });

        stopAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止录音
                audioRecorder.stopRecord();
            }
        });


        startPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        pausePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        goonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        stopPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    /** 申请录音权限*/
    public static void verifyPermissions(Activity activity) {
        boolean permission = (ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED);
        if (permission) {
            ActivityCompat.requestPermissions(activity, PERMISSION_ALL,
                    GET_RECODE_AUDIO);
        }
    }

}