package com.example.myapplication.login;

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
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.database.Room.User.User;
import com.example.myapplication.database.repository.Repository;
import com.example.myapplication.database.repository.RepositoryCallback;
import com.example.myapplication.database.repository.Result;
import com.example.myapplication.databinding.FragmentChangePasswordInSettingBinding;
import com.example.myapplication.databinding.FragmentForgetPassBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ForgetPassFragment extends Fragment {


    private Button sendEmailButton;
    private TextView ForgetEmailText,ForgotPassText;
    private EditText ForgetEmailBox,ForgotPassbox;
    private @NonNull
    FragmentForgetPassBinding binding;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_pass,container,false);
        binding = FragmentForgetPassBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sendEmailButton=view.findViewById(R.id.forgotten_button);
        ForgetEmailText=view.findViewById(R.id.forgotten_email_text);
        ForgetEmailBox=view.findViewById(R.id.forgotten_email_edit_text);
        ForgotPassText=view.findViewById(R.id.new_password_text);
        ForgotPassbox=view.findViewById(R.id.new_password_edit_text);

        List<User> users=new ArrayList<>();

        Repository.getInstance(getContext()).getAllUsers(new RepositoryCallback<List<User>>() {
            @Override
            public void onComplete(Result<List<User>> result) {
                if(result instanceof Result.Success){
                    users.addAll(((Result.Success<List<User>>)result).data);

                }else if(result instanceof Result.Error){
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Password Changed", Toast.LENGTH_SHORT).show();
                for (User user:users){
                    if (user.email.equals(binding.forgottenEmailEditText.getText().toString())){
                        user.password=binding.newPasswordEditText.getText().toString();
                    }
                }
                Navigation.findNavController(v).navigate(R.id.action_forgetPassFragment_to_loginFragment);
            }
        });

        return root;
    }
}
