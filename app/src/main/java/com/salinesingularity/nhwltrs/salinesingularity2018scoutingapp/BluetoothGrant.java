package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Objects;

import me.aflak.bluetooth.Bluetooth;
import me.aflak.bluetooth.CommunicationCallback;

/**
 * Created by Grant D on 1/24/2018.
 */

public class BluetoothGrant {
    private Bluetooth bluetooth;
    public BeginningScreen activity;
    private boolean setup = false;
    private Handler handler = new Handler();
    private static String tag = "7G7 Bluetooth";
    private static String macAddress;
    private static String match = "B8:27:EB:E8:64:53";
    private String pendingData="";
    //public String match1="F0:27:2D:13:41:6C";
    //public String match2="74:75:48:53:5D:FB";
    private CommunicationCallback CCB= new CommunicationCallback() {
        @Override
        public void onConnect(BluetoothDevice device) {
            Log.i(tag,"Connected to device "+device.getName()+" at "+device.getAddress()+"!");
            bluetooth.send(pendingData);
        }

        @Override
        public void onDisconnect(BluetoothDevice device, String message) {
            Log.i(tag,"Disconnected from device "+device.getName()+" at "+device.getAddress()+"!");
            if(pendingData.length()!=0){
                reconnect();
            }
        }

        @Override
        public void onMessage(String message) {
            //Log.i("7G7 Message",message);
            pendingData="";
            DatabaseGrant.dataSent(message);
            Log.i(tag,"Data transfer complete!");

        }

        @Override
        public void onError(String message) {
            Log.e(tag,"Generic error: "+message);
        }

        @Override
        public void onConnectError(BluetoothDevice device, String message) {
            Log.e(tag,"Failed to connect: "+message);
            reconnect();
        }
    };

    public BluetoothGrant(BeginningScreen a){
        activity=a;
        bluetooth = new Bluetooth(activity);
        bluetooth.setCommunicationCallback(CCB);
    }

    public void setup(){
        if(!setup) {
            bluetooth.onStart();
        }
        if (!bluetooth.isEnabled())
            bluetooth.enable();
        setup=true;
        //macAddress = android.provider.Settings.Secure.getString(activity.getContentResolver(), "bluetooth_address");
        //Log.i(tag,"My MAC address is: "+macAddress);
    }

    private void reconnect(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bluetooth.connectToAddress(match);
            }
        },3000);
    }

    public void send(String data){
        if(pendingData.length()==0)
            bluetooth.connectToAddress(match);
        //pendingData+=data;
        pendingData=data;

    }
    public void end(){
        bluetooth.onStop();
    }
}