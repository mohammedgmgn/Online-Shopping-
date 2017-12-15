package com.mahmoud.mohammed.onlineshopping.ui.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.mahmoud.mohammed.onlineshopping.R;

public class SearchActivity extends AppCompatActivity {
    SearchView searchView;
    int voiceCode=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView=findViewById(R.id.searchView);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.action_text_search){
            //  searchView.openSearch();

            //    searchView.setVisibility(View.VISIBLE);
        }
        else if(id==R.id.action_voice_search)
        {

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

}
