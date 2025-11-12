package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var08PlayActivity extends AppCompatActivity {

    String correctAnswer = null;

    boolean startedService = false;

    PracticalTest01Var08BroadcastReceiver broadcastReceiver;

    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_play);

        TextView riddleText = findViewById(R.id.riddle);
        EditText answerText = findViewById(R.id.answer);

        Button check = findViewById(R.id.check);
        check.setOnClickListener(v -> {
            String answer = String.valueOf(answerText.getText());

            if (!answer.isEmpty() && answer.equals(correctAnswer)) {
                setResult(Constants.RESULT_CORRECT, new Intent());
                finish();
            } else {
                setResult(Constants.RESULT_WRONG, new Intent());
                finish();
            }
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            setResult(Constants.RESULT_WRONG, new Intent());
            finish();
        });

        Intent intent = getIntent();
        if (intent != null) {
            String riddle = intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01.RIDDLE");
            riddleText.setText(riddle);

            correctAnswer = intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01.ANSWER");

            broadcastReceiver = new PracticalTest01Var08BroadcastReceiver();

            intentFilter = new IntentFilter();
            intentFilter.addAction(Constants.ACTION);
            intentFilter.addAction(Constants.ADB_BROADCAST);

            if (!startedService) {
                Intent serviceIntent = new Intent(this, PracticalTest01Var08Service.class);
                serviceIntent.putExtra("ro.pub.cs.systems.eim.practicaltest01.ANSWER", correctAnswer);

                startedService = true;
                startService(serviceIntent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PracticalTest01Var08Service.class));
        startedService = false;

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter,
                    Context.RECEIVER_EXPORTED);
        }
    }
}