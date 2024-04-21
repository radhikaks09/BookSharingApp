package com.example.booksharingapp.ui.forum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class ForumViewModel extends ViewModel {
    private final MutableLiveData<List<String>> mMessages;
    private final MutableLiveData<String> mText;

    public ForumViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is book sharing forum fragment");
        mMessages = new MutableLiveData<>();
        mMessages.setValue(new ArrayList<>());
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<String>> getMessages() {
        return mMessages;
    }

    public void addMessage(String message) {
        if (message != null && !message.trim().isEmpty()) {
            List<String> currentMessages = mMessages.getValue();
            if (currentMessages == null) {
                currentMessages = new ArrayList<>();
            }
            currentMessages.add(message);
            mMessages.setValue(currentMessages);
        }
    }
}