package ru.geekbrains.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
//FIXME: Если находишься в меню "настройки" (или "о приложении"), а потом переворачиваешь
//       экран, то action bar Title будет = "заметки" хотя мы находимся все еще в меню.

//FIXME: После работы в ланшафтной ориентации, при переход в портретную,
//       в основном меню сохраняется пункт меню фрагмента. Но если в ландшафтной открыть пункт
//       меню фрагмента, а потом передти в портретную, то этот пункт меню исчезнет.

//FIXME: Имеются некоторые проблемы с кнопкой назад. Когда мы долго работаем с приложением
//       и после этого нажимаем "назад", то приложение просто немного мерцает, но ничего не меняется.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            TitlesFragment titlesFragment = TitlesFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.titles, titlesFragment)
                    .commit();
        }

        Toolbar toolbar = findViewById(R.id.our_bar);
        setSupportActionBar(toolbar);
    }

    @Override //костыль
    protected void onResume() {
        super.onResume();
        Fragment backStackFragment = (Fragment) getSupportFragmentManager()
                .findFragmentById(R.id.titles);
        if (backStackFragment != null && backStackFragment instanceof NotesFragment) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Toolbar toolbar = findViewById(R.id.our_bar);
        toolbar.setTitle("Заметки");
        TitlesFragment titlesFragment = TitlesFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.titles, titlesFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case (R.id.action_about): {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.titles, new AboutFragment()).addToBackStack("")
                        .commit();
                Toolbar toolbar = findViewById(R.id.our_bar);
                toolbar.setTitle("О приложении");
                checkOrietnation();
                return true;
            }
            case (R.id.action_settings): {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.titles, new SettingsFragment()).addToBackStack("")
                        .commit();
                Toolbar toolbar = findViewById(R.id.our_bar);
                toolbar.setTitle("Настройки");
                checkOrietnation();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkOrietnation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.notes, new EmptyFragment()).addToBackStack("")
                    .commit();
        }
    }
}