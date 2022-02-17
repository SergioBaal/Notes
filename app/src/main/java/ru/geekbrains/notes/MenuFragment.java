package ru.geekbrains.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MenuFragment extends Fragment {
    TextView textViewNotes;
    TextView textViewSettings;
    TextView textViewAbout;


    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewNotes(view);
        initViewSettings(view);
        initViewAbout(view);
    }







    public void initViewNotes (View view) {
        textViewNotes = (TextView) view.findViewById(R.id.menu_notes);
        textViewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    TitlesFragment titlesFragment = TitlesFragment.newInstance();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.titles, titlesFragment)
                            .addToBackStack("sd")
                            .commit();
            }
        });
    }
    public void initViewSettings (View view) {
        textViewSettings = (TextView) view.findViewById(R.id.menu_settings);
        textViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsFragment settingsFragment = SettingsFragment.newInstance();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.titles, settingsFragment)
                        .addToBackStack("sd")
                        .commit();
            }
        });
    }
    public void initViewAbout (View view) {
        textViewAbout = (TextView) view.findViewById(R.id.menu_about);
        textViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutFragment aboutFragment = AboutFragment.newInstance();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.titles, aboutFragment)
                        .addToBackStack("sd")
                        .commit();
            }
        });
    }
}