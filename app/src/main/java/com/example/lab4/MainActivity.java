package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
    }

    class ExampleRunnable implements Runnable {
        @Override
        public void run() {
            mockFileDownloader();
        }
    }

    public void startDownload(View view) {
        ExampleRunnable runnable = new ExampleRunnable();
        new Thread(runnable).start();
    }

    public void mockFileDownloader() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startButton.setText("DOWNLOADING...");
            }
        });

        for (int i = 0; i <= 100; i = i + 10) {
            Log.d(TAG, "Download Progress: " + i + "%");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startButton.setText("Start");
            }
        });
    }
}