package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

/**
 * Created by frc5066 on 1/30/2018.
 */

import org.json.*;

public class DatabaseGrant {

    public static BluetoothGrant bluetooth;
    public static boolean inSession = false;
    private static JSONObject robotMatchData;
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
            robotMatchData = new JSONObject("{" +
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
                    "\"autonSkill\":-1," + //-1 error, 0 No auton, 1 Passes Base line, (Wrong side: -)2 Switch, (Wrong side: -)3 Scale, 5 Robot eats power cube
                    "\"climbSkill\":-1," + //-1 error, 0 Not in contact with BASE, 1 In contact with BASE, 2 Completed CLIMB
                    "}");
        }catch (JSONException e){
            return false;
        }
        // Meta data goes here

        return inSession=true;
    }

    public static void setStartingPos(int pos) throws JSONException { // 0-2 close-far
        robotMatchData.put("startingPos",pos);
    }

    public static void setAutonSkill(int skill) throws JSONException { // 0 No auton, 1 Passes Base line, (Wrong side: -)2 Switch, (Wrong side: -)3 Scale, 5 Robot eats power cube
        robotMatchData.put("autonSkill",skill);
    }

    public static void setClimbSkill(int skill) throws JSONException { // 0 Not in contact with BASE, 1 In contact with BASE, 2 Completed CLIMB
        robotMatchData.put("climbSkill",skill);
    }

    public static void addScale(String type, int ms) throws JSONException {
        robotMatchData.getJSONArray("scale").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addSwitchFriendly(String type, int ms) throws JSONException {
        robotMatchData.getJSONArray("switchFriendly").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addSwitchEnemy(String type, int ms) throws JSONException {
        robotMatchData.getJSONArray("switchEnemy").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addVault(String type, int ms) throws JSONException {
        robotMatchData.getJSONArray("vault").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addPortal(String type, int ms) throws JSONException {
        robotMatchData.getJSONArray("portal").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void removeScale() throws JSONException {
        robotMatchData.getJSONArray("scale").remove(robotMatchData.getJSONArray("scale").length()-1);
    }

    public static void removeSwitchFriendly() throws JSONException {
        robotMatchData.getJSONArray("switchFriendly").remove(robotMatchData.getJSONArray("switchFriendly").length()-1);
    }

    public static void removeSwitchEnemy() throws JSONException {
        robotMatchData.getJSONArray("switchEnemy").remove(robotMatchData.getJSONArray("switchEnemy").length()-1);
    }

    public static void removeVault() throws JSONException {
        robotMatchData.getJSONArray("vault").remove(robotMatchData.getJSONArray("vault").length()-1);
    }

    public static void removePortal() throws JSONException {
        robotMatchData.getJSONArray("portal").remove(robotMatchData.getJSONArray("portal").length()-1);
    }


}
