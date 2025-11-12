package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PracticalTest01Var08BroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.w(Constants.TAG, "onReceive called with action: " + action);

        String hint = String.valueOf(intent.getCharArrayExtra("ro.pub.cs.systems.eim.practicaltest01.HINT"));
        Toast.makeText(context, hint, Toast.LENGTH_SHORT).show();
        // adb shell am broadcast -a ro.pub.cs.systems.eim.practicaltest01var08.intent.TEST --es sms_body "test from adb"
    }
}
