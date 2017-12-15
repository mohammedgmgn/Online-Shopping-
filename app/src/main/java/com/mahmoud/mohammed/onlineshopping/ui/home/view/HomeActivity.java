package com.mahmoud.mohammed.onlineshopping.ui.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.authnication.login.view.activties.LoginActivty;
import com.mahmoud.mohammed.onlineshopping.base.BaseActivity;
import com.mahmoud.mohammed.onlineshopping.communication.SessionHelper;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.fragments.MyOrdersFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.books.BooksFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.elctoronics.ElectoronicsFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.homeapplication.HomeApplicationsFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.lifestyle.LifeStyleFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.offers.OffersFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.shoppingcart.view.ShoppingCartFragment;
import com.mahmoud.mohammed.onlineshopping.ui.home.component.DaggerHomeComponent;
import com.mahmoud.mohammed.onlineshopping.ui.home.component.HomeComponent;
import com.mahmoud.mohammed.onlineshopping.ui.home.modules.HomeModule;
import com.mahmoud.mohammed.onlineshopping.ui.home.presnter.HomePresnterImpl;
import com.mahmoud.mohammed.onlineshopping.utils.notification.NotificationCountSetClass;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.mauker.materialsearchview.MaterialSearchView;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, HomeActivityView {
    private Fragment fragment = null;
    Toolbar toolbar;
    HomeComponent homeComponent;
    public static int notificationCountCart = 0;
    @Inject
    HomePresnterImpl presnter;
//    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     //   searchView =  findViewById(R.id.search_view);
        homeComponent = DaggerHomeComponent.builder().homeModule(new HomeModule(this))
                .build();
        homeComponent.injectHomeActivity(this);
        initUi();

    }

    public HomeComponent getHomeComponent() {
        return homeComponent;
    }

    private void initUi() {
        presnter.setView(this);
        presnter.getMyCartListCount();
        getProductNames();
        //setDefaultFragment();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       // searchView=findViewById(R.id.searchView);
       // searchView.setQueryHint("Search View");
/*
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });
*/
        /*
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewOpened() {
                // Do something once the view is open.
            }

            @Override
            public void onSearchViewClosed() {
                // Do something once the view is closed.
            }
        });

        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Do something when the suggestion list is clicked.
                String suggestion = searchView.getSuggestionAtPosition(position);

                searchView.setQuery(suggestion, false);
            }
        });

        searchView.adjustTintAlpha(0.8f);
        searchView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(HomeActivity.this, "Long clicked position: " + i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        searchView.setOnVoiceClickedListener(new MaterialSearchView.OnVoiceClickedListener() {
            @Override
            public void onVoiceClicked() {
                Toast.makeText(HomeActivity.this, "Voice clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        //String[] arr = getResources().getStringArray(R.array.query_suggestions);

      //  searchView.addSuggestions(arr);
*/
    }

    private void getProductNames() {

    }

    private void setDefaultFragment() {
        fragment = new OffersFragment();
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        setTitle("Offers");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.home_action_search);
        //searchView = (MaterialSearchView) menu.findItem(R.id.home_action_search).getActionView();
        //searchView.item

        return true;
    }




    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Get the notifications MenuItem and
        // its LayerDrawable (layer-list)
        MenuItem item = menu.findItem(R.id.action_cart);

        NotificationCountSetClass.setAddToCart(HomeActivity.this, item, notificationCountCart);


        // force the ActionBar to relayout its MenuItems.
        // onCreateOptionsMenu(Menu) will be called again.
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
    if(id==R.id.home_action_search){
      //  searchView.openSearch();

        //    searchView.setVisibility(View.VISIBLE);
    }
        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_search) {
            startActivity(new Intent(MainActivity.this, SearchResultActivity.class));
            return true;
        }else if (id == R.id.action_cart) {

            startActivity(new Intent(MainActivity.this, CartListActivity.class));
            return true;
        }else {
            startActivity(new Intent(MainActivity.this, EmptyActivity.class));

        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_offers) {
            fragment = new OffersFragment();
            setTitle("Offers");

        } else if (id == R.id.nav_electoronics) {
            fragment = new ElectoronicsFragment();
            setTitle("Electoronics");
        } else if (id == R.id.nav_life_style) {
            fragment = new LifeStyleFragment();
            setTitle("LifeStyle");

        } else if (id == R.id.nav_home_application) {
            fragment = new HomeApplicationsFragment();
            setTitle("Home Applications");
        } else if (id == R.id.nav_books) {
            fragment = new BooksFragment();
            setTitle("Books");

        } else if (id == R.id.nav_my_orders) {
            fragment = new MyOrdersFragment();
            setTitle("My Orders");

        } else if (id == R.id.nav_cart) {
            fragment = new ShoppingCartFragment();
            setTitle("Shopping Cart");

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            FirebaseAuth auth = SessionHelper.getFirebaseAuth();
            auth.signOut();

            startActivity(new Intent(this, LoginActivty.class));

        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

        }
        return true;
    }

    @Override
    public void displayproductCartCount(int count) {
        notificationCountCart = count;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showLoadingDialog(String message) {

    }

    @Override
    public void showErrMsg(String msg) {

    }

    @Override
    public void showNoInternetMsg() {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                   // searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
       // searchView.clearSuggestions();
    }

    @Override
    protected void onResume() {
        super.onResume();
///        searchView.activityResumed();
    }

    private void clearHistory() {
   //     searchView.clearHistory();
    }

    private void clearSuggestions() {
       // searchView.clearSuggestions();
    }

    private void clearAll() {
     //   searchView.clearAll();
    }
}
