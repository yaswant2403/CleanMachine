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
                boolean landfill = true;
                boolean paper = false;
                boolean metal = false;
                boolean plastic = false;
                String input = description.getText().toString();
                //use .split() to separate words
                String[] words = input.split(",");
                //FIRST ORGANIZATION
                //category : metal/foil, paper/cardboard, plastic
                for (int i = 0; i < words.length; i++) {
                    if (words[i] == "paper" || words[i] == "cardboard") {
                        paper = true;
                        landfill = false;
                    }
                    if (words[i] == "plastic") {
                        plastic = true;
                        landfill = false;
                    }
                    if (words[i] == "metal" || words[i] == "aluminium" ) {
                        metal = true;
                        landfill = false;
                    }
                }
                if (landfill) {
                    description.setText("please put the item in the landfill, it cannot be sorted");
                }
                //SECOND ORGANIZATION
                // if paper
                // -> check for keywords "box" + "cardboard" = goes in separate cardboard dumpster
                // make String array
                if (paper) {
                    boolean cardboard = false;
                    boolean regular = false;
                    boolean dirty = false;

                    for (int j = 0; j < words.length; j++) {
                        if (words[j] == "box" || words[j] == "cardboard" ) {
                            cardboard = true;
                        }
                        if (words[j] == "book" || words[j] == "newspaper" || words[j] == "magazine"|| words[j] == "journal" || words[j] == "mail" || words[j] == "folder" ) {
                            regular = true;
                        }
                        if (words[j] == "oily" || words[j] == "food") {
                            dirty = true;
                        }
                    }
                    if (dirty) {
                        description.setText("please put the item in the landfill, it cannot be sorted");
                    } else {
                        if (cardboard) {
                            description.setText("please put the item in a separate designated cardboard dumpster, not the recycling bins");
                        }
                        if (regular) {
                            description.setText("you may put the object in the designated paper recycling bin");
                        }
                    }
                }

                // -> check for "book", "newspaper", "magazine", "journal", "mail", "envelope", "folder"
                //if metal
                if (metal) {
                    boolean can = false;
                    for (int x = 0; x < words.length; x++) {
                        if (words[x] == "aluminium" || words[x] == "can" ) {
                            can = true;
                        }
                    }
                    if (can) {
                        description.setText("you may put the object in the designated can/bottle recycling bin if it is rinsed and clean");
                    } else {
                        description.setText("please put the item in the landfill, it cannot be sorted");
                    }
                }
                // check for "aluminium"
                // else put in landfill
                if (plastic) {
                    boolean bottle = false;
                    for (int x = 0; x < words.length; x++) {
                        if (words[x] == "bottle") {
                            bottle = true;
                        } else {
                            description.setText("please put the item in the landfill, it cannot be sorted");
                        }
                    }
                    if (bottle) {
                        description.setText("only type 1 or 2 bottles can be placed in the designated can/bottle bin, please check the type and place your clean bottle in the bin");
                    } else {
                        description.setText("please put the item in the landfill, it cannot be sorted");
                    }
                }
                // if plastic
                // -> check for "bottle"
                // find some way to check if it is #1 or #2 type bottle
                // else landfill
            }
        });
        return view;
    }
}
