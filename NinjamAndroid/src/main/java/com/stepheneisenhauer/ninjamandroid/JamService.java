package com.stepheneisenhauer.ninjamandroid;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by stephen on 9/10/13.
 *
 * This Service encapsulates the business logic of a Jam (a connection to a NINJAM server).
 *
 * This Service will be started/bound by a JamSession activity instance. During its lifetime, a
 * persistent notification should be maintained (like when in a phone call or Hangout) that can
 * bring the user back to the JamSession activity.
 *
 * This Service will maintain the TCP connection to the server, manage all communications,
 * handle audio recording and playback events, and operate the internal metronome/timing system.
 */
public class JamService extends Service {
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    // Connection info
    Socket s;
    String host;
    int port;

    // Server state
    int bpm;            // Beats Per Minute (tempo)
    int bpi;            // Beats Per Interval
    int maxChannels;    // Max number of channels allowed by server
    String topic;

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        JamService getService() {
            // Return this instance of LocalService so clients can call public methods
            return JamService.this;
        }
    }

    public IBinder onBind(Intent intent) {
        // Do setup
        Uri uri = intent.getData();
        host = uri.getHost();
        port = uri.getPort();
        try {
            s = new Socket(host, port);
        }
        catch (UnknownHostException e) {
            // Error msg
        }
        catch (IOException e) {
            // Error msg
        }

        return mBinder;
    }

    public void onDestroy() {

    }
}
