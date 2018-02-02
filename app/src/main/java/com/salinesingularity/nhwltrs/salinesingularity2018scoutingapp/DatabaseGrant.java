package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

/**
 * Created by frc5066 on 1/30/2018.
 */

import android.content.Context;
import android.util.Log;

import org.json.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DatabaseGrant {

    public static BluetoothGrant bluetooth;
    public static boolean inSession = false;
    private static JSONObject robotMatchData;
    private static JSONObject tempTeamData;
    private static JSONArray teamData;

    public static void setup(BluetoothGrant bluetooth){
        DatabaseGrant.bluetooth=bluetooth;
        try {
            FileInputStream fis = bluetooth.activity.openFileInput("teamData");
            teamData=new JSONArray(fis.read());
            fis.close();
        } catch (FileNotFoundException e) {
            try {
                FileOutputStream fos = bluetooth.activity.openFileOutput("teamData", Context.MODE_PRIVATE);
                fos.write("".getBytes());
                fos.close();
                FileInputStream fis = bluetooth.activity.openFileInput("teamData");
                teamData=new JSONArray(fis.read());
                fis.close();
            } catch (IOException | JSONException e1) {
                e1.printStackTrace();
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeTeam(int teamNumber, String teamName) throws JSONException{
        tempTeamData = new JSONObject("{"+
                "\"type\":\"team\"," +
                "\"team\":" + teamNumber + "," +
                "\"name\":\"" + teamName + "\"" +
        "}");
    }

    public static int getTeamNumber(int index) throws JSONException {
        return teamData.getJSONObject(index).getInt("team");
    }

    public static int getTeamName(int index) throws JSONException {
        return teamData.getJSONObject(index).getInt("name");
    }

    public static void dataSent(){
        teamData.put(tempTeamData);
        try {
            FileOutputStream fos = bluetooth.activity.openFileOutput("teamData", bluetooth.activity.MODE_PRIVATE);
            fos.write(teamData.toString().getBytes());
            fos.close();
        }catch (FileNotFoundException e){
            Log.e("Database","RIP, File Not Found Exception: OK, so the server seems to have gotten the team data, but we can't save it, so on next startup you'll need to sync w/ the server.");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            Log.e("Database","RIP, File IO exception: OK, so the server seems to have gotten the team data, but we can't save it, so on next startup you'll need to sync w/ the server.");
            e.printStackTrace();
            return;
        }


    }

    public static void sendRobotMatch(){

        String data=robotMatchData.toString();
        bluetooth.send(","+data);
        inSession=false;
    }

    public static boolean createRobotMatch(int teamNumber, String match, boolean onBlue) throws JSONException{
        if(inSession){
             return false;
        }
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
                "\"climbSkill\":-1" + //-1 error, 0 Not in contact with BASE, 1 In contact with BASE, 2 Completed CLIMB
                "}");
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
