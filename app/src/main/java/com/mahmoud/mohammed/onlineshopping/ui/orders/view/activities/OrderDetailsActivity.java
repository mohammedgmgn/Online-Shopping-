package com.mahmoud.mohammed.onlineshopping.ui.orders.view.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.base.adapters.onOrderedProductClicked;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.detail.component.DetailComponent;
import com.mahmoud.mohammed.onlineshopping.ui.home.component.HomeComponent;
import com.mahmoud.mohammed.onlineshopping.ui.orders.adapter.RequiredOrderedListAdapter;
import com.mahmoud.mohammed.onlineshopping.ui.orders.component.DaggerOrderDetailsComponent;
import com.mahmoud.mohammed.onlineshopping.ui.orders.component.OrderDetailsComponent;
import com.mahmoud.mohammed.onlineshopping.ui.orders.module.OrderDetailsModule;
import com.mahmoud.mohammed.onlineshopping.ui.orders.presnter.OrderDetailsPresenterImp;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.interfaces.OrderDetailsViews;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailsActivity extends AppCompatActivity implements OrderDetailsViews, onOrderedProductClicked {
    @BindView(R.id.price_id_tv)
    TextView totalPriceTv;
    @BindView(R.id.pick_location_tv_id)
    TextView pickLocationTv;
    @BindView(R.id.ordered_products_recycler_view_id)
    RecyclerView ordersRecyclerView;
    @BindView(R.id.confirm_btn_id)
    Button confirmBtn;
    @Inject
    OrderDetailsPresenterImp presenter;
    @Inject
    RequiredOrderedListAdapter adapter;
    OrderDetailsComponent detailsComponent;
    public static int PLACE_PICKER_REQUEST = 1;
    private String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        detailsComponent = DaggerOrderDetailsComponent.builder().orderDetailsModule(new OrderDetailsModule(this))
                .build();
        detailsComponent.injectOrderDetailActivity(this);
        initUi();

        //    List<Product> orderList = getIntent().getExtras().getParcelableArrayList(getString(R.string.ordered_list));
        //  Toast.makeText(this, orderList.size() + "", Toast.LENGTH_SHORT).show();
    }
    public OrderDetailsComponent getDetailComponent() {
        return detailsComponent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.calculateTotalPrice();
    }


    private void initUi() {
        presenter.setView(this);
        presenter.setPresenterData(getIntent());
        presenter.displayOrderdList();
        adapter.setOnProductClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ordersRecyclerView.setLayoutManager(layoutManager);
        ordersRecyclerView.setAdapter(adapter);
        pickLocationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(OrderDetailsActivity.this, MapsActivity.class), PLACE_PICKER_REQUEST);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (address != null) {
                   presenter.confirmOrderPressed(address);
                } else {
                    Toast.makeText(OrderDetailsActivity.this, "Please select location", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
    public void displayTotalPrice(double totalPrice) {
        totalPriceTv.setText(String.valueOf(totalPrice) + " $");
    }

    @Override
    public void displayOrderdList(List<Product> orderdList) {
        adapter.setProductsList(orderdList);
    }

    @Override
    public void showListEmptyError() {
        Toast.makeText(this,"you have no items",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOrderSuccessMessage() {
        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        finish();

    }


    @Override
    public void onOrderedProcductClicked(Product product, int position) {

        CharSequence choices[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
                //     Toast.makeText(OrderDetailsActivity.this, which + "", Toast.LENGTH_SHORT).show();
                if (which == 0) {
                    // edit idea
                    // showEditDialog(idea);
                    showEditDialog(product);

                } else {
                    presenter.removeOrderedItem(position);
                    presenter.calculateTotalPrice();


                }
            }
        });
        builder.show();

    }

    private void showEditDialog(Product product) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText quantity = dialogView.findViewById(R.id.quantity_amount);

        quantity.setText(String.valueOf(product.getQuantity()));

        dialogBuilder.setTitle("Edit Quantity");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                //  adapter.updateAdapter();
                product.setQuantity(Double.parseDouble(quantity.getText().toString()));
                presenter.editOrderedItem(product);
                presenter.calculateTotalPrice();


                //   mPresenter.editIdea(oldIdea, Title.getText().toString(), Content.getText().toString());

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            {
                 address = data.getExtras().getString("Address");
                pickLocationTv.setText(address);


                //  Place selectedPlace = PlacePicker.getPlace(data, this);
                // Do something with the place
            }
        }
    }

}
