package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {
    private final Context context;
    private final char[] answer;

    public boolean running;
    public ProcessingThread(Context context, char[] answer) {
        this.answer = answer;
        this.context = context;
        this.running = true;
    }

    @Override
    public void run() {
        Log.d(Constants.TAG, "Thread.run() was invoked, PID: "
                + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());

        while (running) {
            sendMessage();
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(Constants.SLEEP_TIME);

        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION);

        int shownLetter = (int) (Math.random() * (answer.length - 1));
        for (int i = 0; i < answer.length; i++) {
            if (i != shownLetter) {
                answer[i] = '*';
            }
        }

        intent.putExtra("ro.pub.cs.systems.eim.practicaltest01.HINT", answer);

        context.sendBroadcast(intent);
    }
}
