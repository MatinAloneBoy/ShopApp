<<<<<<< HEAD
package com.example.myapplication.userUi.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CategoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Category fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
=======
package com.example.myapplication.userUi.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CategoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Category fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
>>>>>>> 46d9c479dfc7ed9c66e29ed55300803775512841
}