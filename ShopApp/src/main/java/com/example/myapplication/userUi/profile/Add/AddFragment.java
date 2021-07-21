package com.example.myapplication.userUi.profile.Add;

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

import com.example.myapplication.R;
import com.example.myapplication.database.UpdateProfileDataBase;
import com.example.myapplication.database.Room.User.product.Product;
import com.example.myapplication.database.Room.User.product.ProductDao;
import com.example.myapplication.database.Room.User.product.AppDataBase;
import com.example.myapplication.database.repository.Repository;
import com.example.myapplication.database.repository.RepositoryCallback;
import com.example.myapplication.database.repository.Result;
import com.example.myapplication.databinding.FragmentAddBinding;
import com.example.myapplication.userUi.home.HomeBottomActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddFragment extends Fragment {


    private static final int REQUEST_GET_SINGLE_FILE = 1;
    private FragmentAddBinding binding;
    public String ProductName,ProductPrice,ProductDescription,PhotoPath,PhotoUrl;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_add,container,false);

        RepositoryCallback<Void> callback =new RepositoryCallback<Void>() {
            @Override
            public void onComplete(Result<Void> result) {
                if(result instanceof Result.Success){
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }
        };

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDate = sdf.format(new Date());




        SharedPreferences sharedPref =getActivity().getSharedPreferences(
                getString(R.string.profile_file_key), Context.MODE_PRIVATE);
        String Name=sharedPref.getString(String.valueOf(R.string.profile_name_key),"");
        String Mail=sharedPref.getString(String.valueOf(R.string.profile_email_key),"");
        String Id=sharedPref.getString(String.valueOf(R.string.profile_id_key),"");
        String Photo=sharedPref.getString(String.valueOf(R.string.profile_photo_key),"");






        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product=new Product(binding.productName.getText().toString(),
                        PhotoUrl,Name,
                        binding.productSellerPhoneNumber.getText().toString(),
                        Integer.parseInt(binding.productPrice.getText().toString()),
                        currentDate,binding.productDescriptions.getText().toString());

                Repository.getInstance(getContext()).insertProduct(product,callback);

                Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(v.getContext(), HomeBottomActivity.class);
                startActivity(intent);
            }
        });



        binding.productPhoto.setOnClickListener(new View.OnClickListener() {
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
                    PhotoPath=path;
                    if (path != null) {
                        File f = new File(path);
                        selectedImageUri = Uri.fromFile(f);
                    }
                    // Set the image in ImageView
                    binding.productPhoto.setImageURI(selectedImageUri);
                    PhotoUrl=selectedImageUri.toString();
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