package com.example.weather;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView receiveText;

    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        receiveText = findViewById(R.id.received_value_id);
        goBack = findViewById(R.id.go_back);


        Intent intent = getIntent();
        String text = intent.getStringExtra("parentMess");
        receiveText.setText("Hello " + text);


        goBack.setOnClickListener(v -> {
//            Intent goBackIntent = new Intent(SecondActivity.this, FirstActivity.class);
//            startActivity(goBackIntent);
            ToastUtil.showCustomToast(SecondActivity.this,"This is a custom toast message!");
        });



    }
}
