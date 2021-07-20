package com.example.myapplication.adminPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.Room.User.product.Product;
import com.example.myapplication.database.repository.Repository;
import com.example.myapplication.database.repository.RepositoryCallback;
import com.example.myapplication.database.repository.Result;
import com.example.myapplication.databinding.FragmentAdminPageBinding;

import java.util.ArrayList;
import java.util.List;


public class AdminPage extends Fragment {

    private FragmentAdminPageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAdminPageBinding.inflate(inflater, container, false);
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
        double price = 0;
        for (Product product:products){
            price=price+Double.valueOf(product.Price);
        }


        binding.adminPageFragmentTotalPriceText.setText(String.valueOf(price));

        binding.productListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "All Product List", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_adminPage_to_adminAllProductsFragment);
            }
        });

        binding.sellerListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "All Sellers List", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.action_adminPage_to_showSellersFragment);
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