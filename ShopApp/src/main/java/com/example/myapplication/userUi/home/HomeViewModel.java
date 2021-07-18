<<<<<<< HEAD
package com.example.myapplication.userUi.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
=======
package com.example.myapplication.userUi.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
>>>>>>> 46d9c479dfc7ed9c66e29ed55300803775512841
}