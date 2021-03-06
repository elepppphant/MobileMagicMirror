package com.example.lenveo.camerademo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.IOException;

/**
 * Created by Lenveo on 2017/12/3.
 */

public class MainActivity extends Activity{
    private  CameraPreview mPreview;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //  动态申请权限 CAMERA & WRITE_TXTERNAL_STORAGE
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0/*requestCode*/);
        }
        //while (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {}
        //isGrantExternalRW(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10/*requestCode*/);

        }

        setContentView(R.layout.activity_main);

        initCamera();
        /*
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        */
        final Button buttonCapturePhoto = (Button) findViewById(R.id.takepicture);
        buttonCapturePhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mPreview.takePicture();
            }
        });
        final Button buttonChangeCamera = (Button) findViewById(R.id.change);
        buttonChangeCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try{
                    mPreview.changeCamera();
                } catch (IOException e){
                    Log.e("MainActivity", "Change Camera Error");
                }

            }
        });

        final Button buttonPicture = (Button) findViewById(R.id.picture);
        buttonPicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initCamera(){

        mPreview = new CameraPreview(this );
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

    }

}
