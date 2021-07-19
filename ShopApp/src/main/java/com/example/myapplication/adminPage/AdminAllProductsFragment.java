package com.example.myapplication.adminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.database.product.Product;
import com.example.myapplication.database.product.ProductDao;
import com.example.myapplication.database.product.ProductDataBase;
import com.example.myapplication.databinding.FragmentAdminAllProductsBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class AdminAllProductsFragment extends Fragment {

    private FragmentAdminAllProductsBinding binding;
    private FragmentHomeBinding homeBinding;
    private TextView textView;
    File database= new File("ProductDataBase");


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminAllProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        ProductDataBase productDataBase= new ProductDataBase() {
            @Override
            public ProductDao productDao() {
                return null;
            }

            @NonNull
            @NotNull
            @Override
            protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
                return null;
            }

            @NonNull
            @NotNull
            @Override
            protected InvalidationTracker createInvalidationTracker() {
                return null;
            }

            @Override
            public void clearAllTables() {

            }
        };
        List<Product>products=productDataBase.productDao().getAll();

        RecyclerView recyclerView=binding.adminProductsRecyclerview;


        AdminAdapter adminAdapter=new AdminAdapter(products);
        recyclerView.setAdapter(adminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }
}