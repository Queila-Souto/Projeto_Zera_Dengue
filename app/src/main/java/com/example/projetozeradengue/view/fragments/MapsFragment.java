package com.example.projetozeradengue.view.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.controller.FetchAddressService;
import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.view.activity.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {

    double latitude;
    double longitude;
    boolean gpsAtivo = true;
    LocationManager locationManager;
    GoogleMap gmap;
    ClipboardManager clipboardManager;

    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    public static final int PERMISSIONS_ID = 2021;
    AddressResultReceiver resultReceiver;
    FusedLocationProviderClient fusedLocationProviderClient; // objeto necessário para manipular a bateria

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        gpsAtivo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            gmap = googleMap;

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View header = getActivity().findViewById(R.id.frameLayout1);
        header.bringToFront();
        try {
            checarGPSAtivo();
        } catch (InterruptedException e) {


        }

        return inflater.inflate(R.layout.fragment_maps, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Coordenadas", "gps ativo ->" + gpsAtivo);
        title1("Mapa");
        title2("Clique no local da denúncia");
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Log.i("teste", "LATITUDE - " + location.getLatitude() + " LONGITUDE - " + location.getLongitude());

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });


    }

    public void title1(String title) {

        TextView mtitle = getActivity().findViewById(R.id.title);
        mtitle.setText(title);

    }

    public void title2(String title2) {
        TextView mtitle2 = getActivity().findViewById(R.id.title2);
        mtitle2.setText(title2);
    }

    private void checarGPSAtivo() throws InterruptedException {
        if (gpsAtivo == true) {
            obterCoordenadas();
        } else {
            latitude = 0.00;
            longitude = 0.00;
            Toast.makeText(getContext(), "Não foi possível localizar, verifique se seu gps está ativo", Toast.LENGTH_LONG).show();
        }

    }

    @SuppressLint("MissingPermission")
    private void obterCoordenadas() throws InterruptedException {

        //tenho permissao para usar gps?
        boolean permissaoAtiva = solicitarPermissao();

        if (permissaoAtiva) {
            capturarUltimaLocalização();

        } else {
            solicitarPermissao();
        }

    }

    private boolean solicitarPermissao() {
        Toast.makeText(getContext(), "Precisamos da sua permissão para usar o GPS", Toast.LENGTH_LONG).show();
        List<String> permissoesNegadas = new ArrayList<>();

        int permissaoNegada;

        for (String permissao : this.permissions) {

            permissaoNegada = ContextCompat.checkSelfPermission(getContext(), permissao);

            if (permissaoNegada != PackageManager.PERMISSION_GRANTED) {
                permissoesNegadas.add(permissao);
            }

        }


        return true;

    }

    private void capturarUltimaLocalização() throws InterruptedException {

        @SuppressLint("MissingPermission")

        //Determina o ciclo em que será requisitado a localização, otimizando o uso da bateria
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        // As proximas linhas fazem a checagem de local a cada 15s. Não será utilizada nesse momento, por isso comentei
        //        locationRequest.setInterval(15 * 1000);
        //        locationRequest.setFastestInterval(5 * 1000);

        //Implementa o callback que se comunicará com o fusedLocationProviderClient
        LocationCallback locationCallBack = new LocationCallback() {

            @Override
            // pega o location e atribui às variáveis
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Log.e("teste", "local is null");
                    latitude = 0.0;
                    longitude = 0.0;
                    return;
                } else {
                    for (Location location : locationResult.getLocations()) {
                        Log.e("teste", "latitude ---- " + location.getLatitude());
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                        //só prossegue se o geocoder estiver inativo
                        if (!Geocoder.isPresent()) {
                            return;
                        }

                        //com o geocoder ativo, faz a decodificação do location para uma string de endereço
                        startIntentService(location);
                    }
                }
            }

        };

        //verifica permissões.
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest
                .permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager
                        .PERMISSION_GRANTED) {
            // TODO implementar um alertDialog futuramente para que o usuário possa conceder a permissão
        }

        //faz a requisição do Location
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);

        //estancia a classe que receberá o endereço decodificado
        resultReceiver = new AddressResultReceiver(null);


    }

    //prepara a intent que levará as informações para a classe FetchAddressService
    private void startIntentService(Location location) {
        Intent intent = new Intent(getContext(), FetchAddressService.class);
        intent.putExtra(AppUtil.RECEIVER, resultReceiver);
        intent.putExtra(AppUtil.LOCATION_DATA_EXTRA, location);
        getActivity().startService(intent);

    }

    //Classe que recebe o resultado do fetchAddressService
    private class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        //RECEBE O ENDEREÇO ATRAVÉS DO SERVIÇO FETCHADDRESSSERVICE
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultData == null) {
                return;
            }

            //variável que receberá o endereço decodificado
            final String addressOutput = resultData.getString(AppUtil.RESULT_DATA_KEY);

            if (addressOutput != null) {

                //prepara o modelo de string cep
                String cepMap = addressOutput.replace("[", "")
                        .replace("]", "")
                        .replace("-", "");

                //captura a última localização
                LatLng lastKnownLocation = new LatLng(latitude, longitude);
                //Move a camera e aplica o zoom
                CameraUpdate cameraUpdate = CameraUpdateFactory
                        .newLatLngZoom(lastKnownLocation, 10);
                getActivity().runOnUiThread(() -> gmap.animateCamera(cameraUpdate));

                //adiciona o Mark
                getActivity().runOnUiThread(() -> gmap.addMarker(new MarkerOptions()
                        .position(lastKnownLocation).title("Estou aqui").snippet(cepMap)));

                Log.i("teste", "Endereço capturado: " + addressOutput);
                getActivity().runOnUiThread(() -> gmap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(@NonNull @NotNull Marker marker) {
                        Log.i("teste", "cliquei no info " + addressOutput);
                        clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText(null,addressOutput);
                        clipboardManager.setPrimaryClip(clip);
                        if (clip != null){
                            Toast.makeText(getActivity(), "CEP copiado com suscesso", Toast.LENGTH_LONG ).show();
                        }

                    }
                }));



            }

        }
    }
}

