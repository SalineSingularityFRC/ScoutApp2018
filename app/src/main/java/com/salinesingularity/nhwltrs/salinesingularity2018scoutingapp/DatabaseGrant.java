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
    private static JSONObject tempRobotMatchData;
    public static JSONArray robotMatchData = new JSONArray();
    private static JSONArray tempTeamData = new JSONArray();
    private static JSONArray teamData = new JSONArray();

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
        tempTeamData.put(new JSONObject("{"+
                "\"type\":\"team\"," +
                "\"team\":" + teamNumber + "," +
                "\"name\":\"" + teamName + "\"" +
        "}"));
        send();
    }

    public static int getTeamNumber(int index) throws JSONException {
        return teamData.getJSONObject(index).getInt("team");
    }

    public static String getTeamName(int index) throws JSONException {
        return teamData.getJSONObject(index).getString("name");
    }

    public static int getTeamDatabaseLength(){
        return teamData.length();
    }

    public static int getLocalTeamNumber(int index) throws JSONException {
        return tempTeamData.getJSONObject(index).getInt("team");
    }

    public static String getLocalTeamName(int index) throws JSONException {
        return tempTeamData.getJSONObject(index).getString("name");
    }

    public static int getLocalTeamDatabaseLength(){
        return tempTeamData.length();
    }

    public static void dataSent(String data){
        try {
            teamData=new JSONArray(data);
            FileOutputStream fos = bluetooth.activity.openFileOutput("teamData", bluetooth.activity.MODE_PRIVATE);
            fos.write(teamData.toString().getBytes());
            fos.close();
        }catch (JSONException | IOException e){
            e.printStackTrace();
            return;
        }
        robotMatchData= new JSONArray();
        tempTeamData= new JSONArray();

    }

    private static void send(){
        bluetooth.send("{\"matchData\":"+ robotMatchData.toString()+",\"teamData\":"+tempTeamData.toString()+"}");
    }

    public static void createRobotMatch(int teamNumber, String match, boolean onBlue) throws JSONException{
        tempRobotMatchData = new JSONObject("{" +
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
    }

    public static void setStartingPos(int pos) throws JSONException { // 0-2 close-far
        tempRobotMatchData.put("startingPos",pos);
    }

    public static void setAutonSkill(int skill) throws JSONException { // 0 No auton, 1 Passes Base line, (Wrong side: -)2 Switch, (Wrong side: -)3 Scale, 5 Robot eats power cube
        tempRobotMatchData.put("autonSkill",skill);
    }

    public static void setClimbSkill(int skill) throws JSONException { // 0 Not in contact with BASE, 1 In contact with BASE, 2 Completed CLIMB
        tempRobotMatchData.put("climbSkill",skill);
    }

    public static void addScale(String type, int ms) throws JSONException {
        tempRobotMatchData.getJSONArray("scale").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addSwitchFriendly(String type, int ms) throws JSONException {
        tempRobotMatchData.getJSONArray("switchFriendly").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addSwitchEnemy(String type, int ms) throws JSONException {
        tempRobotMatchData.getJSONArray("switchEnemy").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addVault(String type, int ms) throws JSONException {
        tempRobotMatchData.getJSONArray("vault").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void addPortal(String type, int ms) throws JSONException {
        tempRobotMatchData.getJSONArray("portal").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
    }

    public static void removeScale() throws JSONException {
        tempRobotMatchData.getJSONArray("scale").remove(tempRobotMatchData.getJSONArray("scale").length()-1);
    }

    public static void removeSwitchFriendly() throws JSONException {
        tempRobotMatchData.getJSONArray("switchFriendly").remove(tempRobotMatchData.getJSONArray("switchFriendly").length()-1);
    }

    public static void removeSwitchEnemy() throws JSONException {
        tempRobotMatchData.getJSONArray("switchEnemy").remove(tempRobotMatchData.getJSONArray("switchEnemy").length()-1);
    }

    public static void removeVault() throws JSONException {
        tempRobotMatchData.getJSONArray("vault").remove(tempRobotMatchData.getJSONArray("vault").length()-1);
    }

    public static void removePortal() throws JSONException {
        tempRobotMatchData.getJSONArray("portal").remove(tempRobotMatchData.getJSONArray("portal").length()-1);
    }

    public static void finishMatch(){
        if(tempRobotMatchData==null){
            return;
        }
        robotMatchData.put(tempRobotMatchData);
        tempRobotMatchData=null;
    }
}
