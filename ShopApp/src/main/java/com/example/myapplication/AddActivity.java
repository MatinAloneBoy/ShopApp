package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.database.UpdateProfileDataBase;
import com.example.myapplication.database.product.ProductDataBase;
import com.example.myapplication.database.product.Product;
import com.example.myapplication.database.product.ProductDao;
import com.example.myapplication.databinding.ActivityAddBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private static final int REQUEST_GET_SINGLE_FILE = 0;
    private ActivityAddBinding binding;
    private String ProductName,ProductPrice,ProductDescription;


    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDate = sdf.format(new Date());

        ProductDataBase productDataBase=ProductDataBase.getInstance(this);
        ProductDao productDao= productDataBase.productDao();
        List<Product>products=productDao.getAll();

        SharedPreferences sharedPref =this.getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        String Name=sharedPref.getString(String.valueOf(R.string.profile_name_key),"");
        String Mail=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");
        String Id=sharedPref.getString(String.valueOf(R.string.profile_id_key),"");
        String Photo=sharedPref.getString(String.valueOf(R.string.profile_photo_key),"");




        productDao.insert(products.size()+1,binding.productName.getText().toString(),
                "none","","Name","",
                Integer.parseInt(binding.productPrice.getText().toString()),currentDate,
                binding.productDescriptions.getText().toString());


        UpdateProfileDataBase us=new UpdateProfileDataBase(this);
        String s=us.readProductNum(Mail);
        int a=Integer.valueOf(s);
        a++;
        s=String.valueOf(a);
        us.updateProductNum(s,Mail);



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