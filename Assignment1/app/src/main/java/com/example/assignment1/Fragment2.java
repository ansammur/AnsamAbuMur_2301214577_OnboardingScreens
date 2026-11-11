package com.example.assignment1;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.assignment1.databinding.Fragment2Binding;


public class Fragment2 extends Fragment {

    public interface OnContinueListener {
        void onContinueClicked(String name);
    }
    private OnContinueListener listener;
    private Fragment2Binding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public Fragment2() {
    }
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
     @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnContinueListener) {
            listener = (OnContinueListener) context;
        } else {
            throw new ClassCastException(context + " must implement OnContinueListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);
        binding.btnContinue.setOnClickListener(v -> {
            String name = binding.editText.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                binding.editText.setError("Enter name!");
            } else {
                listener.onContinueClicked(name);
            }
        });
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}