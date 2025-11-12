package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    EditText riddleText;
    EditText answerText;
    String gameResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_practical_test01_var08_main);

        riddleText = findViewById(R.id.riddle);
        answerText = findViewById(R.id.answer);

        Button play = findViewById(R.id.play);
        play.setOnClickListener(v -> {
            if (riddleText.getText().length() > 0 && answerText.getText().length() > 0) {
                String riddle = riddleText.getText().toString();
                String answer = answerText.getText().toString();

                Intent intent = new Intent(this, PracticalTest01Var08PlayActivity.class);
                intent.putExtra("ro.pub.cs.systems.eim.practicaltest01.RIDDLE", riddle);
                intent.putExtra("ro.pub.cs.systems.eim.practicaltest01.ANSWER", answer);

                startActivityForResult(intent, Constants.SECOND_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == Constants.SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Constants.RESULT_CORRECT) {
                Toast.makeText(this, "Victory", Toast.LENGTH_SHORT).show();
                gameResult = "Victory";
            } else {
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                gameResult = "Fail";
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        if (gameResult != null) {
            savedInstanceState.putString("GAME_RESULT", gameResult);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gameResult = savedInstanceState.getString("GAME_RESULT");
        if (gameResult != null) {
            Toast.makeText(this, gameResult, Toast.LENGTH_SHORT).show();
        }

    }
}