package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.database.Room.User.User;
import com.example.myapplication.database.Room.User.product.Product;
import com.example.myapplication.database.repository.Repository;
import com.example.myapplication.database.repository.RepositoryCallback;
import com.example.myapplication.database.repository.Result;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.databinding.FragmentShowSellersBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ShowSellersFragment extends Fragment {
    private FragmentShowSellersBinding binding;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentShowSellersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


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

        List<String>SellerNames=new ArrayList<>();


        for (Product product:products){
            SellerNames.add(product.SellerName);
        }




        
        ListView simpleList = binding.allSellerList;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_listview, R.id.textView, SellerNames);
        simpleList.setAdapter(arrayAdapter);
        return root;
    }
}