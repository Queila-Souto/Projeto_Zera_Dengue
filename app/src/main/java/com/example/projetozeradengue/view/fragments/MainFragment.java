package com.example.projetozeradengue.view.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.projetozeradengue.R;
import com.example.projetozeradengue.core.AppUtil;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class MainFragment extends Fragment implements View.OnClickListener {
MaterialButton mButtonNewDenounce, mButtonMyDenounces, mButtonMyProfile, mButtonExit;
FirebaseAuth auth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Log.d(AppUtil.TAG, "Menu - Tela principal");


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeComponets();

    }

    private void initializeComponets() {
        mButtonNewDenounce = getActivity().findViewById(R.id.btn_denounce);
        mButtonNewDenounce.setOnClickListener(this);
        mButtonMyDenounces = getActivity().findViewById(R.id.btn_my_denounce);
        mButtonMyDenounces.setOnClickListener(this);
        mButtonMyProfile = getActivity().findViewById(R.id.btn_my_profile);
        mButtonMyProfile.setOnClickListener(this);
        mButtonExit = getActivity().findViewById(R.id.btn_exit);
        mButtonExit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_denounce :
                getActivity().getSupportFragmentManager().
                        beginTransaction().replace(R.id.frameLayout2, new Denounce()).commit();
            break;
            case R.id.btn_my_denounce:
                getActivity().getSupportFragmentManager().
                        beginTransaction().replace(R.id.frameLayout2, new MyDenounces()).commit();
                break;
            case R.id.btn_my_profile:
                getActivity().getSupportFragmentManager().
                        beginTransaction().replace(R.id.frameLayout2, new Profile()).commit();
                break;
            case R.id.btn_exit:
                exitLogOff();
                break;
        }

    }

    private void exitLogOff() {
        auth.getInstance().signOut();
        Toast.makeText(getContext(),"Logout realizado com sucesso",Toast.LENGTH_LONG).show();
        getActivity().finish();


    }

}