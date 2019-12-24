package com.example.nordicpoc;


import android.bluetooth.BluetoothDevice;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import no.nordicsemi.android.dfu.DfuProgressListener;
import no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import no.nordicsemi.android.dfu.DfuServiceController;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.dfu.DfuServiceListenerHelper;


public class MainActivity extends AppCompatActivity {

    private DfuServiceController dfuServiceController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DfuServiceListenerHelper.registerProgressListener(this, mDfuProgressListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DfuServiceListenerHelper.unregisterProgressListener(this, mDfuProgressListener);

    }

    private void upload(BluetoothDevice device, String path) {
        DfuServiceInitiator dfuServiceInitiator = initialDfuServiceInitiator(device);
        dfuServiceInitiator.setZip(path);
        startController(dfuServiceInitiator);
    }

    private void upload(BluetoothDevice device, Uri fileStreamUri, String filePath) {
        DfuServiceInitiator dfuServiceInitiator = initialDfuServiceInitiator(device);
        dfuServiceInitiator.setZip(fileStreamUri, filePath);
        startController(dfuServiceInitiator);
    }

    private void startController(DfuServiceInitiator dfuServiceInitiator) {
        if (dfuServiceController != null) {
            this.dfuServiceController = dfuServiceInitiator.start(this, DfuService.class);
        }
    }

    private void stopController() {
        if (dfuServiceController != null) {
            dfuServiceController.abort();
        }
    }

    private void cancel() {
        //ProgressBar.STOP
    }

    private DfuServiceInitiator initialDfuServiceInitiator(BluetoothDevice device) {
        //ProgressBar.START

        DfuServiceInitiator dfuServiceInitiator = new DfuServiceInitiator(device.getAddress());
        dfuServiceInitiator.setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(false);
        return dfuServiceInitiator;
    }

    private final DfuProgressListener mDfuProgressListener = new DfuProgressListenerAdapter() {
        @Override
        public void onDeviceConnecting(final String deviceAddress) {
            super.onDeviceConnecting(deviceAddress);
            //ProgressBar.STOP
        }

        @Override
        public void onDfuProcessStarting(final String deviceAddress) {
            super.onDfuProcessStarting(deviceAddress);
            //ProgressBar.STOP

        }

        @Override
        public void onDeviceConnected(@NonNull String deviceAddress) {
            super.onDeviceConnected(deviceAddress);
            //ProgressBar.STOP

        }

        @Override
        public void onDfuProcessStarted(@NonNull String deviceAddress) {
            super.onDfuProcessStarted(deviceAddress);
            //ProgressBar.STOP

        }

        @Override
        public void onEnablingDfuMode(@NonNull String deviceAddress) {
            super.onEnablingDfuMode(deviceAddress);
            //ProgressBar.STOP

        }

        @Override
        public void onProgressChanged(@NonNull String deviceAddress, int percent, float speed, float avgSpeed, int currentPart, int partsTotal) {
            super.onProgressChanged(deviceAddress, percent, speed, avgSpeed, currentPart, partsTotal);

            //ProgressBar.UPDATE

        }

        @Override
        public void onFirmwareValidating(@NonNull String deviceAddress) {
            super.onFirmwareValidating(deviceAddress);
            //ProgressBar.STOP

        }

        @Override
        public void onDeviceDisconnecting(String deviceAddress) {
            super.onDeviceDisconnecting(deviceAddress);
            //ProgressBar.STOP

        }

        @Override
        public void onDeviceDisconnected(@NonNull String deviceAddress) {
            super.onDeviceDisconnected(deviceAddress);
            //ProgressBar.STOP

        }

        @Override
        public void onDfuCompleted(@NonNull String deviceAddress) {
            super.onDfuCompleted(deviceAddress);
            //ProgressBar.STOP

        }

        @Override
        public void onDfuAborted(@NonNull String deviceAddress) {
            super.onDfuAborted(deviceAddress);
            //ProgressBar.STOP

        }

        @Override
        public void onError(@NonNull String deviceAddress, int error, int errorType, String message) {
            super.onError(deviceAddress, error, errorType, message);
            //ProgressBar.STOP
        }
    };


}
