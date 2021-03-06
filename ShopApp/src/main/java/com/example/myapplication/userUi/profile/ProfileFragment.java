package com.example.myapplication.userUi.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    CircleImageView ProfilePic;
    ListView simpleList;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =new ViewModelProvider(this).get(ProfileViewModel.class);
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        String GName=sharedPref.getString(String.valueOf(R.string.profile_name_key),"");
        String GMail=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");
        String GId=sharedPref.getString(String.valueOf(R.string.profile_id_key),"");
        String GPhoto=sharedPref.getString(String.valueOf(R.string.profile_photo_key),"");

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ProfilePic=binding.profileFragmentImage;
        binding.profileFragmentNameText.setText(GName);
        binding.profileFragmentEmailText.setText(GMail);

        String List[] = {"#"+GId.substring(0,5),"Settings","Log out"};
        simpleList = binding.profileFragmentList;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_listview, R.id.textView, List);
        simpleList.setAdapter(arrayAdapter);

        binding.profileFragmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_home);
                switch (position){
                    case 0:
                        Toast.makeText(context, "ID", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        Navigation.findNavController(view).navigate(R.id.action_navigation_profile_to_settingsFragment);
                        Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        
                        Toast.makeText(context, "Log out", Toast.LENGTH_SHORT).show();
                        break;
                }


            }
        });


//        Picasso.get().load(GPhoto).into(ProfilePic);
        Picasso.get().load(Uri.parse(GPhoto)).into(ProfilePic);
/*
        binding.profileFragmentNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, GPhoto, Toast.LENGTH_SHORT).show();
            }
        });*/
        binding.profileFragmentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, GPhoto, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}