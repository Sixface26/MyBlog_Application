package project.myblog.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import project.myblog.Helper.BottomNavigationViewHelper;
import project.myblog.R;

public class FavouriteActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.trending:
                        i = new Intent(getApplication(), TrendingActivity.class);
                        startActivity(i);
                        break;
                    case R.id.search:
                        i = new Intent(getApplicationContext(), SearchingActivity.class);
                        startActivity(i);
                        break;
                    case R.id.home:
                        i = new Intent(getApplication(), HomeActivity.class);
                        startActivity(i);
                        break;
                    case R.id.fav:
                        break;
                    case R.id.profile:
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        break;


                }
                return false;
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);

    }
}
