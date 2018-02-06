 package com.example.user.android_assignment_17_1;
//Package objects contain version information about the implementation and specification of a Java package.
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

 public class Music extends Service {
     //public is a method and fields can be accessed by the members of any class.
     //class is a collections of objects.
     //created Music class and extends with Service.

    MediaPlayer myPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //Return an IBinder through which clients can call on to the service.
    //This value  be null.
    //Unless you provide binding for your service, you don't need to implement this method, because the default implementation returns null.
    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public void onCreate() {
        //Called by the system when the service is first created.
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
        //showing a message that service is created.
        myPlayer = MediaPlayer.create(Music.this,R.raw.dil);
        if(myPlayer.isPlaying()){
            Toast.makeText(this, "playing.....", Toast.LENGTH_SHORT).show();
            //showing the service that it is playing.
        };
        myPlayer.setLooping(true);
        // Set looping
    }


    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Called by the system every time a client explicitly starts the service by calling startService(Intent),
        // providing the arguments it supplied and a unique integer token representing the start request.
        myPlayer.start();
        //starts the music player
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public void onDestroy() {
        //Called by the system to notify a Service that it is no longer used and is being removed.
        myPlayer.stop();

    }

}