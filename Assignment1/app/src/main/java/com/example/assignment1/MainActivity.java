package com.example.assignment1;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.assignment1.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity
        implements Fragment2.OnContinueListener, Fragment3.OnCheckChangedListener {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_container, Fragment1.newInstance(null, null))
                    .commit();
        }

        fm.addOnBackStackChangedListener(this::updateContinueButton);
        updateContinueButton();

        binding.buttonContinue.setOnClickListener(view ->
                fm.beginTransaction()
                        .replace(R.id.fragment_container, Fragment2.newInstance(null, null))
                        .addToBackStack(null)
                        .commit());
    }
    private void updateContinueButton() {
        boolean onFirstScreen = getSupportFragmentManager().getBackStackEntryCount() == 0;
        binding.buttonContinue.setVisibility(onFirstScreen ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onContinueClicked(String name) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, Fragment3.newInstance(name))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCheckChanged(boolean checked) {
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (current instanceof Fragment3) {
            ((Fragment3) current).updateButton(checked);
        }
    }
}