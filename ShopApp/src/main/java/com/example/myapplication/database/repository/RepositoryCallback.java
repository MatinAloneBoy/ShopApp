package com.example.myapplication.database.repository;

public interface RepositoryCallback<T> {
    void onComplete(Result<T> result);
}
