package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;

public class Flag40Fragment extends Fragment {
    private static final String TAG = "Flag40Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag40, container, false);
        
        EditText editText = view.findViewById(R.id.editTextFlag40);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag40);
        
        launchButton.setOnClickListener(v -> {

        });

        return view;
    }
}