package com.example.myapplication.userUi.profile.settings.Change;

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
import com.example.myapplication.databinding.FragmentChangePasswordInSettingBinding;

import org.jetbrains.annotations.NotNull;

public class ChangePasswordInSetting extends Fragment {


    private Button sendPassButton;
    private TextView newPassText;
    private EditText newPassBox;
    UpdateProfileDataBase upd;
    private @NonNull FragmentChangePasswordInSettingBinding binding;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password_in_setting,container,false);

        binding = FragmentChangePasswordInSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        sendPassButton=binding.ChangePasswordPageButton;
        newPassText=binding.newPasswordToChangeText;
        newPassBox=binding.newPasswordToChangeEditText;
        String mail;
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        mail=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");


        sendPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Sending Email", Toast.LENGTH_SHORT).show();
                upd=new UpdateProfileDataBase(getContext());
                upd.updatePhoneNumber(newPassBox.getText().toString(),mail);
            }
        });

        return view;
    }
}
