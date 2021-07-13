package com.example.myapplication.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.HomeActivity;
import com.example.myapplication.R;
import com.example.myapplication.database.ForgotPassDataBase;

import org.jetbrains.annotations.NotNull;

public class ForgetPassFragment extends Fragment {


    private Button sendEmailButton;
    private TextView ForgetEmailText,ForgotPassText;
    private EditText ForgetEmailBox,ForgotPassbox;
    ForgotPassDataBase fsd;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_pass,container,false);
        sendEmailButton=view.findViewById(R.id.forgotten_button);
        ForgetEmailText=view.findViewById(R.id.forgotten_email_text);
        ForgetEmailBox=view.findViewById(R.id.forgotten_email_edit_text);
        ForgotPassText=view.findViewById(R.id.new_password_text);
        ForgotPassbox=view.findViewById(R.id.new_password_edit_text);

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Sending Email", Toast.LENGTH_SHORT).show();
                fsd=new ForgotPassDataBase(getContext());
                fsd.readCourses(ForgetEmailBox.getText().toString());
                fsd.updateCourses(ForgotPassbox.getText().toString());
            }
        });

        return view;
    }
}
