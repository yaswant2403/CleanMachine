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


public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BottomNavigationView bottomNav = requireActivity().findViewById(R.id.bottom_navigation);
        MainActivity.showBottomNav(bottomNav);
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        TextView entry = v.findViewById(R.id.entryText);
        String s = "You've recorded no entries!";
        entry.setText(s);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int count = bundle.getInt("entry");
            if (count == 0) {
                entry.setText(s);
            } else {
                s = "You've recorded " + count + " entries!";
            }
            entry.setText(s);
        }
        return v;
    }
}
