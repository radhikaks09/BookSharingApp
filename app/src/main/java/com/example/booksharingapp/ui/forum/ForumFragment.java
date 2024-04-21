package com.example.booksharingapp.ui.forum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.booksharingapp.databinding.FragmentForumBinding;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ForumFragment extends Fragment {

    private static final int FILE_SELECT_CODE = 0;
    private static final String PDF_MIME_TYPE = "application/pdf";
    private static final String PDF_STORAGE_PATH = "pdfs/";
    private FragmentForumBinding binding;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        ForumViewModel forumViewModel = new ViewModelProvider(this).get(ForumViewModel.class);

        binding = FragmentForumBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupUI(forumViewModel);

        return root;
    }

    private void setupUI(ForumViewModel forumViewModel) {
        binding.textGallery.setText(forumViewModel.getText().getValue());
        binding.recyclerViewMessages.setLayoutManager(new LinearLayoutManager(getContext()));
        MessagesAdapter adapter = new MessagesAdapter(forumViewModel.getMessages().getValue());
        binding.recyclerViewMessages.setAdapter(adapter);
        forumViewModel.getMessages().observe(getViewLifecycleOwner(), adapter::setMessages);

        binding.buttonSend.setOnClickListener(v -> sendMessage(forumViewModel));
        binding.buttonUpload.setOnClickListener(v -> selectPDFFile());
    }

    private void sendMessage(ForumViewModel forumViewModel) {
        String message = binding.editTextMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            forumViewModel.addMessage(message);
            binding.editTextMessage.setText("");
        }
    }

    private void selectPDFFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(PDF_MIME_TYPE);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, FILE_SELECT_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_SELECT_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            uploadPDFFile(data.getData());
        }
    }

    private void uploadPDFFile(Uri fileUri) {
        Log.d("UploadPDF", "Attempting to upload: " + fileUri.toString());

        if (fileUri != null) {
            StorageReference fileRef = storageReference.child(PDF_STORAGE_PATH + System.currentTimeMillis() + ".pdf");
            fileRef.putFile(fileUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        Toast.makeText(getContext(), "File uploaded successfully", Toast.LENGTH_SHORT).show();
                        ForumViewModel forumViewModel = new ViewModelProvider(this).get(ForumViewModel.class);
                        forumViewModel.addMessage("PDF uploaded: " + taskSnapshot.getMetadata().getPath());
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("UploadPDF","Error: " + e.toString());
                    });
        } else {
            Toast.makeText(getContext(), "File URI is null", Toast.LENGTH_SHORT).show();
            Log.e("UploadPDF", "File URI is null");
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}