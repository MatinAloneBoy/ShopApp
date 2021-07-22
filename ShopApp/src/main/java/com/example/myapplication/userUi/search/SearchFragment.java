package com.example.myapplication.userUi.search;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.Room.User.product.Product;
import com.example.myapplication.database.repository.Repository;
import com.example.myapplication.database.repository.RepositoryCallback;
import com.example.myapplication.database.repository.Result;
import com.example.myapplication.databinding.FragmentSearchBinding;
import com.example.myapplication.userUi.home.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {

    private SearchViewModel profileViewModel;
    private @NonNull
    FragmentSearchBinding binding;
    CircleImageView ProfilePic;
    ListView simpleList;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =new ViewModelProvider(this).get(SearchViewModel.class);
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        Context context = getActivity();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        String GName=sharedPref.getString(String.valueOf(R.string.profile_name_key),"");
        String GMail=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");
        String GId=sharedPref.getString(String.valueOf(R.string.profile_id_key),"");
        String GPhoto=sharedPref.getString(String.valueOf(R.string.profile_photo_key),"");
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView=binding.searchRecyclerview;


        List<Product> products=new ArrayList<>();

        Repository.getInstance(getContext()).getAllProducts(new RepositoryCallback<List<Product>>() {
            @Override
            public void onComplete(Result<List<Product>> result) {
                if(result instanceof Result.Success){
                    products.addAll(((Result.Success<List<Product>>)result).data);

                }else if(result instanceof Result.Error){
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });


        List<String>ProductNames=new ArrayList<>();
        for (Product p:products) {
            ProductNames.add(p.Name);
        }



        binding.searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productsToShow=new ArrayList<>();
                for (Product p:products) {
                    if(p.Name.contains(binding.searchEditText.getText().toString())){
                        productsToShow.add(p);
                        HomeAdapter homeAdapter=new HomeAdapter(productsToShow,getContext());
                        recyclerView.setAdapter(homeAdapter);
                    }
                }

            }
        });



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}