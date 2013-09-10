package com.stepheneisenhauer.ninjamandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A fragment representing a single Server detail screen.
 * This fragment is either contained in a {@link ServerListActivity}
 * in two-pane mode (on tablets) or a {@link ServerDetailActivity}
 * on handsets.
 */
public class ServerDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private NinjamServerSet.NinjamServer mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ServerDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = NinjamServerSet.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_server_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.server_detail_text)).setText(mItem.name);
        }

        // Set the "connect" button's onClick handler
        final Button button = (Button) rootView.findViewById(R.id.server_connect_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("ServerDetailFragment", "You tapped the button! uriString is...");
                String uriString = String.format("ninjam://%s", mItem.host);
                Log.d("ServerDetailFragment", uriString);
                Intent ninjamIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
                startActivity(ninjamIntent);
            }
        });

        return rootView;
    }
}
