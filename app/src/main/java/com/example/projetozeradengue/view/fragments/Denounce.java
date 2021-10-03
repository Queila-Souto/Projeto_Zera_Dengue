package com.example.projetozeradengue.view.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.controller.ControllerDenounces;
import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.core.MaskEditUtil;
import com.example.projetozeradengue.model.Denounces;
import com.example.projetozeradengue.retrofit_APIS.model.CEP;
import com.example.projetozeradengue.retrofit_APIS.model.SimpleCallback;
import com.example.projetozeradengue.retrofit_APIS.service.CEPService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Denounce#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Denounce extends Fragment implements View.OnClickListener {

    MaterialButton m_btn_back, m_btn_register_loc, m_btn_map;
    TextInputEditText m_street, m_number, m_district, m_complement, m_city, m_state, m_note, m_cep;
    ControllerDenounces controllerDenounces;
    Denounces denounce;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Denounce() {
    }


    public static Denounce newInstance(String param1, String param2) {
        Denounce fragment = new Denounce();
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
        m_btn_back.setOnClickListener(this);
        m_btn_map.setOnClickListener(this);
        m_btn_register_loc.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_denounce, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        title1("Denúncia");
        title2("Preencha as informações \n para registrar uma nova denúncia");
    }

    public void title1(String title) {

        TextView mtitle = getActivity().findViewById(R.id.title);
        mtitle.setText(title);
    }

    public void title2(String title2) {
        TextView mtitle2 = getActivity().findViewById(R.id.title2);
        mtitle2.setText(title2);
    }

    public void startingComponents() {
        //BOTOES
        m_btn_back = getActivity().findViewById(R.id.btn_back);
        m_btn_register_loc = getActivity().findViewById(R.id.btn_register_loc);
        m_btn_map = getActivity().findViewById(R.id.btn_map);

        //EDIT TEXTS
        m_cep = getActivity().findViewById(R.id.et_cep);
        m_street = getActivity().findViewById(R.id.et_street);
        m_number = getActivity().findViewById(R.id.et_number);
        m_district = getActivity().findViewById(R.id.et_district);
        m_complement = getActivity().findViewById(R.id.et_complement);
        m_city = getActivity().findViewById(R.id.et_city);
        m_state = getActivity().findViewById(R.id.et_state);
        m_note = getActivity().findViewById(R.id.et_note);


        //masks
        m_cep.addTextChangedListener(MaskEditUtil.mask(m_cep,MaskEditUtil.FORMAT_CEP));
        m_cep.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


            @Override
            public void afterTextChanged(Editable s) {
              searchforCep();
            }
        });


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                backMainFragment();
                break;
            case R.id.btn_register_loc:
                searchforCep();
                break;
            case R.id.btn_map:
                gotoMapsFragment();
                break;
        }
    }


    private void searchforCep() {
        String cep_text = m_cep.getText().toString();

        if (!cep_text.isEmpty()) {
            CEPService service = new CEPService(getContext());
            service.getCEP(cep_text, new SimpleCallback<CEP>() {
                @Override
                public void onResponse(CEP response) {
                    CEP cep = response;
                    m_street.setText(cep.getLogradouro());
                    m_district.setText(cep.getBairro());
                    m_city.setText(cep.getLocalidade());
                    m_complement.setText(cep.getComplemento());
                    m_state.setText(cep.getUf());
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(
                            getContext(),
                            "Erro ao localizar cep",
                            Toast.LENGTH_LONG).show();

                }
            });

        } else {
            Toast.makeText(
                    getContext(),
                    "CEP vazio!",
                    Toast.LENGTH_LONG).show();

        }
    }


    private void registerDen() {
        Log.d(AppUtil.TAG, "DENOUNCE: Estanciando objetos controller e model denuncia");
        controllerDenounces = new ControllerDenounces(getActivity().getBaseContext());
        denounce = new Denounces();

        Log.d(AppUtil.TAG, "DENOUNCE: Salvando dados denuncia... convertendo para string e int");
        int cep = Integer.parseInt(m_cep.getText().toString().trim());
        String street = m_street.getText().toString();
        String number = m_number.getText().toString();
        String district = m_district.getText().toString();
        String complement = m_complement.getText().toString();
        String city = m_city.getText().toString();
        String state = m_state.getText().toString();
        String note = m_note.getText().toString();

        denounce.setUserId(null);
        denounce.setCep(cep);
        denounce.setA_Street(street);
        denounce.setA_number(number);
        denounce.setA_complement(complement);
        denounce.setA_district(district);
        denounce.setA_city(city);
        denounce.setA_state(state);
        denounce.setNote(note);

        Log.d(AppUtil.TAG, "DENOUNCE: Salvando dados denuncia ");

        if (controllerDenounces.create(denounce)) {
            Log.i(AppUtil.TAG, "incluido com sucesso");
            Toast.makeText(getActivity().getBaseContext(), "Denúncia registrada com sucesso", Toast.LENGTH_LONG).show();
            back();
        } else {
            Log.e(AppUtil.TAG, "erro ao incluir");
            Toast.makeText(getActivity().getBaseContext(), "Erro ao registrar denúncia", Toast.LENGTH_LONG).show();

        }
    }

    private void back() {

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2, new Denounce()).commit();
    }


    private void backMainFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2, new MainFragment()).commit();
    }

    public void gotoMapsFragment() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout2, new MapsFragment())
                .addToBackStack(Denounce.class.getName()).commit();

    }

}

