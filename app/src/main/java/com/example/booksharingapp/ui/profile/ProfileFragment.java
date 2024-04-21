package com.example.booksharingapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.booksharingapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);  // Changed to ensure consistent ViewModel instance across fragments

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Set text views and image views based on the LiveData from ViewModel
        profileViewModel.getUsername().observe(getViewLifecycleOwner(), username -> binding.usernameTextView.setText(username));
        profileViewModel.getDescription().observe(getViewLifecycleOwner(), description -> binding.descriptionTextView.setText(description));
        profileViewModel.getProfileImageUrl().observe(getViewLifecycleOwner(), imageUrl -> {
            if (!imageUrl.isEmpty()) {
                Glide.with(this).load(imageUrl).into(binding.profileImageView);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
