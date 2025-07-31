package com.example.android15demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android15demo.test1.TestActivity1;
import com.example.android15demo.test2.TestActivity2;
import com.example.android15demo.test3.TestActivity3;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onTest1(View v) {
        Intent intent = new Intent(this, TestActivity1.class);
        startActivity(intent);
    }

    public void onTest2(View v) {
        Intent intent = new Intent(this, TestActivity2.class);
        startActivity(intent);
    }

    public void onTest3(View v) {
        Intent intent = new Intent(this, TestActivity3.class);
        startActivity(intent);
    }

}