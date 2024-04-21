package com.example.booksharingapp.ui.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booksharingapp.databinding.FragmentForumBinding;

public class ForumFragment extends Fragment {

    private static final int FILE_SELECT_CODE = 0;
    private FragmentForumBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ForumViewModel forumViewModel =
                new ViewModelProvider(this).get(ForumViewModel.class);

        binding = FragmentForumBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView forumHeaderText = binding.textGallery;
        final EditText messageInput = binding.editTextMessage;
        Button sendButton = binding.buttonSend;
        Button uploadButton = binding.buttonUpload;
        RecyclerView messagesList = binding.recyclerViewMessages;

        forumHeaderText.setText(forumViewModel.getText().getValue()); // Set forum header text

        messagesList.setLayoutManager(new LinearLayoutManager(getContext())); // Set the layout manager

        // Initialize the adapter with the current list of messages if any
        MessagesAdapter adapter = new MessagesAdapter(forumViewModel.getMessages().getValue());
        messagesList.setAdapter(adapter);

        // Observe changes in messages list
        forumViewModel.getMessages().observe(getViewLifecycleOwner(), adapter::setMessages);

        sendButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString().trim();
            if (!message.isEmpty()) {
                forumViewModel.addMessage(message);
                messageInput.setText(""); // Clear the input field after sending
            }
        });

        uploadButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*"); // This can be adjusted to filter specific file types
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, FILE_SELECT_CODE);
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_SELECT_CODE && resultCode == getActivity().RESULT_OK) {
            // Handle file selection
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Ensure you clean up any bindings to avoid memory leaks
    }
}