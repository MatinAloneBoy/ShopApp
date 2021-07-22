package com.example.myapplication.userUi.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.database.Room.User.product.Product;
import com.example.myapplication.database.repository.Repository;
import com.example.myapplication.database.repository.RepositoryCallback;
import com.example.myapplication.database.repository.Result;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView=binding.homeRecyclerview;
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

            products.add(new Product("Name","https://www.pngitem.com/pimgs/b/178-1783030_shop-now-png.png","Seller","09124758727",12000,"12,1,1400","Description"));

        
        HomeAdapter homeAdapter=new HomeAdapter(products);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}