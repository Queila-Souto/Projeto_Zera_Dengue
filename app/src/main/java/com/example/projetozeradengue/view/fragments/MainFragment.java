package com.example.projetozeradengue.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.view.activity.LoginActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
private MaterialButton m_btn_exit, m_btn_myprofile, m_btn_mydenounce, m_btn_newdenounce ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startingComponents();
        m_btn_exit.setOnClickListener(this);
        m_btn_mydenounce.setOnClickListener(this);
        m_btn_myprofile.setOnClickListener(this);
        m_btn_newdenounce.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        title1("Menu Principal");


    }

    public void    title1(String title) {

        TextView mtitle = getActivity().findViewById(R.id.title);
        mtitle.setText(title);
    }

    public void title2(String title2){
        TextView mtitle2 = getActivity().findViewById(R.id.title2);
        mtitle2.setText(title2);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_denounce:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new Denounce() ).commit();
                break;
            case R.id.btn_my_denounce:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new MyDenounces() ).commit();
                break;
            case R.id.btn_my_profile:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new Profile() ).commit();

                break;
            case R.id.btn_exit:
                logof();
                backActivity();
                break;
        }
    }

    private void logof() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getActivity().getBaseContext(),"Logout efetuado com sucesso", Toast.LENGTH_LONG).show();
    }




    private void backActivity() {
        Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
        startActivity(intent); }

    private void startingComponents(){
        m_btn_exit = getActivity().findViewById(R.id.btn_exit);
        m_btn_myprofile=getActivity().findViewById((R.id.btn_my_profile));
        m_btn_mydenounce=getActivity().findViewById((R.id.btn_my_denounce));
        m_btn_newdenounce = getActivity().findViewById(R.id.btn_denounce);

    }


}