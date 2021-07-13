package com.example.myapplication.ui.profile;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

        String List[] = {"#"+GId,"Settings"};
        simpleList = binding.profileFragmentList;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_listview, R.id.textView, List);
        simpleList.setAdapter(arrayAdapter);

        binding.profileFragmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                View view2 = inflater.inflate(R.layout.fragment_profile,container,false);

                switch (position){
                    case 0:
                        Toast.makeText(context, "Fuck You", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        Navigation.findNavController(view2).navigate(R.id.action_navigation_profile_to_settingsFragment2);
                        break;
                }


            }
        });


        if(!GPhoto.equals(null)){
            Picasso.get().load(Uri.parse(GPhoto)).into(ProfilePic);
        }else {

        }

        binding.profileFragmentNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, GPhoto, Toast.LENGTH_SHORT).show();
            }
        });
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