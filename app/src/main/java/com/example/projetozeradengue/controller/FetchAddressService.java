package com.example.projetozeradengue.controller;

import android.Manifest;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.projetozeradengue.core.AppUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.transform.Result;

public class FetchAddressService extends IntentService {
    protected ResultReceiver receiver;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public FetchAddressService() {
        super("fetchAddressService");
    }

    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               Context#startService(Intent)}.
     *               This may be null if the service is being restarted after
     *               its process has gone away; see
     *               {@link Service#onStartCommand}
     *               for details.
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        Location location = intent.getParcelableExtra(AppUtil.LOCATION_DATA_EXTRA);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        receiver = intent.getParcelableExtra(AppUtil.RECEIVER);
        List<Address> adresses = null;
        try {
            adresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),
                    1);
        } catch (IOException e) {
            Log.e("teste", "serviço indisponível" + e);
        } catch (IllegalArgumentException e) {
            Log.e("teste", "latitude ou longitude inválida " + e);
        }
        if (adresses == null || adresses.isEmpty()) {
            Log.e("teste", "nenhum endereço encontrado");
            deliverResultReceiver(AppUtil.FAILURE_RESULT, "nenhum endereço encontrado", null);
        } else {
            Address address = adresses.get(0);
            List<String> addressF = new ArrayList<>();
            List<String> cepF = new ArrayList<>();

            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                cepF.add(address.getPostalCode());
                addressF.add(address.getAddressLine(i));

            }

            deliverResultReceiver(AppUtil.SUCESS_RESULT, TextUtils.join("|", addressF), cepF.toString() );
        }
    }

    private void deliverResultReceiver(int resultCode, String message, String cep) {
        Log.e("teste", "CEP - "+ cep);

        Bundle bundle = new Bundle();
        bundle.putString(AppUtil.RESULT_DATA_KEY, message);
        bundle.putString(AppUtil.RESULT_DATA_KEY, cep);
        receiver.send(resultCode, bundle);
    }


}
