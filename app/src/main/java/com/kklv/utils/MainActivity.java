package com.kklv.utils;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kklv.utils.utils.HttpCallBack;
import com.kklv.utils.utils.HttpHelper;
import com.kklv.utils.utils.HttpRequestParams;
import com.kklv.utils.utils.L;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        HttpRequestParams params = new HttpRequestParams();
        params.addParams("Html_AccOrPhone", "182028062");
        params.addParams("Html_Pass","F59BD65F7EDAFB087A81D4DCA06C4910");
        params.addParams("flag","2");
        params.addParams("loca_x","103.97556");
        params.addParams("loca_y","30.739632");
        HttpHelper.getHttpRequest("http://www.ehuu.com//apiv4/clientApiuserLogin", params, new HttpCallBack() {


            @Override
            public void onSuccess(String result) {
                L.i(result);
            }

            @Override
            public void onError(String msg) {
                L.i(msg);
            }

            @Override
            public void onFinished() {
                L.i("onFinished");
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
}
