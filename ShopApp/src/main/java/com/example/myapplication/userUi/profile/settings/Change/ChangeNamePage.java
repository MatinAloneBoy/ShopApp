package com.example.myapplication.userUi.profile.settings.Change;

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

import com.example.myapplication.R;
import com.example.myapplication.database.Room.User.User;
import com.example.myapplication.database.repository.Repository;
import com.example.myapplication.database.repository.RepositoryCallback;
import com.example.myapplication.database.repository.Result;
import com.example.myapplication.databinding.FragmentChangeNamePageBinding;
import com.example.myapplication.userUi.home.HomeBottomActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChangeNamePage extends Fragment {


    private Button sendNameButton;
    private TextView newNameText;
    private EditText newNameBox;
    private @NonNull FragmentChangeNamePageBinding binding;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        binding = FragmentChangeNamePageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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


        sendNameButton=binding.ChangeNamePageButton;
        newNameText=binding.newNameToChangeText;
        newNameBox=binding.newNameToChangeEditText;
        String mail;
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        mail=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");
        SharedPreferences.Editor editor = sharedPref.edit();


        binding.ChangeNamePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Name Changed", Toast.LENGTH_SHORT).show();

                for (User user:users){
                    if (user.email.equals(mail)){
                        user.name=newNameBox.getText().toString();
                    }
                }
                editor.putString(String.valueOf(R.string.profile_name_key),newNameBox.getText().toString());
                editor.apply();
                Intent intent= new Intent(getContext(), HomeBottomActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
}
