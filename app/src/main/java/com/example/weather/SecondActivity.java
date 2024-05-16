package com.example.weather;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weather.sampledata.DataModel;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private ListView listView;
    private List<DataModel> dataModels = new ArrayList<>();

    Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        dataModels.add(new DataModel("Title1", "This is Title1"));
        dataModels.add(new DataModel("Title2", "This is Title2"));

        goBack = findViewById(R.id.go_back);

        goBack.setOnClickListener(v -> {
            Intent goBackIntent = new Intent(SecondActivity.this, FirstActivity.class);
            startActivity(goBackIntent);
        });
    }
}
