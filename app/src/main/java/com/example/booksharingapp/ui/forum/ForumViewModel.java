package com.example.booksharingapp.ui.forum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class ForumViewModel extends ViewModel {
    private final MutableLiveData<List<String>> mMessages;

    public ForumViewModel() {
        mMessages = new MutableLiveData<>();
        mMessages.setValue(new ArrayList<>());
    }


    public LiveData<List<String>> getMessages() {
        return mMessages;
    }

    public void addMessage(String message) {
        List<String> currentMessages = mMessages.getValue();
        if (currentMessages == null) {
            currentMessages = new ArrayList<>();
        }
        currentMessages.add(message);
        mMessages.setValue(currentMessages);
    }
}