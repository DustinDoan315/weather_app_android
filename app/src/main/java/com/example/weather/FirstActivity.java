package com.example.weather;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class FirstActivity extends AppCompatActivity {

    TextView editText;
    Button showInput;

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        editText = findViewById(R.id.editText);
        showInput = findViewById(R.id.showInput);

        showInput.setOnClickListener(v->{
            String text = editText.getText().toString();
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

            intent.putExtra("parentMess", text);
            startActivity(intent);
        });

    }



}
