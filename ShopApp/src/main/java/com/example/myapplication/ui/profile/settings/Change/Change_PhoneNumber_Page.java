package com.example.myapplication.ui.profile.settings.Change;

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
import com.example.myapplication.databinding.FragmentChangePhoneNumberPageBinding;
import com.example.myapplication.databinding.FragmentProfileBinding;

import org.jetbrains.annotations.NotNull;

public class Change_PhoneNumber_Page extends Fragment {


    private Button sendPhoneButton;
    private TextView newPhoneText;
    private EditText newPhoneBox;
    UpdateProfileDataBase upd;
    private @NonNull FragmentChangePhoneNumberPageBinding binding;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change__phone_number__page,container,false);

        binding = FragmentChangePhoneNumberPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        sendPhoneButton=binding.ChangePhoneNumberButton;
        newPhoneText=binding.newPhoneNumberToChangeText;
        newPhoneBox=binding.newPhoneNumberToChangeEditText;
        String mail;
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        mail=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");
        sendPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Sending Email", Toast.LENGTH_SHORT).show();
                upd=new UpdateProfileDataBase(getContext());
                upd.updatePhoneNumber(newPhoneBox.getText().toString(),mail);
            }
        });

        return root;
    }
}
