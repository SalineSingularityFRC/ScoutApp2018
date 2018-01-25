package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import me.aflak.bluetooth.Bluetooth;
import me.aflak.bluetooth.CommunicationCallback;

/**
 * Created by frc5066 on 1/24/2018.
 */

public class BluetoothGrant {
    private static Bluetooth bluetooth;
    private static AppCompatActivity activity;
    public static Handler handler=new Handler();
    public static String tag="7G7 Bluetooth Test";
    public static String macAddress;
    public static String match="B8:27:EB:E8:64:53";
    //public String match1="F0:27:2D:13:41:6C";
    //public String match2="74:75:48:53:5D:FB";
    private static CommunicationCallback CCB= new CommunicationCallback() {
        @Override
        public void onConnect(BluetoothDevice device) {
            Log.i(tag,"Connected to device "+device.getName()+" at "+device.getAddress()+"!");
            bluetooth.send("\"5066\":\"Singularity\"\n");
        }

        @Override
        public void onDisconnect(BluetoothDevice device, String message) {
            Log.i(tag,"Disconnected from device "+device.getName()+" at "+device.getAddress()+"!");
            //bluetooth.connectToAddress(match);
        }

        @Override
        public void onMessage(String message) {
            Log.i("7G7 Message",message);
        }

        @Override
        public void onError(String message) {
            Log.e(tag,"Generic error: "+message);
        }

        @Override
        public void onConnectError(BluetoothDevice device, String message) {
            Log.e(tag,"Failed to connect: "+message);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i(tag,"Attempting to connect to "+match+"...");
                    bluetooth.connectToAddress(match);
                }
            },3000);
        }
    };

    public static void setup(AppCompatActivity a){
        activity=a;
        bluetooth = new Bluetooth(activity);

        bluetooth.setCommunicationCallback(CCB);

        macAddress = android.provider.Settings.Secure.getString(activity.getContentResolver(), "bluetooth_address");
        Log.i(tag,"My MAC address is: "+macAddress);


    }
}
