package com.example.artur.dispoimpoapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.artur.dispoimpoapp.DispoService;
import com.example.artur.dispoimpoapp.R;
import com.example.artur.dispoimpoapp.models.ManagerResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Artur Romasiuk
 */

public class ExitFragment extends Fragment {

    public ExitFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button exitBtn = view.findViewById(R.id.exit_btn);


        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getContext().getSharedPreferences("Info manager", Context.MODE_PRIVATE);
                String managerId = sharedPref.getString("ManagerId", "");
                String shopId = sharedPref.getString("ShopId", "");
                String managerWebService = sharedPref.getString("ManagerWebService", "");
                if (!managerWebService.equals("") && !managerId.equals("") && !shopId.equals("")) {
                    try {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(managerWebService)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        final DispoService service = retrofit.create(DispoService.class);
                        if (!Objects.equals(managerId, "") && !Objects.equals(shopId, "")) {
                            service.addSortie(managerId, shopId).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Toast.makeText(getContext(), "ENVOYE", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
                                    t.printStackTrace();
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
