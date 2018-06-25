package com.example.artur.dispoimpoapp;

import com.example.artur.dispoimpoapp.models.Dispo;
import com.example.artur.dispoimpoapp.models.DispoResponse;
import com.example.artur.dispoimpoapp.models.EventResponse;
import com.example.artur.dispoimpoapp.models.Manager;
import com.example.artur.dispoimpoapp.models.ManagerResponse;
import com.example.artur.dispoimpoapp.models.ShopResponse;
import com.example.artur.dispoimpoapp.models.TimeResponse;
import com.example.artur.dispoimpoapp.models.WeekBlokedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Artur Romasiuk
 */

public interface DispoService {

    @GET("rest_models/search/Manager/rest_models.json")
    Call<ManagerResponse> findManager (@Query("name") String name, @Query("pwd") String password);

    @GET("rest_models/search/Shop/rest_models.json")
    Call<ShopResponse> getAllShops ();

    @GET("rest_models/add/Sortie/add.json")
    Call<Void> addSortie (@Query("login") String managerId, @Query("shop") String shopId);

    @GET("rest_models/search/Event/rest_models.json")
    Call<EventResponse> findEventsForManager (@Query("date1") String date1, @Query("date2") String date2,
                                              @Query("manager_id") String managerId,  @Query("shop_id") String shop_id);

    @GET("rest_models.json?model=Event")
    Call<EventResponse> getAllEvents ();

    @GET("rest_models/search/Dispo/rest_models.json")
    Call<DispoResponse> findDispoForManager (@Query("date1") String date1, @Query("date2") String date2,
                                              @Query("user") String managerId, @Query("shop") String shop);

    @GET("rest_models/search/Dispo/rest_models.json")
    Call<DispoResponse> findDispoForManager (@Query("date") String date1, @Query("user") String managerId);

    @GET("rest_models/add/Dispo/add.json")
    Call<Void> addDispo (@Query("user") String managerId, @Query("shop") String shopId, @Query("date") String date,
                   @Query("range") String range, @Query("number_of_hours") String numberOfHours, @Query("statut") String statut);

    @GET("rest_models/edit/Dispo/{dispoId}/edit.json")
    Call<Void> editDispo (@Path("dispoId") String dispoId, @Query("user") String managerId, @Query("shop") String shopId, @Query("date") String date,
                         @Query("range") String range, @Query("number_of_hours") String numberOfHours, @Query("statut") String statut);

    @GET("rest_models.json?model=Time")
    Call<TimeResponse> getAllTimeRanges ();

    @GET("rest_models/search/WeekBlocked/rest_models.json")
    Call<WeekBlokedResponse> findBlockedWeeksForManager (@Query("user") String user);

}