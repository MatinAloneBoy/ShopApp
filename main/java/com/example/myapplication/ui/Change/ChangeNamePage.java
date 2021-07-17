package com.example.myapplication.ui.Change;

import android.content.Context;
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

import com.example.myapplication.R;
import com.example.myapplication.database.UpdateProfileDataBase;

import org.jetbrains.annotations.NotNull;

public class ChangeNamePage extends Fragment {


    private Button sendNameButton;
    private TextView newNameText;
    private EditText newNameBox;
    UpdateProfileDataBase upd;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_pass,container,false);
        sendNameButton=view.findViewById(R.id.ChangePhoneNumber_button);
        newNameText=view.findViewById(R.id.new_PhoneNumber_to_change_text);
        newNameBox=view.findViewById(R.id.new_phoneNumber_to_change_edit_text);
        String mail;
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        mail=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");
        sendNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Sending Email", Toast.LENGTH_SHORT).show();
                upd=new UpdateProfileDataBase(getContext());
                upd.updatePhoneNumber(newNameBox.getText().toString(),mail);
            }
        });

        return view;
    }
}
