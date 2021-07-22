package com.example.myapplication.adminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.database.product.Product;
import com.example.myapplication.databinding.FragmentAdminAllProductsBinding;
import com.example.myapplication.databinding.FragmentAdminPageBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdminAllProductsFragment extends Fragment {

    private FragmentAdminAllProductsBinding binding;
    private FragmentHomeBinding homeBinding;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminAllProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        RecyclerView recyclerView=binding.adminProductsRecyclerview;
        List<Product> products=new ArrayList<>();
        AdminAdapter adminAdapter=new AdminAdapter(products);
        recyclerView.setAdapter(adminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }
}