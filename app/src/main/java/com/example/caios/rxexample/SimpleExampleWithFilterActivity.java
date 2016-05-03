package com.example.caios.rxexample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.caios.rxexample.Adapter.RecycleViewAdapterItem;
import com.example.caios.rxexample.Dummy.DummyContentItem;
import com.example.caios.rxexample.Model.Item;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by caios on 5/3/16.
 */
public class SimpleExampleWithFilterActivity extends RxAppActivity {
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecycleViewAdapterItem adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_example_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.inject(this);
        DummyContentItem.clearItems();
        adapter = new RecycleViewAdapterItem(DummyContentItem.ITEMS, new IAdapterOnClickListener() {
            @Override
            public void onClickItem(Item item) {
                Snackbar.make(recyclerView, "Click item: " + item, Snackbar.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Observable.just(1, 2, 3, 4, 5, 6) // add more numbers
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer value) {
                        return value % 2 == 0;
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Snackbar.make(recyclerView, "Complete create List", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(recyclerView, "Error create List", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Integer i) {
                        if (adapter != null) {
                            DummyContentItem.addNewItem(i);
                            adapter.notifyDataSetChanged();
                        }
                    }
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

    @Override
    public void onBackPressed() {
        finish();
        openActivity(MainActivity.class);
    }
}