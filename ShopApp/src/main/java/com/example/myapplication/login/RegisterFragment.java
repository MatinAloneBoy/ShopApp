package com.example.myapplication.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.myapplication.database.Room.User.User;
import com.example.myapplication.database.Room.User.product.Product;
import com.example.myapplication.database.repository.Repository;
import com.example.myapplication.database.repository.RepositoryCallback;
import com.example.myapplication.database.repository.Result;
import com.example.myapplication.userUi.home.HomeBottomActivity;
import com.example.myapplication.R;
import com.example.myapplication.database.UsersDataBase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RegisterFragment extends Fragment {
    private Button registerButton;
    private TextView TitleText,EmailText,PasswordText,UsernameText,UserModeText,PhoneNumberText;
    private EditText EmailBox,PassBox,UnBox,PNBox;
    private Switch UserModeSwitch;
    String type=new String();
    UsersDataBase us;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register,container,false);
        //TitleText=view.findViewById(R.id.registeration_title_text);
        EmailText=view.findViewById(R.id.new_email_text);
        PasswordText=view.findViewById(R.id.new_pass_text);
        UsernameText=view.findViewById(R.id.new_username_text);
        PhoneNumberText=view.findViewById(R.id.new_phoneNumber_text);
        EmailBox=view.findViewById(R.id.new_email_edit_text);
        PassBox=view.findViewById(R.id.new_pass_edit_text);
        UnBox=view.findViewById(R.id.new_username_edit_text);
        PNBox=view.findViewById(R.id.new_phoneNumber_edit_text);
        registerButton=view.findViewById(R.id.register_button);

        RepositoryCallback<Void> callback =new RepositoryCallback<Void>() {
            @Override
            public void onComplete(Result<Void> result) {
                if(result instanceof Result.Success){
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }
        };






        us=new UsersDataBase(getContext());
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Registered", Toast.LENGTH_SHORT).show();
                us.register_user(UnBox.getText().toString().trim(),
                        EmailBox.getText().toString().trim(),PassBox.getText().toString().trim(),
                        type,PNBox.getText().toString().trim(),
                        "https://www.seekpng.com/ima/u2q8w7r5u2o0o0i1/");




                List<User>userList = null;
                Repository.getInstance(getContext()).getAllUsers(new RepositoryCallback<List<User>>() {
                    @Override
                    public void onComplete(Result<List<User>> result) {
                        if(result instanceof Result.Success){
                            userList.addAll(((Result.Success<List<User>>)result).data);
                        }
                    }
                });





                User user= new User(UnBox.getText().toString().trim(),EmailBox.getText().toString().trim(),
                        PassBox.getText().toString().trim(),PNBox.getText().toString().trim(),
                        "https://www.seekpng.com/ima/u2q8w7r5u2o0o0i1/");

//                if(userList.contains(user)){
//                    Toast.makeText(getContext(), "Already Exists", Toast.LENGTH_SHORT).show();
//                }else {
                    Repository.getInstance(getContext()).insertUser(user,callback);
                    Context context = getActivity();
                    SharedPreferences sharedPref = context.getSharedPreferences(
                            getString(R.string.profile_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(String.valueOf(R.string.profile_name_key),UnBox.getText().toString());
                    editor.putString(String.valueOf(R.string.profile_email_key),EmailBox.getText().toString());
                    editor.putString(String.valueOf(R.string.profile_type_key),type);
                    editor.putString(String.valueOf(R.string.profile_phone_number_key), PNBox.getText().toString());
                    editor.apply();
                    Intent intent= new Intent(getContext(), HomeBottomActivity.class);
                    startActivity(intent);
//                }

            }
        });


        return view;
    }
}
