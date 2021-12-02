package com.example.cleanmachine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class LoginFragment extends Fragment {
    View loginLayout;
    private EditText uiName;
    private EditText uiPassword;
    private Button uiLogin;

    private final String setUser = "UIUC";
    private final String setPassword = "goIllini";
    private boolean isValid = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginLayout = inflater.inflate(R.layout.fragment_login,container,false);
        BottomNavigationView bottomNav = requireActivity().findViewById(R.id.bottom_navigation);
        MainActivity.hideBottomNav(bottomNav);
        return loginLayout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // do your variables initialisations here except Views!!!
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // initialise your views
        uiLogin = (Button)loginLayout.findViewById(R.id.loginButton);
        uiName = (EditText)loginLayout.findViewById(R.id.usernameInput);
        uiPassword = (EditText)loginLayout.findViewById(R.id.passwordInput);
        uiLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = uiName.getText().toString();
                String inputPass = uiPassword.getText().toString();
                isValid = validate(inputName, inputPass);
                if (!isValid) {
                    //get Activity instead of this bc it's a fragment
                    Toast.makeText(getActivity(), "Incorrect Credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Successful Login!", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new HomeFragment()).commit();
                }
            }
        });
    }

    public boolean validate(String n, String p) {
        return n.equals(setUser) && p.equals(setPassword);
    }

}

