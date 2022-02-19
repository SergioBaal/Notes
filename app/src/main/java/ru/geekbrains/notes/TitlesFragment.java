package ru.geekbrains.notes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;


public class TitlesFragment extends Fragment {

    public static final String CURRENT_TITLE = "title";
    private Title currentTitle;


    public static TitlesFragment newInstance() {
        TitlesFragment fragment = new TitlesFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_titles, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_TITLE, currentTitle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            currentTitle = savedInstanceState.getParcelable(CURRENT_TITLE);
        } else {
            currentTitle = new Title(0);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showLand(currentTitle);
        }
        initView(view);
    }


    private void initView(View view) {
        String[] titles = getResources().getStringArray(R.array.titles);

        for (int i = 0; i < titles.length; i++) {
            String titleName = titles[i];
            TextView textView = new TextView(getContext());
            textView.setText(titleName);
            textView.setTextSize(40f);
            ((LinearLayout) view).addView(textView);
            final int finalI = i;

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    currentTitle = new Title(finalI);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        showLand(currentTitle);
                    } else {
                        String[] titleList = getResources().getStringArray(R.array.titles);
                        showPort(currentTitle);
                        Toolbar toolbar = getActivity().findViewById(R.id.our_bar);
                        toolbar.setTitle(titleList[finalI]);
                    }
                }
            });

            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(requireContext(), view);
                    requireActivity().getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()) {
                                case (R.id.action_popup_more):
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.titles, new PopupFragment()).addToBackStack("")
                                            .commit();
                                    Toolbar toolbar = getActivity().findViewById(R.id.our_bar);
                                    toolbar.setTitle("More");
                                    return true;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                    return false;
                }
            });

        }
    }


    private void showLand(Title currentTitle) {
        NotesFragment notesFragment = NotesFragment.newInstance(currentTitle);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes, notesFragment)
                .commit();

    }


    private void showPort(Title title) {
        NotesFragment notesFragment = NotesFragment.newInstance(title);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.titles, notesFragment)
                .addToBackStack("")
                .commit();
    }
}


