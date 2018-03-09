package com.dev.yasiru.qrmaster.Fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.yasiru.qrmaster.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ReadQRFragment extends Fragment implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    Dialog dialog;
    String txtQRResult;

    public ReadQRFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mScannerView = new ZXingScannerView(getActivity());
        return mScannerView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
//        Toast.makeText(getActivity(), "Contents = " + rawResult.getText() +
//                ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

        msgDialog(rawResult.getText());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(ReadQRFragment.this);
            }
        }, 2000);
    }


    public void msgDialog(String msg){

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout_result);

        final TextView txtResult = (TextView) dialog.findViewById(R.id.qrResult);
        txtResult.setText(msg);

        Button buttonok = (Button) dialog.findViewById(R.id.ok);
        buttonok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                Uri uri = Uri.fromFile(new java.io.File(msg));
                shareIntent.putExtra(Intent.EXTRA_STREAM, msg);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "QR Read Result");
                startActivity(shareIntent);

                dialog.dismiss();

            }
        });
        Button buttoncancel = (Button) dialog.findViewById(R.id.cancel);
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        dialog.show();

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
}
