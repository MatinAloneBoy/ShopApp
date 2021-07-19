package com.example.myapplication;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.myapplication.database.UpdateProfileDataBase;
import com.example.myapplication.database.product.ProductDao;
import com.example.myapplication.database.product.ProductDataBase;
import com.example.myapplication.databinding.FragmentAddBinding;
import com.example.myapplication.userUi.home.HomeBottomActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFragment extends Fragment {


    private static final int REQUEST_GET_SINGLE_FILE = 1;
    private FragmentAddBinding binding;
    private String ProductName,ProductPrice,ProductDescription;
    ProductDataBase db = Room.databaseBuilder(getContext(),
            ProductDataBase.class, "ProductDB").build();
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_add,container,false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDate = sdf.format(new Date());

        SharedPreferences sharedPref =getActivity().getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        String Name=sharedPref.getString(String.valueOf(R.string.profile_name_key),"");
        String Mail=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");
        String Id=sharedPref.getString(String.valueOf(R.string.profile_id_key),"");
        String Photo=sharedPref.getString(String.valueOf(R.string.profile_photo_key),"");






        ProductDataBase productDataBase=ProductDataBase.getInstance(getContext());
        ProductDao productDao=db.productDao();





        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productDao.insert(Integer.valueOf(String.valueOf(Math.random())),
                        binding.productName.getText().toString(),
                        "none",Photo,Name,"",
                        Integer.parseInt(binding.productPrice.getText().toString()),currentDate,
                        binding.productDescriptions.getText().toString());

                UpdateProfileDataBase us=new UpdateProfileDataBase(getContext());
                String s=us.readProductNum(Mail);
                int a=Integer.valueOf(s);
                a++;
                s=String.valueOf(a);
                us.updateProductNum(s,Mail);
                Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(v.getContext(), HomeBottomActivity.class);
                startActivity(intent);
            }
        });



        binding.addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_GET_SINGLE_FILE);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (true) {
                if (true) {
                    Uri selectedImageUri = data.getData();
                    // Get the path from the Uri
                    final String path = getPathFromURI(selectedImageUri);
                    if (path != null) {
                        File f = new File(path);
                        selectedImageUri = Uri.fromFile(f);
                    }
                    // Set the image in ImageView
                    binding.productPhoto.setImageURI(selectedImageUri);
                }
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

}