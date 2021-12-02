package com.example.cleanmachine;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
// fay added button
public class ScanFragment extends Fragment {
    Button btn;
    View view;
    //set textviews in layout!!
    TextView explain;
    TextView example;
    TextView category;
    TextView reminder;
    EditText description;
    LinearLayout linearLayout;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scan, container, false);
        explain = (TextView) view.findViewById(R.id.explain);
        example = (TextView) view.findViewById(R.id.example);
        category = (TextView) view.findViewById(R.id.category);
        reminder = (TextView) view.findViewById(R.id.reminder);
        description = (EditText) view.findViewById(R.id.description);
        btn = (Button) view.findViewById(R.id.btn);
        linearLayout = (LinearLayout) view.findViewById(R.id.linLayout);
        //text views explain the identification feature
        //text inputs allow user to describe the object
        //btn takes in text input
        //fay's algorithm
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick store data
                String input = description.getText().toString();
                //use .split() to separate words
                String[] words = input.split(",");
                //FIRST ORGANIZATION
                //category : cartons, glass, metal/foil, paper/cardboard, plastic
                for (int i = 0; i < words.length; i++) {

                }
                //SECOND ORGANIZATION
                // if paper
                // -> check for keywords "box" + "cardboard" = goes in separate cardboard dumpster
                // make String array
                // -> check for "book", "newspaper", "magazine", "journal", "mail", "envelope", "folder"
                //if metal
                // check for "aluminium"
                // else put in landfill
                // if plastic
                // -> check for "bottle"
                // find some way to check if it is #1 or #2 type bottle
                // else landfill
            }
        });
        // tv remind user to clean all bottles or cans before dropping it in bin (metal+plastic)
        // "exceptions" (automatic landfill words) -> "oily" (not cleanable usually) + "technology" (does not belong in any category) + "glass" + "tissue"
        return view;
    }
}
