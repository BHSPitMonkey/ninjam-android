package com.stepheneisenhauer.ninjamandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;

public class JamSession extends Activity implements LoginDialogFragment.LoginDialogListener, ServiceConnection {

    JamService.JamBinder jamBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jam_session);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // Bind to a/the JamService and find out what's going on
        //JamService service = new JamService();
        Intent svcIntent = new Intent(this, JamService.class);
        svcIntent.setData(intent.getData());
        bindService(svcIntent, this, Context.BIND_AUTO_CREATE);
        // onServiceConnected will be called once the service is bound and connected.
    }

    // Called by JamService when service becomes connected
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        jamBinder = (JamService.JamBinder)iBinder;

        // We should now be able to check the service's state. Or ask the service to update our state. Or something.
        Log.d("JamSession", String.format("Service uiState appears to be: %d", jamBinder.getUIState().ordinal()));

        switch (jamBinder.getUIState()) {
            case NEEDS_CONNECT:
                // Produce the login dialog
                LoginDialogFragment ldf = new LoginDialogFragment();
                ldf.show(getFragmentManager(), "LoginDialogFragment");
                break;
            case CONNECTING:
                ProgressDialog pd = ProgressDialog.show(this, "Connecting", "Hang tight...", true, false);
                break;
            default:
                Log.d("JamSession", "Unhandled uiState!");
        }
    }

    // Called by JamService when service becomes disconnected
    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jam_session, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.


    }

    // Called when the Connect button is clicked in the login dialog
    //@Override
    public void onLoginClick(String username, String password) {
        // Set the wheels in motion...

        // Create a "connecting..." dialog
        ProgressDialog pd = ProgressDialog.show(this, "Connecting", "Hang tight...", true, false);

        // Build a nice Intent Uri for our JamService
        /*
        Uri uri = (new Uri.Builder())
                .appendPath(getIntent().getData().getPath())
                .appendQueryParameter("user", username)
                .appendQueryParameter("anon", "1") // TODO: Allow for non-anon
                .appendQueryParameter("pass", password)
                .build();

        // Bind a new JamService
        JamService service = new JamService();
        this.bindService(new Intent(Intent.ACTION_VIEW, uri), this, BIND_IMPORTANT); */
    }

    // Called when the Cancel button is clicked in the login dialog
    //@Override
    public void onCancelClick() {
        // TODO: Call a method that bails and closes the Activity
    }


}
