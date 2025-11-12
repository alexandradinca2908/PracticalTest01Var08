package ro.pub.cs.systems.eim.practicaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var08Service extends Service {
    ProcessingThread thread;
    public PracticalTest01Var08Service() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constants.TAG, "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constants.TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Constants.TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "onStartCommand() method was invoked");

        String answer = intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01.ANSWER");
        Log.d(Constants.TAG, answer);

        thread = new ProcessingThread(this, answer.toCharArray());
        thread.start();

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        thread.running = false;
        super.onDestroy();
    }
}