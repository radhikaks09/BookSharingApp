package com.example.booksharingapp.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mUsername;
    private MutableLiveData<String> mDescription;
    private MutableLiveData<String> mProfileImageUrl;

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is profile fragment");

        mUsername = new MutableLiveData<>();
        mDescription = new MutableLiveData<>();
        mProfileImageUrl = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getUsername() {
        return mUsername;
    }

    public LiveData<String> getDescription() {
        return mDescription;
    }

    public LiveData<String> getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public void setUserProfile(String username, String description, String profileImageUrl) {
        mUsername.setValue(username);
        mDescription.setValue(description);
        mProfileImageUrl.setValue(profileImageUrl);
    }
}
