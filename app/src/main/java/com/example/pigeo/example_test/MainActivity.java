package com.example.pigeo.example_test;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerFragment.DatePickerInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toastButton = findViewById(R.id.toastButton);
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "Alerte catastrophique", Toast.LENGTH_LONG).show();
            }
        });

        Button choisirButton = findViewById(R.id.dateButton);
        choisirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentManager fm = getSupportFragmentManager();
                DatePickerFragment dpf = new DatePickerFragment();
                dpf.attach(MainActivity.this);
                dpf.show(fm, "Date picker");
            }
        });

    }


    @Override
    public void onDatePicked(Date datePicked) {
        Toast.makeText(MainActivity.this, datePicked.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
        return true;
    }
}
