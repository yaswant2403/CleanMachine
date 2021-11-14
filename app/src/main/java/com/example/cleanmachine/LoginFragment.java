package com.example.cleanmachine;

import android.content.Intent;
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

public class LoginFragment extends Fragment {
    View loginLayout;
    private EditText uiName;
    private EditText uiPassword;
    private Button uiLogin;

    private String setUser = "Admin";
    private String setPassword = "12345";
    private boolean isValid = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginLayout = inflater.inflate(R.layout.fragment_login,container,false);
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
                }
                Intent intent = new Intent(getActivity(), HomeFragment.class);
                startActivity(intent);

            }
        });
    }

    public boolean validate(String n, String p) {
        if (n.equals(setUser) && p.equals(setUser)) {
            return true;
        }
        return false;
    }

}

