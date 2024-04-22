package com.example.booksharingapp.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mUsername;
    private MutableLiveData<String> mEmail;
    private MutableLiveData<String> mProfileImageUrl;

    public ProfileViewModel() {
        mUsername = new MutableLiveData<>();
        mEmail = new MutableLiveData<>();
        mProfileImageUrl = new MutableLiveData<>();
    }

    public LiveData<String> getUsername() {
        return mUsername;
    }

    public LiveData<String> getEmail() {
        return mEmail;
    }

    public LiveData<String> getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public void setUserProfile(String username, String email) {
        mUsername.setValue(username);
        mEmail.setValue(email);
    }
}
