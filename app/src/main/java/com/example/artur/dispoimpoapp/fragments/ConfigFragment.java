package com.example.artur.dispoimpoapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.artur.dispoimpoapp.DispoService;
import com.example.artur.dispoimpoapp.MainActivity;
import com.example.artur.dispoimpoapp.R;
import com.example.artur.dispoimpoapp.models.ManagerResponse;
import com.example.artur.dispoimpoapp.models.ManagerRow;
import com.example.artur.dispoimpoapp.models.Shop;
import com.example.artur.dispoimpoapp.models.ShopResponse;
import com.example.artur.dispoimpoapp.models.ShopRow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Artur Romasiuk
 */

public class ConfigFragment extends Fragment {

    String managerWebService;


    public ConfigFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final SharedPreferences sharedPref = getContext().getSharedPreferences("Info manager", Context.MODE_PRIVATE);

        final EditText loginET = view.findViewById(R.id.login_ev_config);
        final EditText passET = view.findViewById(R.id.password_ev_config);
        final EditText webServiceET = view.findViewById(R.id.webservice_url_tv_config);
        final Spinner shopSpinner = view.findViewById(R.id.shop_spinner_config);
        Button connectBtn = view.findViewById(R.id.connect_btn_config);
        final ArrayList<Shop> shops = new ArrayList<>();
        ArrayList<String> shopNames = new ArrayList<>();
        shopNames.add("Select shop");

        String managerName = sharedPref.getString("ManagerName", "");
        managerWebService = sharedPref.getString("ManagerWebService", "");
        final String shopId = sharedPref.getString("ShopId", "");

        loginET.setText(managerName);
        webServiceET.setText(managerWebService);

        updateShopsSpinner(shopSpinner, shops, shopNames, shopId);

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!webServiceET.getText().toString().equals("")) {
                    try {

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(webServiceET.getText().toString())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        final DispoService service = retrofit.create(DispoService.class);

                        final SharedPreferences.Editor edit = sharedPref.edit();
                        edit.putString("ManagerName", loginET.getText().toString());
                        edit.putString("ManagerWebService", webServiceET.getText().toString());
                        service.findManager(loginET.getText().toString(), passET.getText().toString()).enqueue(new Callback<ManagerResponse>() {
                            @Override
                            public void onResponse(Call<ManagerResponse> call, Response<ManagerResponse> response) {
                                ManagerResponse body = null;
                                body = response.body();
                                try {
                                    List<ManagerRow> managerRows = body.getManagerRows();
                                    for (ManagerRow row : managerRows) {
                                        edit.putString("ManagerId", row.getManager().getId());
                                        Toast.makeText(getContext(), "ENVOYE", Toast.LENGTH_LONG).show();
                                        edit.apply();
                                    }
                                    if (managerRows.size() == 0)
                                        Toast.makeText(getContext(), "Failure. Wrong login or pass", Toast.LENGTH_LONG).show();
                                } catch (NullPointerException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();
                                }
                                Log.i("Manager", String.valueOf(body));
                                new Handler().post(new Runnable() {

                                    @Override
                                    public void run() {
                                        Intent intent = getActivity().getIntent();
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                                                | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        getActivity().overridePendingTransition(0, 0);
                                        getActivity().finish();

                                        getActivity().overridePendingTransition(0, 0);
                                        startActivity(intent);
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<ManagerResponse> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                        int selectedItemPosition = shopSpinner.getSelectedItemPosition();

                        if (selectedItemPosition - 1 >= 0) {
                            Shop shop = shops.get(selectedItemPosition - 1);
                            edit.putString("ShopId", shop.getId());
                            Log.i("Manager", shop.getId());

                        } else {
                            Toast.makeText(getContext(), "Please select Shop", Toast.LENGTH_LONG).show();
                        }
                        edit.apply();
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(getContext(), "Please enter Web Service", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updateShopsSpinner(final Spinner shopSpinner, final ArrayList<Shop> shops, final ArrayList<String> shopNames, final String shopId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://direct-order.be/order/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DispoService service = retrofit.create(DispoService.class);

        service.getAllShops().enqueue(new Callback<ShopResponse>() {
            @Override
            public void onResponse(Call<ShopResponse> call, Response<ShopResponse> response) {
                Log.i("Manager", String.valueOf(response.body()));
                try {
                    List<ShopRow> rows = response.body().getRows();
                    for (ShopRow row : rows) {
                        shops.add(row.getShop());
                        shopNames.add(row.getShop().getName());
                    }
                    for (Shop shop : shops) {
                        if (shop.getId().equals(shopId)) {
                            shopSpinner.setSelection(shops.indexOf(shop) + 1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ShopResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

        ArrayAdapter<String> shopsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, shopNames);
        shopsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shopSpinner.setAdapter(shopsAdapter);
        shopSpinner.setPrompt("Shops");
    }
}
