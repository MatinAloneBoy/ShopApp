package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.myapplication.database.ChangeNameDataBase;
import com.example.myapplication.database.ForgotPassDataBase;

import org.jetbrains.annotations.NotNull;

public class ChangeNamePage extends Fragment {


    private Button ChangeButton;
    private TextView NameText;
    private EditText NameBox;
    ChangeNameDataBase cnd;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_name_page,container,false);
        ChangeButton=view.findViewById(R.id.ChangeNamePage_button);
        NameBox=view.findViewById(R.id.new_name_to_change_edit_text);
        String email;
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        email=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");

        ChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Sending Email", Toast.LENGTH_SHORT).show();
                cnd=new ChangeNameDataBase(getContext());
                cnd.updateCourses(NameBox.getText().toString(),email);
            }
        });

        return view;
    }
}
