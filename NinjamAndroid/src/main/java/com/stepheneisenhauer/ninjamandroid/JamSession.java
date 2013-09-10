package com.stepheneisenhauer.ninjamandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class JamSession extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jam_session);

        // Get the intent that started this activity
        Intent intent = getIntent();
        Uri data = intent.getData();

        Log.d("JamSession", "Intent Type:");
        if (intent.getType() != null)
            Log.d("JamSession", intent.getType());
        if (data != null)
            Log.d("JamSession", data.toString());

        // Create a "connecting..." dialog
        ProgressDialog pd = ProgressDialog.show(this, "Connecting", data.toString(), true, true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jam_session, menu);
        return true;
    }
    
}
