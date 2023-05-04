package com.example.project6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    DownloadNasaDataTask01 downloadPages;
    CustomAdapter mAdapter;
    ListView mListView;
    Button dispButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new CustomAdapter(this, new ArrayList<Nasa>());
        mListView = findViewById(R.id.list_view);
        dispButton = findViewById(R.id.button);
        mListView.setAdapter(mAdapter);

        dispButton.setEnabled(false);


        downloadPages= new DownloadNasaDataTask01(mListView, dispButton);
        downloadPages.execute("https://nasasearch.nasa.gov/search?affiliate=nasa&page=6&query=%2A.jpg&sort_by=&utf8=%E2%9C%93");

    }
    public void viewImages(View view){
        Intent intent = new Intent(MainActivity.this, DisplayImages.class);
        startActivity(intent);
    }

}
