package com.example.myapplication.adminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.database.product.Product;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.userUi.home.HomeAdapter;
import com.example.myapplication.userUi.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;


public class AdminPage extends Fragment {

    private FragmentHomeBinding binding;
    private TextView txtview;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView=binding.homeRecyclerview;
        txtview= recyclerView.findViewById(R.id.Count_Product_text);
        List<Product> products=new ArrayList<>();

        txtview.setText(products.size());
        AdminAdapter adminAdapter=new AdminAdapter(products);
        recyclerView.setAdapter(adminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}