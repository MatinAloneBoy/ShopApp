package com.example.myapplication.userUi.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.database.Room.User.product.Product;
import com.example.myapplication.databinding.FragmentProductBinding;
import com.example.myapplication.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        SharedPreferences sharedPref = getActivity().getSharedPreferences(String.valueOf(R.string.profile_file_key), Context.MODE_PRIVATE);
        String PName=sharedPref.getString("Product Name","");
        String PPhoto=sharedPref.getString("Product Photo","");
        String PSellerName=sharedPref.getString("Product Seller Name","");
        String PSellerPhone=sharedPref.getString("Product Seller PhoneNum","");
        String PPrice=sharedPref.getString("Product Price","");
        String PDes=sharedPref.getString("Product Description","");
        String PDate=sharedPref.getString("Product Date","");


        binding.productPriceText.setText(PPrice);
        binding.productDateText.setText(PDate);
        binding.productDescriptionsText.setText((PDes));
        binding.productNameText.setText(PName);
        binding.productSellerNameText.setText(PSellerName);
        binding.productPhoneNumberText.setText(PSellerPhone);
        Picasso.get().load(Uri.parse(PPhoto)).into(binding.productImageView);

        binding.productPhoneNumberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = binding.productPhoneNumberText.getText().toString();
                Intent callintent=new Intent(Intent.ACTION_CALL);
                callintent.setData(Uri.parse("tel:"+number));
                startActivity(callintent);
            }
        });


        return root;
    }
}

