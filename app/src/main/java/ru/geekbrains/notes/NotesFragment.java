package ru.geekbrains.notes;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;


public class NotesFragment extends Fragment {

public static final String ARG_TITLE = "title";
private Title title;

// Создание фрагмента заметки, сохранение аргумента.
    public static NotesFragment newInstance(Title title) {
        NotesFragment fragment = new NotesFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitles(view);
        setNotes(view);
    }

    private void setTitles (View view) {
        title = getArguments().getParcelable(ARG_TITLE);
        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        String [] titleList = getResources().getStringArray(R.array.titles);
        textViewTitle.setText(titleList[title.getIndex()]);
        textViewTitle.setTextSize(30f);
    }

    private void setNotes (View view) {
        TextView textViewNote = view.findViewById(R.id.textViewNote);
        String [] notelist = getResources().getStringArray(R.array.notes);
        textViewNote.setText(notelist[title.getIndex()]);
        textViewNote.setTextSize(30f);
    }
}