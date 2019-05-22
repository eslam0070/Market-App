package com.android.newvolley.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.newvolley.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    View view;
    EditText editTextName , editTextPhone , editTextEmail;
    Button buttonSave , buttonDelete , buttonRetrieve;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextName = view.findViewById(R.id.editTextName);
        editTextPhone = view.findViewById(R.id.editTextPhone);
        buttonSave = view.findViewById(R.id.button_Save);
        buttonDelete = view.findViewById(R.id.button_Delete);
        buttonRetrieve = view.findViewById(R.id.button_Retrieve);
        buttonSave.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonRetrieve.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_Save:
                Save();
                break;

            case R.id.button_Retrieve:
                Retrieve();
                break;

            case R.id.button_Delete:
                Delete();
                break;
        }

    }

    private void Save() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("EditProfile",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",editTextName.getText().toString());
        editor.putString("email",editTextEmail.getText().toString());
        editor.putString("phone",editTextPhone.getText().toString());
        editor.apply();
    }

    private void Retrieve() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("EditProfile",0);
        sharedPreferences.getString("name",editTextName.getText().toString());
        sharedPreferences.getString("email",editTextEmail.getText().toString());
        sharedPreferences.getString("phone",editTextPhone.getText().toString());
    }

    private boolean Delete() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("EditProfile",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

}
