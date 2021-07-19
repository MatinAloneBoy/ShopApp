package com.example.myapplication.adminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.product.Product;
import com.example.myapplication.databinding.FragmentAdminPageBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.userUi.home.HomeAdapter;
import com.example.myapplication.userUi.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;


public class AdminPage extends Fragment {

    private FragmentAdminPageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.productListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "All Product List", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_adminPage_to_adminAllProductsFragment);
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