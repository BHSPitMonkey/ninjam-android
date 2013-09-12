package com.stepheneisenhauer.ninjamandroid;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

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
    private final IBinder mBinder = new JamBinder();

    // Connection info
    Socket s;
    String host;
    int port;
    String username;
    String password;
    boolean anonymous;  // Connect anonymously

    // Server state
    int bpm;            // Beats Per Minute (tempo)
    int bpi;            // Beats Per Interval
    int maxChannels;    // Max number of channels allowed by server
    String topic;
    JamUIState uiState;

    // Constants indicating current state
    public enum JamUIState {
        NOT_INITIALIZED,
        NEEDS_CONNECT,
        //NEEDS_LOGIN,
        CONNECTING,
        NEEDS_AGREEMENT_ACCEPT,
        JAMMING,
        DISCONNECTED
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class JamBinder extends Binder {
        JamService getService() {
            // Return this instance of LocalService so clients can call public methods (not thread safe!)
            return JamService.this; // TODO: Replace with thread safe methods
        }
        JamUIState getUIState() {
            return uiState;
        }
        void connect(String hostString, int portInt, String user, String pass, boolean anon) {
            uiState = JamUIState.CONNECTING;

            host = hostString;
            port = portInt;
            username = user;
            password = pass;
            anonymous = anon;
            Log.d("JamService", String.format("Service connecting to %s port %d user %s", host, port, username));
            try {
                s = new Socket(host, port);
            }
            catch (UnknownHostException e) {
                // Error msg
            }
            catch (IOException e) {
                // Error msg
            }
        }
    }

    @Override
    public void onCreate() {
        // The service is being created
        uiState = JamUIState.NEEDS_CONNECT;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    @Override
    public void onDestroy() {

    }
}

