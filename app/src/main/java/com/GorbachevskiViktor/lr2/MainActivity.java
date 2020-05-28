package com.GorbachevskiViktor.lr2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.GorbachevskiViktor.lr2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_PHONE = ".phone";
    public static final String KEY_NAME = ".name";
    public static final String KEY_SURNAME = ".surname";

    private static final int PATH_REQUEST_CODE = 25;
    public static final String KEY_PATH_RESULT_FROM = ".path_from";
    public static final String KEY_PATH_RESULT_TO = ".path_to";

    private String phone;
    private String name;
    private String surname;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        extractArgs();
        initViews();
        fillData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PATH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String from = data.getExtras().getString(KEY_PATH_RESULT_FROM);
            String to = data.getExtras().getString(KEY_PATH_RESULT_TO);
            binding.tvPath.setText(String.format(
                    "Taxi will arrive at %1$s in 12 minutes and take you in %2$s. If you are agree click Call taxi",
                    from,
                    to
            ));
            binding.btnCallTaxi.setEnabled(true);

            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void fillData() {
        binding.tvName.setText(String.format("%1$s %2$s", name, surname));
        binding.tvPhone.setText(phone);
    }

    private void initViews() {
        binding.btnSetPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PathActivity.class);
                MainActivity.this.startActivityForResult(intent, PATH_REQUEST_CODE);
            }
        });
        binding.btnCallTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Wait for Taxi. Good luck!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void extractArgs() {
        Bundle args = getIntent().getExtras();

        phone = args.getString(KEY_PHONE);
        name = args.getString(KEY_NAME);
        surname = args.getString(KEY_SURNAME);
    }



}