package foodyshop.com;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static foodyshop.com.App.CHANNEL_1_ID;
import static foodyshop.com.App.CHANNEL_2_ID;

public class NotificationService extends Service {
    private DatabaseReference testef;
    private NotificationManagerCompat notificationmanager;
    Timer timer;
    TimerTask timerTask;
    String TAG = "Timers";
    int Your_X_SECS = 1;
    Random random = new Random();
    String curUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        // initializeTimerTask();
        startTimer();

        return START_STICKY;
    }


    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");

        notificationmanager = NotificationManagerCompat.from(this);

    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        stoptimertask();
        super.onDestroy();


    }

    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();


    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, Your_X_SECS * 10); //
        //timer.schedule(timerTask, 5000,1000); //
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        testef = FirebaseDatabase.getInstance().getReference().child("users").child(curUid);
                        testef.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                int mi = random.nextInt(9999 - 1000) + 1000;
                                String name = dataSnapshot.getValue(String.class);
                                String as = dataSnapshot.child("diachi").getValue(String.class);
                                Notification notification = new NotificationCompat.Builder(App.getcontext(), CHANNEL_1_ID)
                                        .setSmallIcon(R.drawable.ic_launcher_background)
                                        .setContentTitle("co nguoi dat hang")
                                        .setContentText(name)
                                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                                        .setCategory(NotificationCompat.CATEGORY_EVENT)
                                        .setDefaults(Notification.DEFAULT_ALL)
                                        .build();

                                notificationmanager.notify(mi,notification);

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        //TODO CALL NOTIFICATION FUNC




                    }
                });
            }
        };
    }
}
