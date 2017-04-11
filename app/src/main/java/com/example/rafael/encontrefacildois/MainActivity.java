package com.example.rafael.encontrefacildois;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView textV;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textV = (TextView) findViewById(R.id.Ola);

        Bundle extras = getIntent().getExtras();
        text = "LOGOU ESSA PORRA! \n" + extras.getString("Usuario") + " - " + extras.getString("Email");
        textV.setText(text);

    }
}
