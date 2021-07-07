package com.example.projetozeradengue.view.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.controller.ControllerDenounces;
import com.example.projetozeradengue.controller.ControllerUser;
import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.datamodel.DenouncesDataModel;
import com.example.projetozeradengue.datamodel.UserDataModel;
import com.example.projetozeradengue.model.Denounces;
import com.example.projetozeradengue.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyDenounces#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyDenounces extends Fragment implements View.OnClickListener {
    ControllerDenounces controllerDenounces;
    ControllerUser controllerUser;
    Denounces denounce = new Denounces();
    User user = new User();

    MaterialButton m_btnBack, m_btn_DeleteUser, m_btn_DeleteDen;
   TextInputEditText m_insertUserId , m_insertDenId;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyDenounces() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyDenounces.
     */
    // TODO: Rename and change types and number of parameters
    public static MyDenounces newInstance(String param1, String param2) {
        MyDenounces fragment = new MyDenounces();
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
        m_btn_DeleteUser.setOnClickListener(this);
        m_btn_DeleteDen.setOnClickListener(this);
        m_btnBack.setOnClickListener(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_denounces, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        title1("Minhas Denúncias");
        title2(("Encontre aqui suas denúncias cadastradas"));
    }

    public void    title1(String title) {

        TextView mtitle = getActivity().findViewById(R.id.title);
        mtitle.setText(title);
    }

    public void title2(String title2){
        TextView mtitle2 = getActivity().findViewById(R.id.title2);
        mtitle2.setText(title2);
    }

    private void startingComponents() {
        m_btnBack = getActivity().findViewById(R.id.btn_back);
        m_btn_DeleteUser = getActivity().findViewById(R.id.btn_deleteUser);
        m_btn_DeleteDen = getActivity().findViewById(R.id.btn_deleteDen);
        m_insertDenId = getActivity().findViewById(R.id.et_insertDenId);
        m_insertUserId = getActivity().findViewById(R.id.et_insertUserId);

    }

    private void back() {

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new MainFragment() ).commit();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_back:
                    back();
                    break;
                case R.id.btn_deleteUser:
                    delete_User();
                    break;
                case R.id.btn_deleteDen:
                    delete_denounce();

                    break;
            }
        }

    private void delete_denounce() {
        ControllerDenounces controllerDenounces = new ControllerDenounces(getActivity().getBaseContext());
        int denounceId = Integer.parseInt(m_insertDenId.getText().toString().trim());

        denounce.setId(denounceId);

        if (controllerDenounces.delete(denounce.getId())){
            Log.i(AppUtil.TAG, "excluido com sucesso");
            Toast.makeText(getActivity().getBaseContext(), "Denuncia excluida com sucesso", Toast.LENGTH_LONG).show();
            back();
        } else{
            Log.e(AppUtil.TAG, "erro ao excluir");
            Toast.makeText(getActivity().getBaseContext(), "Erro ao excluir denúncia", Toast.LENGTH_LONG).show();

        }

    }

    private void delete_User() {
        ControllerUser controllerUser = new ControllerUser(getActivity().getBaseContext());
        int userId = Integer.parseInt(m_insertUserId.getText().toString().trim());

        user.setId(userId);

        if (controllerUser.delete(user.getId())){
            Log.i(AppUtil.TAG, "excluido com sucesso");
            Toast.makeText(getActivity().getBaseContext(), "Usuário excluida com sucesso", Toast.LENGTH_LONG).show();
            back();
        } else{
            Log.e(AppUtil.TAG, "erro ao excluir");
            Toast.makeText(getActivity().getBaseContext(), "Erro ao excluir denúncia", Toast.LENGTH_LONG).show();

        }

    }

    private void update_Denounce() {
        controllerDenounces = new ControllerDenounces(getActivity().getBaseContext());

        denounce.setId(1);
        denounce.setA_city("cidade alterada");


        if (controllerDenounces.update(denounce)){
            Log.i(AppUtil.TAG, "atualizado com sucesso");
            Toast.makeText(getActivity().getBaseContext(), "Denuncia atualizada com sucesso", Toast.LENGTH_LONG).show();

        } else{
            Log.e(AppUtil.TAG, "erro ao alterar denuncia");
            Toast.makeText(getActivity().getBaseContext(), "Erro ao alterar denúncia", Toast.LENGTH_LONG).show();

        }

    }

    private void denounces_Show() {

        controllerDenounces = new ControllerDenounces(getActivity().getBaseContext());
        for (Denounces den: controllerDenounces.showDenounce(DenouncesDataModel.TABLE)) {
            Log.i("Dados Denúncia" , " "+den.getId()+" "+den.getUserId()+" "+den.getCep()+" "+den.getA_Street()+" "+den.getA_number()+" "+den.getA_complement()+" "+den.getA_district()+" "+den.getA_city()+" "+den.getA_state()+" "+den.getNote());
        }
    }


}

