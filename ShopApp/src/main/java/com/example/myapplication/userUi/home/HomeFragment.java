package com.example.myapplication.userUi.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.database.product.Product;
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

        for (int i = 0; i < 100; i++) {
            products.add(new Product(1,"Fuck You","Fucks","","Arian","09124758727",12,"12,1,1400","a good fuck"));
        }
        
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