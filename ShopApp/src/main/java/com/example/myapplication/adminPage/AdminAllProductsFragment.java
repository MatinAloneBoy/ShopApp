package com.example.myapplication.adminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.database.Room.User.product.AppDataBase;
import com.example.myapplication.database.Room.User.product.Product;
import com.example.myapplication.database.Room.User.product.ProductDao;
import com.example.myapplication.database.repository.Repository;
import com.example.myapplication.database.repository.RepositoryCallback;
import com.example.myapplication.database.repository.Result;
import com.example.myapplication.databinding.FragmentAdminAllProductsBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.userUi.home.HomeAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.File;
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


        products.add(new Product("Toy","","Arian","09124758727",12000,"12,1,1400","a good Toy"));


        HomeAdapter homeAdapter=new HomeAdapter(products);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return root;
    }
}