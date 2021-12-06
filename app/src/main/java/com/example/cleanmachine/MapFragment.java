package com.example.cleanmachine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MapFragment extends Fragment {

    private ImageView img;
    private Spinner spinner;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map,container,false);
        spinner = view.findViewById(R.id.dropdown_menu);
        String[] locations = {"Select the closest location to you!", "Loomis Laboratory", "PAR/FAR", "Siebel Center", "UGL Library", "CS Department", "Union"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, locations);
        spinner.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img = view.findViewById(R.id.mapImg);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        img.setImageResource(R.drawable.loomis);
                        break;
                    case 2:
                        img.setImageResource(R.drawable.par);
                        break;
                    case 3:
                        img.setImageResource(R.drawable.siebelcenter);
                        break;
                    case 4:
                        img.setImageResource(R.drawable.uglibrary);
                        break;
                    case 5:
                        img.setImageResource(R.drawable.departmentofcomputerscience);
                        break;
                    case 6:
                        img.setImageResource(R.drawable.bookstore);
                        break;
                }
            }
        });
    }
}
