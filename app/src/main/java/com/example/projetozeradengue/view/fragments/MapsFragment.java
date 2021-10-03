package com.example.projetozeradengue.view.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.view.activity.MainActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {
    double latitude;
    double longitude;
    boolean gpsAtivo = true;
   LocationManager locationManager;

    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    public static final int PERMISSIONS_ID = 2021;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        gpsAtivo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);


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
            LatLng lastKnownLocation = new LatLng(latitude, longitude);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(lastKnownLocation, 15);

            googleMap.addMarker(new MarkerOptions().position(lastKnownLocation).title("EStou aqui"));

            googleMap.animateCamera(cameraUpdate);


        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View header = getActivity().findViewById(R.id.frameLayout1);
        header.bringToFront();
        checarGPSAtivo();

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
        Log.e("Coordenadas", "gps ativo ->"+ gpsAtivo);
        title1("Mapa");
        title2("Clique no local da denúncia");
    }

    public void title1(String title) {

        TextView mtitle = getActivity().findViewById(R.id.title);
        mtitle.setText(title);
    }

    public void title2(String title2) {
        TextView mtitle2 = getActivity().findViewById(R.id.title2);
        mtitle2.setText(title2);
    }

    private void checarGPSAtivo() {
        if (gpsAtivo == true) {
            obterCoordenadas();
        } else {
            latitude = 0.00;
            longitude = 0.00;
            Toast.makeText(getContext(), "Não foi possível localizar, verifique se seu gps está ativo", Toast.LENGTH_LONG).show();
        }

    }

    private void obterCoordenadas() {

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

    private void capturarUltimaLocalização() {

        @SuppressLint("MissingPermission")

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null){

            // Geopoint
            latitude = location.getLatitude();
            longitude = location.getLongitude();




        }
        else {
            latitude = 0;
            longitude = 0;
        }

    }
}