package com.example.projetozeradengue.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.controller.ControllerUser;
import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.datamodel.UserDataModel;
import com.example.projetozeradengue.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    MaterialTextView tvName;
    MaterialTextView tvBornDate;
    MaterialTextView tvAge;
    MaterialTextView tvEmail;
    private User user ;
    private ControllerUser controllerUser ;
    private MaterialButton btn_back;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private void changeComponents(){
        captureData("nameUser",tvName);
        captureData("email",tvEmail);
        captureData("email",tvBornDate);
        tvBornDate.setText(user.getDob().toString());
    }
    private void captureData(String fillUsersTable, TextView textView) {

       DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(auth.getCurrentUser().getUid()).child(fillUsersTable).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                             String dataCaptured = String.valueOf(task.getResult().getValue());

                            textView.setText(dataCaptured);

                        }
                    }
                });


        //tvAge.setText(calculateAge());
    }


        public Integer calculateAge() {
            GregorianCalendar hj=new GregorianCalendar();
            GregorianCalendar nascimento=new GregorianCalendar();
            if(user.getDob() !=null){
                nascimento.setTime(user.getDob());
            }
            int anohj=hj.get(Calendar.YEAR);
            int anoNascimento=nascimento.get(Calendar.YEAR);
            return new Integer(anohj-anoNascimento);
        }


    private void startingComponents() {
        tvName = getActivity().findViewById(R.id.textNameProfile);
        tvAge = getActivity().findViewById(R.id.textAgeProfile);
        tvBornDate = getActivity().findViewById(R.id.textBodProfile);
        tvEmail = getActivity().findViewById(R.id.textEmailProfile);

    }


    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    btn_back = getActivity().findViewById(R.id.btn_Back);
    btn_back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            backMainFragment();
        }
    });

    }

    @Override
    public void onResume() {
        super.onResume();
        startingComponents();
        changeComponents();
        title1("Recuperar nome do Banco de Dados");
        title2("Informações de Perfil");

    }

    public void title1(String title) {

        TextView mtitle = getActivity().findViewById(R.id.title);
        mtitle.setText(title);
    }

    public void title2(String title2){
        TextView mtitle2 = getActivity().findViewById(R.id.title2);
        mtitle2.setText(title2);
    }
    private void backMainFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2, new MainFragment()).commit();
    }

    private void update_User(){
        controllerUser = new ControllerUser(getActivity().getBaseContext());

        if (controllerUser.update(user)){
            Toast.makeText(getActivity().getBaseContext(),"Usuario "+user.getNameUser()+" alterado com sucesso....",Toast.LENGTH_LONG).show();
            Log.i(AppUtil.TAG, "Dado alterado");
        } else {
            Toast.makeText(getActivity().getBaseContext(),"Usuario "+user.getNameUser()+" nao foi possivel alterar....",Toast.LENGTH_LONG).show();

            Log.e(AppUtil.TAG, "Não foi possível alterar");
        }
    }

    private void user_Show() throws ParseException {

        controllerUser = new ControllerUser(getActivity().getBaseContext());
        for (User user : controllerUser.showUser(UserDataModel.TABLE)) {
            Log.i("Dados Usuarios", " " + user.getId() + " " + user.getNameUser() + " " + user.getDob() + " " + user.getEmail());
        }
    }
}