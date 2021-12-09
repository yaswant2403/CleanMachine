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
    View v;
    private TextView entry;

    public static HomeFragment newInstance(int total) {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        args.putInt("entryTotal",total);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BottomNavigationView bottomNav = requireActivity().findViewById(R.id.bottom_navigation);
        MainActivity.showBottomNav(bottomNav);
        v = inflater.inflate(R.layout.fragment_home,container,false);
        entry = v.findViewById(R.id.entryText);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            count = getArguments().getInt("entryTotal");
        }
        String s = "You've recorded no entries!";
        entry.setText(String.valueOf(count));
        if (count == 0) {
            entry.setText(s);
        } else {
            s = "You've recorded " + count + " entries!";
        }
        //entry.setText(s);
    }
}
