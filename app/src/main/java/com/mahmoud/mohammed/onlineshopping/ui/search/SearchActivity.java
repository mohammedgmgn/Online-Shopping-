package com.mahmoud.mohammed.onlineshopping.ui.search;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mahmoud.mohammed.onlineshopping.R;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    ListView listView;
    String username;
    EditText searchBox;
    Button search,voice;
    int voiceCode=1;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView =  findViewById(R.id.search_list);
        searchBox=  findViewById(R.id.edit_search);
        search=  findViewById(R.id.btn_search);
        voice= findViewById(R.id.btn_voice_search);
        arrayAdapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);
/*
        FirebaseReader.getFireDataList(Product.class, new ValueCallback<ArrayList<Object>>() {
            @Override
            public void onReceiveValue(ArrayList<Object> objects) {
                List<Product> products = (List<Product>) (List<?>) objects;
                for (int i = 0; i < products.size(); i++) {
                    arrayAdapter.add(products.get(i).getProductName());
                    products.add(products.get(i));

                }

            }
        }, FireConstants.PRODUCTS_NODE, FireConstants.OFFERS_CHILD);
*/

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US);
                    startActivityForResult(intent,voiceCode);

                }catch (Exception e)
                {

                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!searchBox.getText().toString().isEmpty())
                { Intent intent=new Intent(SearchActivity.this,searchResultActivity.class);
                    intent.putExtra("search",searchBox.getText().toString());
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Enter product name!!", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==voiceCode && resultCode == this.RESULT_OK)
        {
            ArrayList<String> text=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            searchBox.setText(text.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //MenuItem item = menu.findItem(R.id.home_action_search);
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
