package com.example.user.android_assignment_17_1;
//Package objects contain version information about the implementation and specification of a Java package.
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MainActivity and extends with AppCompatActivity which is Parent class that implements the on clicl listner method.

    Button startButton, stopButton;
    MediaPlayer myPlayer;
    NotificationCompat.Builder builder;
    //Intializing the variables.

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onCreate(Bundle savedInstanceState) {
        //protected can be accessed by within the package and class and subclasses
        //The Void class is an uninstantiable placeholder class to hold a reference to the Class object
        //representing the Java keyword void.
        //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
        // Activities have the ability, under special circumstances, to restore themselves to a previous state using the data stored in this bundle.
        super.onCreate(savedInstanceState);
        //Android class works in same.You are extending the Activity class which have onCreate(Bundle bundle) method in which meaningful code is written
        //So to execute that code in our defined activity. You have to use super.onCreate(bundle)
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design

        startButton = (Button) findViewById(R.id.start_button);
        //giving id as start_button assigning to button.
        Intent intent= new Intent(MainActivity.this,MainActivity.class);
        //creating intent which links the music class with the main activity.
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,1,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        //By giving a PendingIntent to another application, you are granting it the right to perform the operation you have specified as if the other application was yourself (with the same permissions and identity).

        builder= new NotificationCompat.Builder(MainActivity.this);
        builder.setContentTitle("music player");
        //Set the first line of text in the platform notification template.
        builder.setContentText("playing music");
        //Set the second line of text in the platform notification template.
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //Set the small icon resource, which will be used to represent the notification in the status bar.
        builder.setContentIntent(pendingIntent);
        //Supply a PendingIntent to be sent when the notification is clicked.
        myPlayer = MediaPlayer.create(MainActivity.this,R.raw.dil);
        if(myPlayer.isPlaying()){
            Toast.makeText(this, "playing.....", Toast.LENGTH_SHORT).show();
            //giving a message that when music is started it shows playing.
        };
        myPlayer.setLooping(true);
        stopButton = (Button) findViewById(R.id.stop_button);
        //setting onclick listener by clicking start button to it displays the toast of the particular action
        startButton.setOnClickListener(MainActivity.this);
        //setting onclick listener by clicking stop button to it displays the toast of the particular action
        stopButton.setOnClickListener(MainActivity.this);
    }
    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public void onClick(View v) {
        //implementing onClick view method
        switch (v.getId()) {
            case R.id.start_button:
                // Play audio from the start service intent of context MyMusic.class
                // Set looping
                myPlayer = MediaPlayer.create(MainActivity.this,R.raw.dil);
                myPlayer.setLooping(true);
                //setting loop as true.
                myPlayer.start();
                //starting the mplayer.
                NotificationManager notificationManager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                //creating the new object as notification
                notificationManager.notify(2,builder.build());
                Toast.makeText(this, " Music Started", Toast.LENGTH_LONG).show();
                //showing that music is started

                break;
                //breaking the case
            case R.id.stop_button:
                // Pause audio
                myPlayer.stop();
                myPlayer.setLooping(false);
                //if loop sets to false
                Toast.makeText(this, " Music Stopped", Toast.LENGTH_LONG).show();
                //showing a message that music is stopped as loop becomes false.
                break;
        }
    }



    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onStop() {
        //Called when the activity is no longer visible to the user, because another activity has been resumed and is covering this one.
        super.onStop();
        startService(new Intent(this,Music.class));
    }

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    public void onPointerCaptureChanged(boolean hasCapture) {
        //Called when pointer capture is enabled or disabled for the current window.

    }
}
