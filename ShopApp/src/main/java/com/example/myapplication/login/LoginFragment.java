package com.example.myapplication.login;


import android.app.Activity;
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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.userUi.home.HomeBottomActivity;
import com.example.myapplication.R;
import com.example.myapplication.database.UsersDataBase;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment {


    private Button login;
    private EditText email_edit_text,password_edit_text;
    private TextView forgetPass,error_text,register_text;
    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    UsersDataBase us;


    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                        handleSignInResult(task);
                    }
                }
            });
    private FragmentLoginBinding binding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_login,container,false);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        login =view.findViewById(R.id.login);
        email_edit_text=view.findViewById(R.id.edit_text_email);
        password_edit_text=view.findViewById(R.id.edit_text_pass);
        forgetPass=view.findViewById(R.id.forgot_Text);
        error_text=view.findViewById(R.id.error_text);
        signInButton= view.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_ICON_ONLY);
        register_text=view.findViewById(R.id.new_account);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        register_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_forgetPassFragment);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        updateUI(account);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        mStartForResult.launch(signInIntent);
        //startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account!=null){
            String personPhoto;
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            if(!account.getPhotoUrl().equals(null)){
                personPhoto = account.getPhotoUrl().toString();

            }else {
                personPhoto ="https://www.pngkey.com/png/full/157-1579943_tracy-mcgrady-png.png";

            }
            getActivity().finish();
///////////////////////////////SharedPreferences
            Context context = getActivity();
            SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.profile_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(String.valueOf(R.string.profile_name_key),personName);
            editor.putString(String.valueOf(R.string.profile_email_key),personEmail);
            editor.putString(String.valueOf(R.string.profile_id_key),personId);
            editor.putString(String.valueOf(R.string.profile_photo_key),personPhoto);
            editor.apply();
            us=new UsersDataBase(getContext());
            us.register_user(personName,personEmail,personId+personFamilyName,"NormalUser","-",account.getPhotoUrl().toString());
            Intent intent= new Intent(getContext(), HomeBottomActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), personName, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getActivity(), "Could not sign in with Google", Toast.LENGTH_SHORT).show();
        }
    }

}
