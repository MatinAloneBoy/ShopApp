package com.example.myapplication.database.repository;

import android.content.Context;

import com.example.myapplication.Application.MyApplication;
import com.example.myapplication.database.Room.User.User;
import com.example.myapplication.database.Room.User.product.Product;

import java.util.List;
import java.util.concurrent.Executor;

public class Repository {

    private static Repository repository;

    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    private Repository(Context context){
        localDataSource=new LocalDataSource(context);
        remoteDataSource=new RemoteDataSource();
    }


    public static Repository getInstance(Context context){

        if(repository == null){
            repository=new Repository(context);
        }

        return repository;
    }
///////////////Product
    public void getAllProducts(RepositoryCallback<List<Product>> callback){
        MyApplication.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Product> users = localDataSource.getAllProducts();
                    callback.onComplete(new Result.Success<>(users));
                }catch (Exception e){
                    callback.onComplete(new Result.Error<>(e));
                }
            }
        });
    }


    public void insertProduct(Product product,RepositoryCallback<Void> callback){
        MyApplication.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    localDataSource.insertProduct(product);
                    callback.onComplete(new Result.Success<>(null));
                }catch (Exception e){
                    callback.onComplete(new Result.Error<>(e));
                }
            }
        });
    }


//////////////////User
    public void getAllUsers(RepositoryCallback<List<User>> callback){
        MyApplication.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<User> users = localDataSource.getAllUsers();
                    callback.onComplete(new Result.Success<>(users));
                }catch (Exception e){
                    callback.onComplete(new Result.Error<>(e));
                }
            }
        });

    }

    public void insertUser(User user,RepositoryCallback<Void> callback){
        MyApplication.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    localDataSource.insertUser(user);
                    callback.onComplete(new Result.Success<>(null));
                }catch (Exception e){
                    callback.onComplete(new Result.Error<>(e));
                }
            }
        });

    }

}
