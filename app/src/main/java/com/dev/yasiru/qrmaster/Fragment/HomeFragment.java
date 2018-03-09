package com.dev.yasiru.qrmaster.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dev.yasiru.qrmaster.Dashboard;
import com.dev.yasiru.qrmaster.R;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;


public class HomeFragment extends Fragment {

    @BindView(R.id.btnRead) Button btnRead;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,v);

        userAction();

        return v;
    }


    public void userAction(){

        RxView.clicks(btnRead)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                        ((Dashboard)getActivity()).navigateTOReadQR();
                    }
                });
    }

}
