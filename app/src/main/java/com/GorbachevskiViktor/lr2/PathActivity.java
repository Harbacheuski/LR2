package com.GorbachevskiViktor.lr2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.GorbachevskiViktor.lr2.databinding.ActivityPathBinding;

public class PathActivity extends AppCompatActivity {

    private ActivityPathBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPathBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
    }

    private void initViews() {
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromStreet = binding.etFromStreet.getText().toString();
                String fromHouse = binding.etFromHouse.getText().toString();
                String fromFlat = binding.etFromFlat.getText().toString();
                String toStreet = binding.etToStreet.getText().toString();
                String toHouse = binding.etToHouse.getText().toString();
                String toFlat = binding.etToFlat.getText().toString();

                if (Utils.hasEmptyFields(fromStreet, fromHouse, fromFlat, toStreet, toHouse, toFlat)) {
                    Toast.makeText(PathActivity.this, "Информация введена не полностью !", Toast.LENGTH_SHORT).show();
                } else {
                    Intent result = new Intent();

                    Bundle args = new Bundle(2);
                    args.putString(MainActivity.KEY_PATH_RESULT_FROM, String.format("%1$s, %2$s, %3$s", fromStreet, fromHouse, fromFlat));
                    args.putString(MainActivity.KEY_PATH_RESULT_TO, String.format("%1$s, %2$s, %3$s", toStreet, toHouse, toFlat));

                    result.putExtras(args);

                    setResult(Activity.RESULT_OK, result);
                    finish();
                }
            }
        });
    }



}