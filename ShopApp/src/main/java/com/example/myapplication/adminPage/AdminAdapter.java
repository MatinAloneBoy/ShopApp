package com.example.myapplication.adminPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.Room.User.product.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

    private List<Product> productlist;
    public AdminAdapter(List<Product> productlist){
        this.productlist=productlist;
    }

    @NonNull
    @NotNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.layout_home_adapter, parent, false);
        return new AdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdminViewHolder holder, int position) {
        holder.bind(productlist.get(position));
    }

    @Override
    public int getItemCount() {
        return productlist.size();
    }

    static class AdminViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView,dueTextView;
        View view;

        public AdminViewHolder(@NonNull @NotNull View itemView) {
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

