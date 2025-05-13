package com.appdev.intentattacks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Flag10Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag10, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag10);
        launchButton.setOnClickListener(v -> {
            // TODO: Add flag10 exploit code here
        });
        return view;
    }
} 