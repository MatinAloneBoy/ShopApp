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


            products.add(new Product("Name","data:image/png;base64,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","Seller","09124758727",12000,"12,1,1400","Description"));

        
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