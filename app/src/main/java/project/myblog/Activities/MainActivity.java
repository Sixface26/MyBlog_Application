package project.myblog.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import project.myblog.Fragments.FourthFragment;
import project.myblog.Helper.BottomNavigationViewHelper;
import project.myblog.Fragments.HomeFragment;
import project.myblog.R;
import project.myblog.Fragments.SecondFragment;
import project.myblog.Fragments.ThirdFragment;


public class MainActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView2;
    BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPreferences;
    ImageView settings;

    HomeFragment homeFragment;
    SecondFragment secondFragment;
    ThirdFragment thirdFragment;
    FourthFragment fourthFragment;
    int mSelectedItem;
    TextView caption;

    Intent i;

    public static final String mypreference = "edit_data";
    public static final String Data = "desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = (ImageView)findViewById(R.id.settings);
        caption = (TextView)findViewById(R.id.caption);

        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Data,getResources().getString(R.string.description));
        editor.apply();
        loadCaption();

        NestedScrollView scrollView = (NestedScrollView) findViewById (R.id.nscroll);
        scrollView.setFillViewport(true);

        homeFragment=new HomeFragment();
        secondFragment=new SecondFragment();
        thirdFragment=new ThirdFragment();
        fourthFragment=new FourthFragment();
        replaceFragment(homeFragment);

        bottomNavigationView2 = (BottomNavigationView)findViewById(R.id.bottomNavigation2);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView2);
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });


        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.trending:
                        i = new Intent(getApplicationContext(),TrendingActivity.class);
                        startActivity(i);
                        break;
                    case R.id.search:
                        i = new Intent(getApplicationContext(),SearchingActivity.class);
                        startActivity(i);
                        break;
                    case R.id.home:
                        i = new Intent(getApplication(),HomeActivity.class);
                        startActivity(i);
                        break;
                    case R.id.fav:
                        i = new Intent(getApplicationContext(),FavouriteActivity.class);
                        startActivity(i);
                        break;
                    case R.id.profile:

                        break;

                }
                return false;
            }
        });

    }


    private void selectFragment(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.trending:
                replaceFragment(homeFragment);
                test(item);
                break;
            case R.id.search:
                replaceFragment(secondFragment);
                test(item);
                break;
            case R.id.home:
                replaceFragment(thirdFragment);
                test(item);
                break;
            case R.id.fav:
                replaceFragment(fourthFragment);
                test(item);
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    void test(MenuItem item) {
        for (int i = 0; i < bottomNavigationView2.getMenu().size(); i++)
        {
            mSelectedItem = item.getItemId();
            MenuItem menuItem = bottomNavigationView2.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }
    }

    public void fbpage(View view)
    {
        String url = "https://www.facebook.com/f22labs";
        Intent intent = new Intent(MainActivity.this,WebActivity.class);
        intent.putExtra("message",url);
        startActivity(intent);
    }

    public void twitterpage(View view)
    {
        String url = "https://twitter.com/f22labs?lang=en";
        Intent intent = new Intent(MainActivity.this,WebActivity.class);
        intent.putExtra("message",url);
        startActivity(intent);

    }
    public void instapage(View view)
    {
        String url = "https://www.instagram.com/?hl=en";
        Intent intent = new Intent(MainActivity.this,WebActivity.class);
        intent.putExtra("message",url);
        startActivity(intent);
    }

    public void settings(View view)
    {
        PopupMenu popup = new PopupMenu(MainActivity.this, settings);
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                finish();
                return true;
            }

        });
        popup.show();
    }

    public void edit(View view)
    {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_edit, null);
        final EditText desc = (EditText) mView.findViewById(R.id.desc);
        Button savebtn = (Button) mView.findViewById(R.id.savebtn);

        mBuilder.setView(mView);
        if (sharedPreferences.contains(Data))
        {
            desc.setText(sharedPreferences.getString(Data, ""));

        }
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!desc.getText().toString().isEmpty()) {
                    String description = desc.getText().toString();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Data, description);
                    editor.apply();
                    Toast.makeText(MainActivity.this,R.string.desc_update, Toast.LENGTH_SHORT).show();
                    loadCaption();
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this,R.string.desc_validation, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loadCaption()
    {
        if (sharedPreferences.contains(Data))
        {
            caption.setText(sharedPreferences.getString(Data, ""));

        }
    }

    @Override
    public void onBackPressed()
    {
        this.moveTaskToBack(true);

    }

}