package com.atriple.study.barvolume;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editWidth;
    private EditText editHeight;
    private EditText editLength;
    private Button btnCalculate;
    private TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Komponen BarVolume (Basic View and Single Activity)
        editLength = findViewById(R.id.edit_length);
        editWidth = findViewById(R.id.edit_width);
        editHeight = findViewById(R.id.edit_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        //Komponen Latihan Intent
        Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        Button btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data);

        //Listener
        btnCalculate.setOnClickListener(this);
        btnMoveActivity.setOnClickListener(this);
        btnMoveWithDataActivity.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_calculate:
                calculateBarVolume();
                break;
            case R.id.btn_move_activity:
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_move_activity_data:
                Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Boy");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5);
                startActivity(moveWithDataIntent);
                break;
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    private void calculateBarVolume(){
        String inputLength = editLength.getText().toString().trim();
        String inputWidth = editWidth.getText().toString().trim();
        String inputHeight = editHeight.getText().toString().trim();

        boolean isEmptyFields = false;
        boolean isInvalidDouble = false;

        if (TextUtils.isEmpty(inputLength)) {
            isEmptyFields = true;
            editLength.setError("Field ini tidak boleh kosong");
        }

        if (TextUtils.isEmpty(inputWidth)) {
            isEmptyFields = true;
            editWidth.setError("Field ini tidak boleh kosong");
        }

        if (TextUtils.isEmpty(inputHeight)) {
            isEmptyFields = true;
            editHeight.setError("Field ini tidak boleh kosong");
        }

        Double length = toDouble(inputLength);
        Double width = toDouble(inputWidth);
        Double height = toDouble(inputHeight);

        if (length == null) {
            isInvalidDouble = true;
            editLength.setError("Field ini harus berupa nomer yang valid");
        }

        if (width == null) {
            isInvalidDouble = true;
            editWidth.setError("Field ini harus berupa nomer yang valid");
        }

        if (height == null) {
            isInvalidDouble = true;
            editHeight.setError("Field ini harus berupa nomer yang valid");
        }

        if (!isEmptyFields && !isInvalidDouble) {
            double volume = length * width * height;
            tvResult.setText(String.valueOf(volume));
        }
    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
