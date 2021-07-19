package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.database.product.AppDatabase;
import com.example.myapplication.database.product.Product;
import com.example.myapplication.database.product.ProductDao;
import com.example.myapplication.databinding.ActivityAddBinding;
import com.example.myapplication.databinding.FragmentAddBinding;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private static final int REQUEST_GET_SINGLE_FILE = 0;
    private ActivityAddBinding binding;
    private String ProductName,ProductPrice,ProductDescription;
    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "database-name").build();

    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDate = sdf.format(new Date());

        ProductDao productDao= db.productDao();
        List<Product>products=productDao.getAll();

        productDao.insert(products.size()+1,binding.productName.getText().toString(),
                "none","","","",
                Integer.parseInt(binding.productPrice.getText().toString()),currentDate,
                binding.productDescriptions.getText().toString());





        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivity(intent);
            }
        });

    }

}