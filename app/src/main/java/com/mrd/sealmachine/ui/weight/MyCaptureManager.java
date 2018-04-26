package com.mrd.sealmachine.ui.weight;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.Window;
import android.view.WindowManager;

import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.InactivityTimer;
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CameraPreview;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/11.
 */

public class MyCaptureManager extends CaptureManager {

    private static final String TAG = "weight.MyCaptureManager";

    private ReturnResultListener mlistener;

    private DecoratedBarcodeView barcode;

    public MyCaptureManager(Activity activity,DecoratedBarcodeView barcode)
    {
        super(activity,barcode);
    }

    @Override
    protected void returnResult(BarcodeResult rawResult) {

        Log.d(TAG,"returnResult==>" +rawResult.toString());
        if(rawResult!=null)
        {
            if(mlistener!=null)
                mlistener.setResult(rawResult.getText());
        }else
        {
            if(mlistener!=null)
                mlistener.onError();
            super.returnResult(rawResult);
            return;
        }
        decode();
    }

    public  interface ReturnResultListener
    {
        void setResult(String result);
        void onError();
    }

    public void setReturnResultListener(ReturnResultListener listener)
    {
        mlistener = listener;
    }

}
