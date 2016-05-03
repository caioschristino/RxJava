package com.example.caios.rxexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends RxAppActivity {
    @InjectView(R.id.btSimpleExample)
    Button btSimpleExample;
    @InjectView(R.id.btSimpleExampleWithFilters)
    Button btSimpleExampleWithFilters;
    @InjectView(R.id.btSimpleExampleWithFiltersAndMap)
    Button btSimpleExampleWithFiltersAndMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.inject(this);
        btSimpleExample.setOnClickListener(x -> {
            openActivity(SimpleExampleActivity.class);
        });
        btSimpleExampleWithFilters.setOnClickListener(x -> {
            openActivity(SimpleExampleWithFilterActivity.class);
        });

        btSimpleExampleWithFiltersAndMap.setOnClickListener(x -> {
            openActivity(SimpleExampleWithFilterAndMapActivity.class);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
