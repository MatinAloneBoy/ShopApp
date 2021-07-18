package com.example.myapplication.userUi.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.product.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<Product> products;

    public HomeAdapter(List<Product> products) {
        this.products = products;
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
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView,dueTextView;
        View view;

        public HomeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.product_title);
            dueTextView = itemView.findViewById(R.id.product_view_due);
            view = itemView;
        }

        public void bind(Product pro){
            titleTextView.setText(pro.Name);
            dueTextView.setText(pro.Date);

        }
    }
}
