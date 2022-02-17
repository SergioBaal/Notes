package ru.geekbrains.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            MenuFragment menuFragment = MenuFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.titles, menuFragment)
                    .commit();


        }




        // если это первый запуск, то создается фрагмент, который помещает (заменяет) себя в основной макет.
      /*  if (savedInstanceState == null) {
           TitlesFragment titlesFragment = TitlesFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.titles, titlesFragment)
                    .commit();
        } */
    }

    @Override //костыль
    protected void onResume() {
        super.onResume();
       /* Fragment backStackFragment = (Fragment) getSupportFragmentManager()
                .findFragmentById(R.id.titles);
        if (backStackFragment != null && backStackFragment instanceof NotesFragment){
            onBackPressed();
        }*/
    }
}