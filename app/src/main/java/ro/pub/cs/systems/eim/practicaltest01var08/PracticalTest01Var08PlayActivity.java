package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
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
        }
    }
}