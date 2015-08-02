package it.jaschke.alexandria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * An activity featuring a simple barcode scanner
 *
 * @see <a href="https://github.com/dm77/barcodescanner/blob/master/zbar/sample/src/main/java/me/dm7/barcodescanner/zbar/sample/SimpleScannerActivity.java"></a>
 */
public class SimpleScannerActivity extends ActionBarActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);
        ArrayList<BarcodeFormat> formats = new ArrayList<>();
        formats.add(BarcodeFormat.EAN13);
        mScannerView.setFormats(formats);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(AddBook.EAN_CODE, rawResult.getContents());
        setResult(AddBook.SCAN_CODE, resultIntent);
        finish();
    }
}