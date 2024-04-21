package com.example.booksharingapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to the BookSharingApp! \n" +
                "This app allows you to share any ebooks that you may have with you, to make it available for any member to download. You can search up any ebook that you want to read, and if it is available with us, in our database, then you are free to download it for your use :)\n" +
                "\n" +
                "We provide you with the following pages:\n" +
                "1. You may Login to your account using your EmailID and a unique password, or you may SignUp to our app by creating an account.\n" +
                "2. Once you are Logged in, you will be taken to the description screen (this one) and you will have a Navigation bar to the left of the screen.\n" +
                "3. The Navigation bar provides you with the details to contact the makers of the app. \n" +
                "\tIt also allows you to view your Profile and include a profile description if you wanted to. \n" +
                "\tIt allows you to open the Forum where you can upload and download books.\n" +
                "4. When you're done using the app, you may Logout.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}