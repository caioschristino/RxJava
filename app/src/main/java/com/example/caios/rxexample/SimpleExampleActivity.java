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

/**
 * Created by caios on 5/3/16.
 */
public class SimpleExampleActivity extends RxAppActivity {
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
        Observable integerObservable = Observable.create(subscriber -> {
            subscriber.onNext(1);
            subscriber.onNext(2);
            subscriber.onNext(3);
            subscriber.onNext(4);
            subscriber.onNext(5);
            subscriber.onNext(6);
            subscriber.onCompleted();
        });

        Subscriber integerSubscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                Snackbar.make(recyclerView, "Complete create List", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(recyclerView, "Error create List", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Object o) {
                if (adapter != null) {
                    DummyContentItem.addNewItem();
                    adapter.notifyDataSetChanged();
                }
            }
        };
        integerObservable.subscribe(integerSubscriber);
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