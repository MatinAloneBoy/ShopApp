package com.example.myapplication;

import android.content.ContentProvider;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.FragmentAddBinding;

import org.jetbrains.annotations.NotNull;

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;
    private String ProductName,ProductPrice,ProductDescription;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ProductName=binding.productName.getText().toString();
        ProductPrice=binding.productPrice.getText().toString();
        ProductDescription=binding.productDescriptions.getText().toString();






        return root;
    }


}