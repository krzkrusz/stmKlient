package com.example.cityklient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN";
    @BindView(R.id.editText)
    EditText upper_left_x;

    @BindView(R.id.editText2)
    EditText upper_left_y;

    @BindView(R.id.editText4)
    EditText lower_right_x;

    @BindView(R.id.editText3)
    EditText lower_right_y;

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.error_label)
    TextView error_label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    void submit() {
        Intent intent = new Intent(this, MapActivity.class);

        int x1 = Integer.parseInt(upper_left_x.getText().toString());
        int y1 = Integer.parseInt(upper_left_y.getText().toString());
        int x2 = Integer.parseInt(lower_right_x.getText().toString());
        int y2 = Integer.parseInt(lower_right_y.getText().toString());

        //jezeli prawy dolny jest lewym górnym :)
        if (x2 < x1 || y2 < y1) {
            error_label.setText("Wrong data");
            return;
        }

        intent.putExtra("upper_left_x", x1);
        intent.putExtra("upper_left_y", y1);
        intent.putExtra("x2", x2);
        intent.putExtra("y2", y2);

        PackageManager packageManager = MainActivity.this.getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent);
        } else {
            Log.d(TAG, "Activity not handled for: " + intent.getData());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        error_label.setText("");
    }
}
