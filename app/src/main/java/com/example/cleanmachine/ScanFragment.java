package com.example.cleanmachine;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// fay added button
public class ScanFragment extends Fragment {
    View scanView;
    private EditText input;
    private TextView result;
    private int count = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        scanView = inflater.inflate(R.layout.fragment_scan, container, false);
        return scanView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input = scanView.findViewById(R.id.description);
        result = scanView.findViewById(R.id.result);
        Button btn = scanView.findViewById(R.id.btn);
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
                boolean technology = false;
                String userInput = input.getText().toString();
                String defaultResult = "Search to Find Out Which Recycling Bin To Use!";
                if (userInput.length() == 0) {
                    result.setText(defaultResult);
                }
                if (userInput.length() != 0) {
                    count++;
                    Bundle bundle = new Bundle();
                    bundle.putInt("entry",count);
                    HomeFragment fragment = new HomeFragment();
                    fragment.setArguments(bundle);
                }
                //use .split() to separate words
                String cantSort = "Please put the item in the landfill, it cannot be sorted!";
                String[] words = userInput.split(" ");
                //FIRST ORGANIZATION
                //category : metal/foil, paper/cardboard, plastic
                for (String word : words) {
                    if (word.equalsIgnoreCase("paper") || word.equalsIgnoreCase("cardboard")) {
                        paper = true;
                        landfill = false;
                    }
                    if (word.equalsIgnoreCase("plastic") || word.equalsIgnoreCase("bottle")) {
                        plastic = true;
                        landfill = false;
                    }
                    if (word.equalsIgnoreCase("metal") || word.equalsIgnoreCase("aluminium") || word.equalsIgnoreCase("can")) {
                        metal = true;
                        landfill = false;
                    }
                    if (word.equalsIgnoreCase("computer") || word.equalsIgnoreCase("tech") || word.equalsIgnoreCase("technology") || word.equalsIgnoreCase("phone") || word.equalsIgnoreCase("charger") || word.equalsIgnoreCase("wire")) {
                        technology = true;
                        landfill = false;
                    }
                }
                if (userInput.length() > 0 && landfill) {
                    result.setText(cantSort);
                }
                //SECOND ORGANIZATION
                // if paper
                // -> check for keywords "box" + "cardboard" = goes in separate cardboard dumpster
                // make String array
                //paper exceptions: paper towel, paper cup, tissues,
                if (paper) {
                    boolean cardboard = false;
                    boolean dirty = false;

                    for (String word : words) {
                        if (word.equalsIgnoreCase("oily") || word.equalsIgnoreCase("food") || word.equalsIgnoreCase("dirty") || word.equalsIgnoreCase("cup") || word.equalsIgnoreCase("tissue") || word.equalsIgnoreCase("towel") || word.equalsIgnoreCase("toilet") || word.equalsIgnoreCase("receipt") || word.equalsIgnoreCase("carton") || word.equalsIgnoreCase("wet") || word.equalsIgnoreCase("pizza") || word.equalsIgnoreCase("plate")) {
                            dirty = true;
                        }
                        if (word.equalsIgnoreCase("box") || word.equalsIgnoreCase("cardboard")) {
                            cardboard = true;
                        }
                    }
                    if (dirty) {
                        result.setText(cantSort);
                    } else {
                        if (cardboard) {
                            result.setText("Please put the item in a separate designated cardboard dumpster, not the recycling bins!");
                        } else {
                            result.setText("Put this object in the designated paper recycling bin!");
                        }
                    }
                }

                // -> check for "book", "newspaper", "magazine", "journal", "mail", "envelope", "folder"
                //if metal
                if (metal) {
                    boolean can = false;
                    for (String word : words) {
                        if (word.equalsIgnoreCase("aluminium") || word.equalsIgnoreCase("can")) {
                            can = true;
                            break;
                        }
                    }
                    if (can) {
                        result.setText("Put the object in the designated can/bottle recycling bin if it is rinsed and clean!");
                    } else {
                        result.setText(cantSort);
                    }
                }
                // check for "aluminium"
                // NO SOUP CANS, YES SODA CANS : watch for brands
                // else put in landfill
                // check glass bottles such as beer bottles and hot sauce bottles
                if (plastic) {
                    boolean bottle = false;
                    for (String word : words) {
                        if (word.equalsIgnoreCase("bottle")) {
                            bottle = true;
                        } else {
                            result.setText(cantSort);
                        }
                    }
                    if (bottle) {
                        result.setText("Only Type 1 or 2 bottles can be placed in the designated can/bottle bin. Please check the type and place your clean bottle in the bin!");
                    } else {
                        result.setText(cantSort);
                    }
                }
                // if plastic
                // -> check for "bottle"
                // find some way to check if it is #1 or #2 type bottle
                // else landfill
                if (technology) {
                    result.setText("Do NOT throw away this item in the landfill. Find a e-waste recycling location!");
                }

            }
        });
    }
}
