package com.example.vivekgandhi.bookstoreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void insertData(View v)
    {
        Intent i = new Intent(this,InsertActivity.class);
        startActivity(i);
    }
    public void displayData(View v)
    {
        Intent i = new Intent(this,DisplayActivity.class);
        startActivity(i);
    }
}
