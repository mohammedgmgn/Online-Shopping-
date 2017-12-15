package com.mahmoud.mohammed.onlineshopping.ui.categories.shoppingcart.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.base.adapters.MainAdapter;
import com.mahmoud.mohammed.onlineshopping.base.adapters.onProductClickListener;
import com.mahmoud.mohammed.onlineshopping.communication.FireConstants;
import com.mahmoud.mohammed.onlineshopping.communication.FireHelper;
import com.mahmoud.mohammed.onlineshopping.communication.SessionHelper;
import com.mahmoud.mohammed.onlineshopping.models.Order;
import com.mahmoud.mohammed.onlineshopping.models.Product;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.presnter.ProductPresnterImpl;
import com.mahmoud.mohammed.onlineshopping.ui.categories.products.view.ProductView;
import com.mahmoud.mohammed.onlineshopping.ui.categories.shoppingcart.adapter.AlertDialogHelper;
import com.mahmoud.mohammed.onlineshopping.ui.categories.shoppingcart.adapter.MultiSelectAdapter;
import com.mahmoud.mohammed.onlineshopping.ui.categories.shoppingcart.adapter.RecyclerItemClickListener;
import com.mahmoud.mohammed.onlineshopping.ui.detail.view.DetailActivity;
import com.mahmoud.mohammed.onlineshopping.ui.home.view.HomeActivity;
import com.mahmoud.mohammed.onlineshopping.ui.orders.view.activities.OrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShoppingCartFragment extends Fragment implements ProductView, onProductClickListener, AlertDialogHelper.AlertDialogListener {
    @BindView(R.id.shopping_cart_recycler_view_id)
    RecyclerView recyclerView;
    @Inject
    ProductPresnterImpl presnter;
    @Inject
    MainAdapter adapter;
    boolean isMultiSelect = false;
    ActionMode mActionMode;

    ArrayList<Product> user_list = new ArrayList<>();
    ArrayList<Product> multiselect_list = new ArrayList<>();
    MultiSelectAdapter multiSelectAdapter;
    Menu context_menu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HomeActivity) getActivity()).getHomeComponent().inject(this);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        ButterKnife.bind(this, root);
        presnter.setView(this);
        initUi();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        presnter.getMyCartList();

    }

    private void initUi() {
        //    adapter.setOnProductClickListener(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        // recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        multiSelectAdapter = new MultiSelectAdapter(getContext(), user_list, multiselect_list);
        recyclerView.setAdapter(multiSelectAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isMultiSelect) {
                    multi_select(position);

                } else {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra(getString(R.string.product_key), user_list.get(position));
                    startActivity(intent);

                }

            }

            @Override
            public void onItemLongClick(View view, int position) {
                if (!isMultiSelect) {
                    multiselect_list = new ArrayList<Product>();
                    isMultiSelect = true;

                    if (mActionMode == null) {
                        mActionMode = ShoppingCartFragment.this.getActivity().startActionMode(mActionModeCallback);
                    }
                }

                multi_select(position);

            }
        }));

    }


    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_multi_select, menu);
            context_menu = menu;

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    //    if(from==1)
                {
                    if (multiselect_list.size() > 0) {
                        for (int i = 0; i < multiselect_list.size(); i++) {
                            user_list.remove(multiselect_list.get(i));
                            FireHelper.getRequiredPath(FireConstants.MY_CARTS,
                                    SessionHelper.getUser().getUid()
                                    , String.valueOf(multiselect_list.get(i).getId())).removeValue();

                        }

                        multiSelectAdapter.notifyDataSetChanged();

                        if (mActionMode != null) {
                            mActionMode.finish();
                        }
                        Toast.makeText(getContext(), "Delete Click", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                case R.id.action_order: {
                    // todo start activity with user list
                    Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
                    intent.putParcelableArrayListExtra(getString(R.string.ordered_list), multiselect_list);
                    startActivity(intent);
                    return true;
                }
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
            isMultiSelect = false;
            multiselect_list = new ArrayList<Product>();
            refreshAdapter();
        }
    };

    private void multi_select(int position) {
        if (mActionMode != null) {
            try {
                if (multiselect_list.contains(user_list.get(position)))
                    multiselect_list.remove(user_list.get(position));
                else
                    multiselect_list.add(user_list.get(position));

                if (multiselect_list.size() > 0)
                    mActionMode.setTitle("" + multiselect_list.size());
                else
                    mActionMode.setTitle("");

                refreshAdapter();

            } catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void refreshAdapter() {
        multiSelectAdapter.selected_usersList = multiselect_list;
        multiSelectAdapter.usersList = user_list;
        multiSelectAdapter.notifyDataSetChanged();
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
    public void displayProductList(List<Product> products) {
        //  adapter.setMyCartList(products);

    }

    @Override
    public void displayMyCartList(List<Product> products) {
        multiSelectAdapter.usersList.clear();
        multiSelectAdapter.usersList.addAll(products);
        multiSelectAdapter.notifyDataSetChanged();

    }

    @Override
    public void displayOrdersList(List<Order> orderList) {

    }

    @Override
    public void onProcductClicked(Product product) {

    }

    @Override
    public void onPositiveClick(int from) {
        if (from == 1) {
            if (multiselect_list.size() > 0) {
                for (int i = 0; i < multiselect_list.size(); i++)
                    user_list.remove(multiselect_list.get(i));

                multiSelectAdapter.notifyDataSetChanged();

                if (mActionMode != null) {
                    mActionMode.finish();
                }
                Toast.makeText(getContext(), "Delete Click", Toast.LENGTH_SHORT).show();
            }
        } else if (from == 2) {
            if (mActionMode != null) {
                mActionMode.finish();
            }

            Product mSample = new Product();
            user_list.add(mSample);
            multiSelectAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onNegativeClick(int from) {

    }

    @Override
    public void onNeutralClick(int from) {

    }
}