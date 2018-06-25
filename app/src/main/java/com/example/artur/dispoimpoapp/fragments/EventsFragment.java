package com.example.artur.dispoimpoapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.artur.dispoimpoapp.DispoService;
import com.example.artur.dispoimpoapp.MainActivity;
import com.example.artur.dispoimpoapp.R;
import com.example.artur.dispoimpoapp.models.Event;
import com.example.artur.dispoimpoapp.models.EventResponse;
import com.example.artur.dispoimpoapp.models.EventRow;
import com.example.artur.dispoimpoapp.models.EventsTime;
import com.example.artur.dispoimpoapp.models.Manager;
import com.example.artur.dispoimpoapp.models.Time;
import com.example.artur.dispoimpoapp.models.TimeResponse;
import com.example.artur.dispoimpoapp.models.TimeRow;

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

public class EventsFragment extends Fragment {

    TableLayout tableEvent;
    ArrayList<String> datesFor3Weeks = new ArrayList();
    ArrayList<String> datesFor3WeeksForRequest = new ArrayList();
    ArrayList<String> timeRangeNames = new ArrayList();
    ArrayList<Time> timeRanges = new ArrayList();
    ArrayList<Event> eventsFromDB = new ArrayList();
    ArrayList<EventRow> eventRowsFromDB = new ArrayList();
    String managerId;
    String shopId;
    TableRow tableRow[];
    Button[][] buttons;
    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
//    ArrayList<String> range= new ArrayList().add("");

    public EventsFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getContext().getSharedPreferences("Info manager", Context.MODE_PRIVATE);
        managerId = sharedPref.getString("ManagerId", "");
        shopId = sharedPref.getString("ShopId", "");
        String managerWebService = sharedPref.getString("ManagerWebService", "");
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
                            getFragmentManager().beginTransaction().detach(EventsFragment.this).attach(EventsFragment.this).commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TimeResponse> call, Throwable t) {

                    }
                });

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date()); // Устанавливаем текущее время
//        TextView weekNumberTV = inflate.findViewById(R.id.week_number);
//        SimpleDateFormat weekNumberFormat = new SimpleDateFormat( "ww");
//        weekNumberTV.setText("WEEK №"+weekNumberFormat.format(calendar.getTime())+1);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                calendar.add(Calendar.DAY_OF_WEEK, -7);
                for (int i = 0; i < 8; i++) {
                    Date time = calendar.getTime();
                    SimpleDateFormat format = new SimpleDateFormat("E dd/MM");
                    SimpleDateFormat formatForRequest = new SimpleDateFormat("yyyy-MM-dd");
                    if (i != 0) {
                        datesFor3Weeks.add(format.format(time));
                        datesFor3WeeksForRequest.add(formatForRequest.format(time));
                    }
                    calendar.add(Calendar.DAY_OF_WEEK, 1);
                }


                udpateEventFromDB(service);
            } catch (IllegalArgumentException e) {
                Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void udpateEventFromDB(DispoService service) {
        service.findEventsForManager(datesFor3WeeksForRequest.get(0),
                datesFor3WeeksForRequest.get(datesFor3WeeksForRequest.size() - 1), managerId, shopId).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                List<EventRow> rows = null;
                try {
                    rows = response.body().getRows();
                    eventsFromDB.clear();
                    for (EventRow row : rows) {
                        eventsFromDB.add(row.getEvent());
                        eventRowsFromDB.add(row);
                    }
                    getFragmentManager().beginTransaction().detach(EventsFragment.this).attach(EventsFragment.this).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Wrong Web Service", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_events, container, false);

        tableEvent = (TableLayout) inflate.findViewById(R.id.table_event);


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
                    for (EventRow eventRow : eventRowsFromDB) {
                        if (eventRow.getEvent().getEventDate().equals(datesFor3WeeksForRequest.get(col - 1))
                                && eventRow.getEvent().getShopId().equals(shopId)
                                && eventRow.getEventsManager().getManagerId().equals(managerId)
                                && eventRow.getEventsTime().getTimeId().equals(timeRanges.get(line - 1).getId())) {
                            if (eventRow.getEventsTime().getSB().equals("1"))
                                buttons[line][col].setBackgroundColor(Color.parseColor("#FFA500"));
                            else
                                buttons[line][col].setBackgroundColor(Color.parseColor("#009933"));
                        }
                    }
                }
//                buttons[line][col].setLayoutParams(layoutParams);
                buttons[line][col].setLayoutParams(layoutParams);
                buttons[line][col].setVisibility(View.VISIBLE);
                tableRow[line].addView(buttons[line][col]);
            }
        }
        for (int i = 0; i < timeRangeNames.size() + 1; i++) {
            tableEvent.addView(tableRow[i], new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

        return inflate;
    }
}
