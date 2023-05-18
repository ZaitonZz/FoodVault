package com.example.foodvault.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodvault.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;


public class FragmentSearch extends Fragment {

    Chip filterChip;

    FragmentFilters f = new FragmentFilters();
    BottomSheetDialog bottomSheetDialog;

    int clickCounter = 1; // serves as counter to close filter when clicked again
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);


        filterChip = view.findViewById(R.id.chipFilter);
        bottomSheetDialog = new BottomSheetDialog(view.getContext(),R.style.BottomSheetDialogTheme);

        createDialog();

        filterChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });
        return view;
    }

    private void createDialog() {
        View bottonSheetView = getLayoutInflater().inflate(R.layout.fragment_filters, null, false);
        bottomSheetDialog.setContentView(bottonSheetView);
    }


}