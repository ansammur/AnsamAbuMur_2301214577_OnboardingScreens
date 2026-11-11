package com.example.assignment1;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.assignment1.databinding.Fragment3Binding;
public class Fragment3 extends Fragment {

    public interface OnCheckChangedListener {
        void onCheckChanged(boolean checked);
    }
    private static final String ARG_NAME = "name";
    private OnCheckChangedListener listener;
    private Fragment3Binding binding;
    public Fragment3() {
    }

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCheckChangedListener) {
            listener = (OnCheckChangedListener) context;
        } else {
            throw new ClassCastException(context + " must implement OnCheckChangedListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment3Binding.inflate(inflater, container, false);
        String name = getArguments() != null ? getArguments().getString(ARG_NAME) : "";
        binding.tvName.setText("Hello, " + name + "!");
            updateButton(false);
        binding.checkAgree.setOnCheckedChangeListener((buttonView, isChecked) ->
                listener.onCheckChanged(isChecked));
        return binding.getRoot();
    }

    public void updateButton(boolean checked) {
        if (binding == null) return;
        binding.btnFinish.setEnabled(checked);
        binding.btnFinish.setText(checked ? "Finish" : "Continue");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}