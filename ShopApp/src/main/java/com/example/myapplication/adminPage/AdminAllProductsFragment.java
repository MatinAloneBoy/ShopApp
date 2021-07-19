package com.example.myapplication.adminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.database.product.Product;
import com.example.myapplication.database.product.ProductDao;
import com.example.myapplication.database.product.Products;
import com.example.myapplication.databinding.FragmentAdminAllProductsBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class AdminAllProductsFragment extends Fragment {

    private FragmentAdminAllProductsBinding binding;
    private FragmentHomeBinding homeBinding;
    private TextView textView;

    Products db = Room.databaseBuilder(getContext(),
            Products.class, "Products").build();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminAllProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ProductDao productDao= db.productDao();
        List<Product>products=productDao.getAll();

        RecyclerView recyclerView=binding.adminProductsRecyclerview;


        AdminAdapter adminAdapter=new AdminAdapter(products);
        recyclerView.setAdapter(adminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }
}