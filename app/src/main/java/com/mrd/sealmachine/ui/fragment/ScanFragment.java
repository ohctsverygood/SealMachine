package com.mrd.sealmachine.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.mrd.sealmachine.R;
import com.mrd.sealmachine.base.BaseFragment;

import java.util.List;


public class ScanFragment extends BaseFragment{



    private DecoratedBarcodeView codeView;
    private static final String TAG = "fragment.ScanFragment";


    public ScanFragment()
    {
        title="请求封条";
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_scan, container, false);
        codeView = (DecoratedBarcodeView) contentView.findViewById(R.id.dbv_custom);
        codeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                Log.d(TAG,"result =="+result.toString());
                if(result!=null)
                    if(mListener!=null)
                        mListener.successful(result);
                else
                    if(mListener!=null)
                        mListener.error();
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {
                Log.d(TAG,"result =="+resultPoints.toString());
            }
        });
        codeView.pause();
        return contentView;
    }

    /***
     * 恢复开启摄像头
     */
    public void onVisibile()
    {
            codeView.resume();
            Log.d(TAG,"===>Resume scan");
    }

    @Override
    public void onResume() {
        if(getUserVisibleHint())
            codeView.resume();
        super.onResume();
    }

    /***
     * 暂停摄像头
     */
    public  void onInVisibile()
    {
            codeView.pause();
            Log.d(TAG,"===>pause scan");
    }


    @Override
    public void onPause() {
        super.onPause();
        codeView.pause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        capManager.onDestroy();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"==>onDestroyView");
    }

    DecodeResultListener mListener;
    public void setDecodeResultListener(DecodeResultListener listener)
    {
        this.mListener = listener;
    }

    public interface DecodeResultListener
    {
        void successful(BarcodeResult result);
        void error();
    }


}
