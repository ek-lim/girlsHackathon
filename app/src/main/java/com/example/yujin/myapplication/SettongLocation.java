package com.example.yujin.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import cardstream.CardStream;
import cardstream.CardStreamFragment;
import cardstream.CardStreamState;
import cardstream.OnCardClickListener;
import cardstream.StreamRetentionFragment;

public class SettongLocation extends SampleActivityBase implements CardStream {
    public static final String TAG = "SettongLocation";
    public static final String FRAGTAG = "PlacePickerFragment";
    static SampleActivityBase PublicMap;
    private CardStreamFragment mCardStreamFragment;

    private StreamRetentionFragment mRetentionFragment;
    private static final String RETENTION_TAG = "retention";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_map);
        PublicMap = SettongLocation.this;
        FragmentManager fm = getSupportFragmentManager();
        PlacePickerFragment fragment =
                (PlacePickerFragment) fm.findFragmentByTag(FRAGTAG);

        if (fragment == null) {
            FragmentTransaction transaction = fm.beginTransaction();
            fragment = new PlacePickerFragment();
            transaction.add(fragment, FRAGTAG);
            transaction.commit();
        }

        // Use fragment as click listener for cards, but must implement correct interface
        if (!(fragment instanceof OnCardClickListener)){
            throw new ClassCastException("PlacePickerFragment must " +
                    "implement OnCardClickListener interface.");
        }

        OnCardClickListener clickListener = (OnCardClickListener) fm.findFragmentByTag(FRAGTAG);
        mRetentionFragment = (StreamRetentionFragment) fm.findFragmentByTag(RETENTION_TAG);
        if (mRetentionFragment == null) {
            mRetentionFragment = new StreamRetentionFragment();
            fm.beginTransaction().add(mRetentionFragment, RETENTION_TAG).commit();
        } else {
            // If the retention fragment already existed, we need to pull some state.
            // pull state out
            CardStreamState state = mRetentionFragment.getCardStream();

            // dump it in CardStreamFragment.
            mCardStreamFragment =
                    (CardStreamFragment) fm.findFragmentById(R.id.fragment_cardstream);
            mCardStreamFragment.restoreState(state, clickListener);
        }
    }

    public CardStreamFragment getCardStream() {
        if (mCardStreamFragment == null) {
            mCardStreamFragment = (CardStreamFragment)
                    getSupportFragmentManager().findFragmentById(R.id.fragment_cardstream);
        }
        return mCardStreamFragment;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        CardStreamState state = getCardStream().dumpState();
        mRetentionFragment.storeCardStream(state);
    }

}
