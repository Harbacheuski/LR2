package com.GorbachevskiViktor.lr2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.GorbachevskiViktor.lr2.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {

    private static final String SP_SAVED_DATA = "saved_data";
    private static final String KEY_SAVED_NUMBER = "saved_number";
    private static final String KEY_SAVED_NAME = "saved_name";
    private static final String KEY_SAVED_SURNAME = "saved_surname";

    private SharedPreferences sharedPreferences;

    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences=getSharedPreferences(SP_SAVED_DATA, Context.MODE_PRIVATE);

        initViews();
        checkSavedData();
    }

    private void checkSavedData() {

        String number = sharedPreferences.getString(KEY_SAVED_NUMBER,"");
        String name = sharedPreferences.getString(KEY_SAVED_NAME,"");
        String surname = sharedPreferences.getString(KEY_SAVED_SURNAME,"");

        if (Utils.hasEmptyFields(number,name,surname)) {
         binding.btnLogin.setEnabled(false);
        } else {
            binding.tvSavedData.setText(String.format(
                     "%1$s, %2$s %3$s ",
                    number,
                    name,
                    surname
            ));

            binding.btnLogin.setEnabled(true);
        }
        }
    private void initViews() {
        binding.btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.etNumber.getText().toString();
                String name = binding.etName.getText().toString();
                String surname = binding.etSurname.getText().toString();

                if (Utils.hasEmptyFields(phone, name, surname)) {
                    Toast.makeText(AuthActivity.this, "Информация введена не полностью !", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPreferences.edit().
                            putString(KEY_SAVED_NUMBER,phone).
                            putString(KEY_SAVED_NAME,name).
                            putString(KEY_SAVED_SURNAME,surname).
                          apply();

                    Bundle args = new Bundle(3);
                    args.putString(MainActivity.KEY_NAME, name);
                    args.putString(MainActivity.KEY_PHONE, phone);
                    args.putString(MainActivity.KEY_SURNAME, surname);

                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    intent.putExtras(args);
                    AuthActivity.this.startActivity(intent);
                    finish();
                }
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = sharedPreferences.getString(KEY_SAVED_NUMBER,"");
                String name = sharedPreferences.getString(KEY_SAVED_NAME,"");
                String surname = sharedPreferences.getString(KEY_SAVED_SURNAME,"");

                Bundle args = new Bundle(3);
                args.putString(MainActivity.KEY_NAME, name);
                args.putString(MainActivity.KEY_PHONE, number);
                args.putString(MainActivity.KEY_SURNAME, surname);

                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                intent.putExtras(args);
                AuthActivity.this.startActivity(intent);
                finish();
            }
        })
        ;

}}