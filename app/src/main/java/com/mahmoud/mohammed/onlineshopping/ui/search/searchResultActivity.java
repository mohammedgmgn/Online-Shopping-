package com.mahmoud.mohammed.onlineshopping.ui.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.communication.FirebaseReader;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.detail.view.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class searchResultActivity extends AppCompatActivity {
    ArrayList<Product> products;
    String searchKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ListView productList =  findViewById(R.id.search_result_list_id);
        products = new ArrayList<>();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        productList.setAdapter(myAdapter);
        setTitle("Search result");
        searchKey=getIntent().getStringExtra("search");
        // DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        FirebaseReader.getSearchResult(searchKey, Product.class, new ValueCallback<ArrayList<Object>>() {
            @Override
            public void onReceiveValue(ArrayList<Object> objects) {
                List<Product> products = (List<Product>) (List<?>) objects;
                for (int i = 0; i < products.size(); i++) {
                    Product product = new Product();
                    myAdapter.add(product.getProductName());
                    products.add(product);

                }

            }
        });


      /*  FirebaseReader.getFireDataList(Product.class, new ValueCallback<ArrayList<Object>>() {
            @Override
            public void onReceiveValue(ArrayList<Object> objects) {
                List<Product> products = (List<Product>) (List<?>) objects;

                for (int i = 0; i < products.size(); i++) {
                    Product product = new Product();
                    myAdapter.add(product.getProductName());
                    products.add(product);

                }

            }
        }, FireConstants.PRODUCTS_NODE, FireConstants.OFFERS_CHILD);*/

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(searchResultActivity.this, DetailActivity.class);
                i.putExtra("product", products.get(position));
                startActivity(i);
            }
        });

    }
}
