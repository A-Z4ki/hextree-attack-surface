package com.appdev.intentattacks;

import android.content.BroadcastReceiver;
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
import androidx.fragment.app.Fragment;

public class Flag21Fragment extends Fragment {

    private static final String ACTION_FLAG21 = "io.hextree.broadcast.GIVE_FLAG";
    private static final String TAG = "Flag21Receiver";

    private BroadcastReceiver receiver;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag21, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag21);

        // Define the broadcast receiver
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "Received broadcast with action: " + intent.getAction());
                Utils.showDialog(context, intent);
            }
        };

        IntentFilter filter = new IntentFilter(ACTION_FLAG21);
        // Optional: button behavior
        launchButton.setOnClickListener(v -> {
            Log.d(TAG, "Launch button clicked. Waiting for broadcast...");
            requireActivity().registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED);
            Toast.makeText(requireContext(), "Click on the GIVE FLAG notification button", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (receiver != null) {
            requireActivity().unregisterReceiver(receiver);
        }
    }
}