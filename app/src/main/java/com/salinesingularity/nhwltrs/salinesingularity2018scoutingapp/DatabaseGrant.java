package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

/**
 * Created by frc5066 on 1/30/2018.
 */

import org.json.*;

public class DatabaseGrant {

    public static BluetoothGrant bluetooth;
    public static boolean inSession = false;
    private static JSONObject data;
    private static String Switch = "";

    public static void setup(BluetoothGrant bluetooth){
        DatabaseGrant.bluetooth=bluetooth;
    }

    public static void send(){

        String data="";
        bluetooth.send(","+data);
        inSession=false;
    }

    public static boolean createRobotMatch(int teamNumber, String match, boolean onBlue){
        if(inSession){
             return false;
        }
        try {
            data = new JSONObject("{" +
                    "\"type\":\"robotMatch\"," +
                    "\"team\":" + teamNumber + "," +
                    "\"matchID\":\"" + match + "\"," +
                    "\"onBlue\":"+onBlue+"," +
                    "\"scale\":[]," +
                    "\"switchFriendly\":[]," +
                    "\"switchEnemy\":[]," +
                    "\"vault\":[]," +
                    "\"portal\":[]," +
                    "\"startingPos\":-1," + //-1 error, 0-2 close-far
                    "\"autonSkill\":-1," + //-1 error, 0 No auton, 1 Runs, 2 Passes Base line, (Wrong side: -)3 Switch, (Wrong side: -)4 Scale, 5 Robot eats power cube
                    "\"climbSkill\":-1," + //-1 error, 0 Not in contact with BASE, 1 In contact with BASE, 2 Completed CLIMB
                    "}");
        }catch (JSONException e){
            return false;
        }
        // Meta data goes here

        return inSession=true;
    }

    public static void setStartingPos(int pos) throws JSONException { // 0-2 close-far
        data.put("startingPos",pos);
    }

    public static void setAutonSkill(int skill) throws JSONException { // 0 No auton, 1 Runs, 2 Passes Base line, (Wrong side: -)3 Switch, (Wrong side: -)4 Scale, 5 Robot eats power cube
        data.put("autonSkill",skill);
    }

    public static void setClimbSkill(int skill) throws JSONException { // 0 Not in contact with BASE, 1 In contact with BASE, 2 Completed CLIMB
        data.put("climbSkill",skill);
    }

    public static void addScale(String type, int ms) throws JSONException {
        data.getJSONArray("scale").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addSwitchFriendly(String type, int ms) throws JSONException {
        data.getJSONArray("switchFriendly").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addSwitchEnemy(String type, int ms) throws JSONException {
        data.getJSONArray("switchEnemy").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addVault(String type, int ms) throws JSONException {
        data.getJSONArray("vault").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addPortal(String type, int ms) throws JSONException {
        data.getJSONArray("portal").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void removeScale() throws JSONException {
        data.getJSONArray("scale").remove(data.getJSONArray("scale").length()-1);
    }

    public static void removeSwitchFriendly() throws JSONException {
        data.getJSONArray("switchFriendly").remove(data.getJSONArray("switchFriendly").length()-1);
    }

    public static void removeSwitchEnemy() throws JSONException {
        data.getJSONArray("switchEnemy").remove(data.getJSONArray("switchEnemy").length()-1);
    }

    public static void removeVault() throws JSONException {
        data.getJSONArray("vault").remove(data.getJSONArray("vault").length()-1);
    }

    public static void removePortal() throws JSONException {
        data.getJSONArray("portal").remove(data.getJSONArray("portal").length()-1);
    }


}
