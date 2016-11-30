package com.hormiga6.dummy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public class DummyActivity extends AppCompatActivity {
    private static final String TAG = "DummyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        new AsyncTask<Void, Void, AdvertisingIdClient.Info>() {
            @Override
            protected AdvertisingIdClient.Info doInBackground(Void... voids) {
                AdvertisingIdClient.Info info = null;
                try {
                    info = AdvertisingIdClient.getAdvertisingIdInfo(DummyActivity.this);
                } catch (Exception e) {
                    Log.d(TAG, "Cant't get adid " + e);
                }
                return info;
            }

            @Override
            protected void onPostExecute(AdvertisingIdClient.Info adid) {
                ((TextView) findViewById(R.id.textViewAdid)).setText(adid != null ? adid.getId() : "unavailable");
            }
        }.execute();
    }
}
