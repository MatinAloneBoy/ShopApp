package com.example.myapplication.userUi.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.Room.User.product.Product;
import com.squareup.picasso.Picasso;


import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{


    private List<Product> products;
    public Context context;

    public HomeAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context=context;
    }


    @NonNull
    @NotNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_home_adapter, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeViewHolder holder, int position) {
        holder.bind(products.get(position),context);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView titleTextView,sellerNameTextView,priceTextView;
        View view;


        public HomeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.product_title);
            sellerNameTextView = itemView.findViewById(R.id.product_seller_name);
            priceTextView=itemView.findViewById(R.id.product_price_text_view);
            imageView=itemView.findViewById(R.id.product_image);
            view = itemView;
        }

        public void bind(Product product,Context context){
            titleTextView.setText(product.Name);
            sellerNameTextView.setText(product.SellerName);
            priceTextView.setText(String.valueOf(product.Price));

        if(!product.PhotoUrl.equals(null)){
            Picasso.get().load(Uri.parse(product.PhotoUrl)).into(imageView);
        }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences sharedPref = context.getSharedPreferences(String.valueOf(R.string.profile_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Product Name",product.Name);
                    editor.putString("Product Date",product.Date);
                    editor.putString("Product Photo",product.PhotoUrl);
                    editor.putString("Product Seller Name",product.SellerName);
                    editor.putString("Product Seller PhoneNum",product.SellerPhone);
                    editor.putString("Product Price",String.valueOf(product.Price));
                    editor.putString("Product Description",product.Description);
                    editor.putString("Product Date",product.Date);
                    editor.apply();
                    ////////showing the product
                    Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_productFragment);
                }
            });
        }
    }
}
