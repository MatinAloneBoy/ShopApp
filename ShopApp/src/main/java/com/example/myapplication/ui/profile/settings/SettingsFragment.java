package com.example.myapplication.ui.profile.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentProfileBinding;
import com.example.myapplication.databinding.FragmentSettingsBinding;

import org.jetbrains.annotations.NotNull;


public class SettingsFragment extends Fragment {
    private @NonNull FragmentSettingsBinding binding;

    Button CNameButton,CPassButton,CPNButton,CProfilePhotoButton;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,container,false);
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        CNameButton=binding.ChangeNameButton;
        CPassButton=binding.ChangePasswordButton;
        CPNButton=binding.ChangePhoneNumberButton;
        CProfilePhotoButton=binding.ChangeProfilePhotoButton;


        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_home);
        CNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_changeNamePage);
            }
        });

        CPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_changePasswordInSetting);
            }
        });
        CPNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_change_PhoneNumber_Page);
            }
        });
        CProfilePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_changeProfilePhotoPage);
            }
        });

        return root;
    }
}