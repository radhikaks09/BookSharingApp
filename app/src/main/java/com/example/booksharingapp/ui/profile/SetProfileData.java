package com.example.booksharingapp.ui.profile;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SetProfileData {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public void updateProfile() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Task<DocumentSnapshot> data = db.collection("users").document(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        Log.d(TAG, "Successfully retrieved data");
                        ProfileViewModel dataModel = new ProfileViewModel();
                        dataModel.setUserProfile(document.get("username").toString(), document.getString("emailID").toString());
                    }
                    else{
                        Log.d(TAG, "No such document");
                    }
                }
                else{
                    Log.d(TAG, "Retrieval failed with ", task.getException());
                }
            }
        });
    }
}
