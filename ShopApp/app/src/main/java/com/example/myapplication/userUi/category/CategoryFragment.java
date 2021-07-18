package com.example.myapplication.userUi.category;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCategoryBinding;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryFragment extends Fragment {

    private CategoryViewModel profileViewModel;
    private @NonNull FragmentCategoryBinding binding;
    CircleImageView ProfilePic;
    ListView simpleList;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =new ViewModelProvider(this).get(CategoryViewModel.class);
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        String GName=sharedPref.getString(String.valueOf(R.string.profile_name_key),"");
        String GMail=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");
        String GId=sharedPref.getString(String.valueOf(R.string.profile_id_key),"");
        String GPhoto=sharedPref.getString(String.valueOf(R.string.profile_photo_key),"");

        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        String List[] = {"Furniture","Digital Devices","Cars"};
        simpleList = binding.categoryFragmentList;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_listview, R.id.textView, List);
        simpleList.setAdapter(arrayAdapter);

        binding.categoryFragmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                View view2 = inflater.inflate(R.layout.fragment_profile,container,false);

                switch (position){
                    case 0:
//                        Toast.makeText(context, "ID", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
//                        Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
//                        Toast.makeText(context, "Log out", Toast.LENGTH_SHORT).show();
                        break;
                }


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