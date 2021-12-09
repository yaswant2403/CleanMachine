package com.example.cleanmachine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;


public class HomeFragment extends Fragment {

    private int count;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BottomNavigationView bottomNav = requireActivity().findViewById(R.id.bottom_navigation);
        MainActivity.showBottomNav(bottomNav);
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        TextView entry = v.findViewById(R.id.entryText);
        if (getArguments() != null) {
            count = getArguments().getInt("totalEntry");
        }
        String s = "You've recorded no entries!";
        entry.setText(s);
        if (count == 0) {
            entry.setText(s);
        } else {
            s = "You've recorded " + count + " entries!";
        }
        entry.setText(s);
        return v;
    }
}
