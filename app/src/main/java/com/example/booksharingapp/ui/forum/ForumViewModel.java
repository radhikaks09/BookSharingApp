package com.example.booksharingapp.ui.forum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForumViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ForumViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is book sharing forum fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}