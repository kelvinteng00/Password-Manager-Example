package com.merive.securepass.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.merive.securepass.R;
import com.merive.securepass.elements.TypingTextView;

import static com.merive.securepass.elements.TypingTextView.typingAnimation;

public class UpdateFragment extends DialogFragment {

    TypingTextView title, version;
    ImageView download;

    public UpdateFragment() {
        /* Empty constructor (Needs) */
    }

    public static UpdateFragment newInstance(String oldVersion, String newVersion) {
        /* newInstance method */
        UpdateFragment frag = new UpdateFragment();
        Bundle args = new Bundle();
        args.putString("oldVersion", oldVersion);
        args.putString("newVersion", newVersion);
        frag.setArguments(args);
        return frag;
    }

    /* **************** */
    /* Override methods */
    /* **************** */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.update_fragment, container);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariables(view);
        setTitle();
        setVersion();

        download.setOnClickListener(this::clickDownload);
    }

    /* ************ */
    /* Init methods */
    /* ************ */

    public void initVariables(View view) {
        /* Init main variables */
        title = view.findViewById(R.id.updateTitle);
        version = view.findViewById(R.id.version);
        download = view.findViewById(R.id.download);
    }

    /* ************* */
    /* Click methods */
    /* ************* */

    public void clickDownload(View view) {
        /* Click Download Button */
        dismiss();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.website)));
        startActivity(browserIntent);
    }

    /* *************** */
    /* Another methods */
    /* *************** */

    public void setTitle() {
        typingAnimation(title, getResources().getString(R.string.new_version_released));
    }

    public void setVersion() {
        /* Set Version Text */
        typingAnimation(version, ("Download: " + getArguments().getString("oldVersion") + " → " +
                getArguments().getString("newVersion")));
    }
}
