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
                    "\"autonSkill\":-1," + //-1 error, 0 No auton, 1 Runs, 2 Passes Base line, 3 Switch, 4 Scale, 5 Robot eats power cube
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

    public static void setAutonSkill(int skill) throws JSONException { // 0 No auton, 1 Runs, 2 Passes Base line, 3 Switch, 4 Scale, 5 Robot eats power cube
        data.put("autonSkill",skill);
    }

    public static void setClimbSkill(int skill) throws JSONException { // 0 Not in contact with BASE, 1 In contact with BASE, 2 Completed CLIMB
        data.put("climbSkill",skill);
    }

    public static void addScale(String type, int ms) throws JSONException {
        data.getJSONArray("scale").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }
}
