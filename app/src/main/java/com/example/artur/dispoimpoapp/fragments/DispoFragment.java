package com.example.artur.dispoimpoapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.artur.dispoimpoapp.DispoService;
import com.example.artur.dispoimpoapp.R;
import com.example.artur.dispoimpoapp.models.Dispo;
import com.example.artur.dispoimpoapp.models.DispoResponse;
import com.example.artur.dispoimpoapp.models.DispoRow;
import com.example.artur.dispoimpoapp.models.Time;
import com.example.artur.dispoimpoapp.models.TimeResponse;
import com.example.artur.dispoimpoapp.models.TimeRow;
import com.example.artur.dispoimpoapp.models.WeekBlocked;
import com.example.artur.dispoimpoapp.models.WeekBlockedRow;
import com.example.artur.dispoimpoapp.models.WeekBlokedResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Artur Romasiuk
 */

public class DispoFragment extends Fragment {

    TableLayout tableDispo;
    ArrayList<String> datesFor3Weeks = new ArrayList();
    ArrayList<String> datesFor3WeeksForRequest = new ArrayList();
    ArrayList<String> weekNumberFor3Weeks = new ArrayList();
    ArrayList<String> timeRangeNames = new ArrayList();
    ArrayList<WeekBlocked> blockedWeeks = new ArrayList();
    ArrayList<Time> timeRanges = new ArrayList();
    ArrayList<Dispo> disposFromDB = new ArrayList();
    String managerId;
    String shopId;
    String managerWebService;
    TableRow tableRow[];
    Button[][] buttons;
    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
//    ArrayList<String> range= new ArrayList().add("");

    public DispoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getContext().getSharedPreferences("Info manager", Context.MODE_PRIVATE);
        managerId = sharedPref.getString("ManagerId", "");
        shopId = sharedPref.getString("ShopId", "");
        managerWebService = sharedPref.getString("ManagerWebService", "");
        if (!managerWebService.equals("") && !managerId.equals("") && !shopId.equals("")) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(managerWebService)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                DispoService service = retrofit.create(DispoService.class);
                service.getAllTimeRanges().enqueue(new Callback<TimeResponse>() {
                    @Override
                    public void onResponse(Call<TimeResponse> call, Response<TimeResponse> response) {
                        List<TimeRow> rows = null;
                        try {
                            rows = response.body().getRows();
                            for (TimeRow row : rows) {
                                timeRangeNames.add(row.getTime().getName());
                                timeRanges.add(row.getTime());
                            }
                            getFragmentManager().beginTransaction().detach(DispoFragment.this).attach(DispoFragment.this).commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TimeResponse> call, Throwable t) {

                    }
                });

                service.findBlockedWeeksForManager(managerId).enqueue(new Callback<WeekBlokedResponse>() {
                    @Override
                    public void onResponse(Call<WeekBlokedResponse> call, Response<WeekBlokedResponse> response) {
                        List<WeekBlockedRow> rows = null;
                        try {
                            rows = response.body().getRows();
                            for (WeekBlockedRow row : rows) {
                                blockedWeeks.add(row.getWeekBlocked());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeekBlokedResponse> call, Throwable t) {

                    }
                });

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date()); // Устанавливаем текущее время
//        TextView weekNumberTV = inflate.findViewById(R.id.week_number);
//        SimpleDateFormat weekNumberFormat = new SimpleDateFormat( "ww");
//        weekNumberTV.setText("WEEK №"+weekNumberFormat.format(calendar.getTime())+1);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                for (int i = 0; i < 15; i++) {
                    Date time = calendar.getTime();
                    SimpleDateFormat format = new SimpleDateFormat("E dd/MM");
                    SimpleDateFormat formatForRequest = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat formatForWeekNumber = new SimpleDateFormat("w");

                    if (i != 0) {
                        datesFor3Weeks.add(format.format(time));
                        datesFor3WeeksForRequest.add(formatForRequest.format(time));
                        weekNumberFor3Weeks.add(formatForWeekNumber.format(time));
                    }
                    calendar.add(Calendar.DAY_OF_WEEK, 1);
                }

                udpateDispoFromDB(service);
            } catch (Exception e) {
                Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void udpateDispoFromDB(DispoService service) {
        service.findDispoForManager(datesFor3WeeksForRequest.get(0),
                datesFor3WeeksForRequest.get(datesFor3WeeksForRequest.size() - 1), managerId, shopId).enqueue(new Callback<DispoResponse>() {
            @Override
            public void onResponse(Call<DispoResponse> call, Response<DispoResponse> response) {
                List<DispoRow> rows = null;
                try {
                    rows = response.body().getRows();
                    disposFromDB.clear();
                    for (DispoRow row : rows) {
                        disposFromDB.add(row.getDispo());
                    }
                    getFragmentManager().beginTransaction().detach(DispoFragment.this).attach(DispoFragment.this).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DispoResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_dispo, container, false);

        tableDispo = (TableLayout) inflate.findViewById(R.id.table_dispo);
        Button updateButton = (Button) inflate.findViewById(R.id.upadte_btn);


        tableRow = new TableRow[timeRangeNames.size() + 1];
        buttons = new Button[timeRangeNames.size() + 1][datesFor3Weeks.size() + 1];

        for (int line = 0; line < timeRangeNames.size() + 1; line++) {
            tableRow[line] = new TableRow(getContext());
            tableRow[line].setLayoutParams(layoutParams);
            tableRow[line].setVisibility(View.VISIBLE);
        }
        for (int line = 0; line < timeRangeNames.size() + 1; line++) {
            for (int col = 0; col < datesFor3Weeks.size() + 1; col++) {
                buttons[line][col] = new Button(getContext());
                if (col != 0 && line == 0) {
                    buttons[line][col].setText(datesFor3Weeks.get(col - 1));
                } else if (col == 0 && line != 0) {
                    buttons[line][col].setText(timeRangeNames.get(line - 1));
                } else if (col == 0 && line == 0) {
                } else {
                    buttons[line][col].setText("DISPO");
                    buttons[line][col].setBackgroundColor(Color.parseColor("#009933"));
                    buttons[line][col].setTag(R.string.dispo_tag, "1");
                    for (Dispo dispo : disposFromDB) {
                        if (dispo.getDate().equals(datesFor3WeeksForRequest.get(col - 1) + " 00:00:00") &&
                                dispo.getRange().equals(timeRanges.get(line - 1).getId())) {
                            buttons[line][col].setTag(R.string.dispo_id_tag, dispo.getId());
                            if (dispo.getStatut().equals("0")) {
                                buttons[line][col].setText("IMPO");
                                buttons[line][col].setBackgroundColor(Color.parseColor("#993300"));
                                buttons[line][col].setTag(R.string.dispo_tag, "0");
                            }
                        }
                    }
                    buttons[line][col].setTag(R.string.date_tag, datesFor3WeeksForRequest.get(col - 1));
                    buttons[line][col].setTag(R.string.time_range_tag, timeRanges.get(line - 1));
                    boolean wasWeekInWeekBlockedResponse = false;
                    for (WeekBlocked weekBlocked : blockedWeeks) {
                        if (weekBlocked.getWeek().equals(weekNumberFor3Weeks.get(col - 1))) {
                            wasWeekInWeekBlockedResponse = true;
                            if (weekBlocked.getStatut().equals("0")) {
                                buttons[line][col].setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Button button = (Button) view;
                                        if (button.getText().equals("DISPO")) {
                                            button.setText("IMPO");
                                            button.setTag(R.string.dispo_tag, "0");
                                            button.setBackgroundColor(Color.parseColor("#993300"));
                                        } else {
                                            button.setText("DISPO");
                                            button.setTag(R.string.dispo_tag, "1");
                                            button.setBackgroundColor(Color.parseColor("#009933"));
                                        }
                                    }
                                });
                            }
                        }
                    }
                    if (!wasWeekInWeekBlockedResponse) {
                        buttons[line][col].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Button button = (Button) view;
                                if (button.getText().equals("DISPO")) {
                                    button.setText("IMPO");
                                    button.setTag(R.string.dispo_tag, "0");
                                    button.setBackgroundColor(Color.parseColor("#993300"));
                                } else {
                                    button.setText("DISPO");
                                    button.setTag(R.string.dispo_tag, "1");
                                    button.setBackgroundColor(Color.parseColor("#009933"));
                                }
                            }
                        });
                    }
                }

                buttons[line][col].setLayoutParams(layoutParams);
                buttons[line][col].setVisibility(View.VISIBLE);
                tableRow[line].addView(buttons[line][col]);
            }
        }
        for (int i = 0; i < timeRangeNames.size() + 1; i++) {
            tableDispo.addView(tableRow[i], new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(managerWebService)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final boolean[] wasUpdated = new boolean[1];

                DispoService service = retrofit.create(DispoService.class);
                for (int line = 1; line < timeRangeNames.size() + 1; line++) {
                    for (int col = 1; col < datesFor3Weeks.size() + 1; col++) {
                        String date = (String) buttons[line][col].getTag(R.string.date_tag);
                        Time range = (Time) buttons[line][col].getTag(R.string.time_range_tag);
                        String dispoStatus = (String) buttons[line][col].getTag(R.string.dispo_tag);
                        Log.i("Dispo request", managerId + " " + shopId + " " + date + " " + range.getId() + " " +
                                range.getHours() + " " + dispoStatus);
                        Object tagDispoId = buttons[line][col].getTag(R.string.dispo_id_tag);
                        final int finalLine = line;
                        final int finalCol = col;
                        if (tagDispoId == null) {
                            service.addDispo(managerId, shopId, date,
                                    range.getId(), range.getHours(), dispoStatus).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (finalLine == timeRangeNames.size() && finalCol == datesFor3Weeks.size()) {
                                        Toast.makeText(getContext(), "ENVOYER", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    if (finalLine == timeRangeNames.size() && finalCol == datesFor3Weeks.size()) {
                                        Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            String dispoId = (String) tagDispoId;
                            service.editDispo(dispoId, managerId, shopId, date,
                                    range.getId(), range.getHours(), dispoStatus).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (finalLine == timeRangeNames.size() && finalCol == datesFor3Weeks.size()) {
                                        Toast.makeText(getContext(), "ENVOYER", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    if (finalLine == timeRangeNames.size() && finalCol == datesFor3Weeks.size()) {
                                        Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                }
                udpateDispoFromDB(service);
            }
        });

        return inflate;
    }
}
