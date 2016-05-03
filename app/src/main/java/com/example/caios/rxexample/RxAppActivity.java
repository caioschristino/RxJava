package com.example.caios.rxexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;

/**
 * Created by caios on 5/3/16.
 */
public class RxAppActivity extends AppCompatActivity {

    public void openActivity(final Class activity) {
        openActivity(activity, null);
    }

    private void openActivity(final Class activity, final Intent extras) {
        final Intent intent = new Intent(this, activity);
        if (extras != null) {
            intent.putExtras(extras);
        }
        startActivity(intent);
        finish();
    }
}
