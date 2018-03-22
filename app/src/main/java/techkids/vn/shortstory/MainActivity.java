package techkids.vn.shortstory;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import techkids.vn.shortstory.features.RecycleViewMainActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();
    private ActionBar actionBar;
    private MenuItem abMenuSearch, abMenuSort;  //define menu item - hide it
    private DrawerLayout drawerLayout;

    private RecycleViewMainActivity recycleViewMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.coordinator_toolbar);
        recycleViewMainActivity = new RecycleViewMainActivity(this);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        init();
    }

    private void setupNavigationDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        checkItemChecked(navigationView);
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                resetAllMenuItemsTextColor(navigationView);
                switch (item.getItemId()) {
                    case R.id.nav_all_books:
                        setTextColorForMenuItem(item, R.color.nav_all_books);
                        break;
                    case R.id.nav_bookmark:
                        setTextColorForMenuItem(item, R.color.nav_bookmark);
                        break;
                    case R.id.nav_create_collection:
                        setTextColorForMenuItem(item, R.color.nav_collection);
                        break;
                    case R.id.nav_recent:
                        setTextColorForMenuItem(item, R.color.nav_recent);
                        break;
                }
                //drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void setTextColorForMenuItem(MenuItem menuItem, @ColorRes int color) {
        SpannableString spanString = new SpannableString(menuItem.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, color)), 0, spanString.length(), 0);
        menuItem.setTitle(spanString);
    }

    private void resetAllMenuItemsTextColor(NavigationView navigationView) {
        for (int i = 0; i < navigationView.getMenu().size(); i++)
            setTextColorForMenuItem(navigationView.getMenu().getItem(i), R.color.text_primna);
    }

    private void checkItemChecked(NavigationView navigationView) {
        if (navigationView.getMenu().getItem(0).isChecked()) {
            setTextColorForMenuItem(navigationView.getMenu().getItem(0), R.color.nav_all_books);
        }
    }


    private void init() {
        setupNavigationDrawer();
        recycleViewMainActivity.setupLinearLayoutRecyclerView(); //khỏi tạo recycle view

        //INIT COLLAPSING TOOLBAR
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.coordinator_collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        final AppBarLayout appBarLayout = findViewById(R.id.coordinator_appbar);
        appBarLayout.setExpanded(true);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));

                    isShow = true;

                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
                actionBar.setDisplayHomeAsUpEnabled(isShow);
                if (abMenuSort!=null && abMenuSearch != null){
                    abMenuSearch.setVisible(isShow);
                    abMenuSort.setVisible(isShow);
                }
                actionBar.setHomeAsUpIndicator(R.drawable.ic_view_headline_24dp);

                Log.d(TAG, "onOffsetChanged: bật tắt View");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_appbar, menu);
        Log.d(TAG, "onCreateOptionsMenu: Đã khởi tạo Menu");
        abMenuSearch = menu.findItem(R.id.ab_menu_search);
        abMenuSort = menu.findItem(R.id.ab_menu_sort);
        abMenuSearch.setVisible(false);      //ẩn item menu khi chưa kéo hết Appbar
        abMenuSort.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.ab_menu_search:
                Snackbar.make(findViewById(R.id.drawer_layout), "Feature search updating", Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.ab_menu_sort:
                recycleViewMainActivity.setupGridLayoutRecyclerView();
                Snackbar.make(findViewById(R.id.drawer_layout), "Feature sort updating", Snackbar.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void listener() {

    }
}
