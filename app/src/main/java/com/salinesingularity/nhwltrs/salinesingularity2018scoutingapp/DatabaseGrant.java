package com.salinesingularity.nhwltrs.salinesingularity2018scoutingapp;

/**
 * Created by frc5066 on 1/30/2018.
 */

import android.content.Context;

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

    public static void makeTeam(int teamNumber, String teamName){
        try {
            tempTeamData.put(new JSONObject("{"+
                    "\"team\":" + teamNumber + "," +
                    "\"name\":\"" + teamName + "\"" +
            "}"));
            send();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static int getTeamNumber(int index){
        try {
            return teamData.getJSONObject(index).getInt("team");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getTeamName(int index) {
        try {
            return teamData.getJSONObject(index).getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public static int getTeamDatabaseLength(){
        return teamData.length();
    }

    public static int getLocalTeamNumber(int index) {
        try {
            return tempTeamData.getJSONObject(index).getInt("team");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getLocalTeamName(int index) {
        try {
            return tempTeamData.getJSONObject(index).getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Error";
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

    public static void createRobotMatch(int teamNumber, String match, boolean onBlue){
        try {
            tempRobotMatchData = new JSONObject("{" +
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Meta data goes here
    }

    public static void setStartingPos(int pos){ // 0-2 close-far
        try {
            tempRobotMatchData.put("startingPos",pos);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setAutonSkill(int skill){ // 0 No auton, 1 Passes Base line, (Wrong side: -)2 Switch, (Wrong side: -)3 Scale, 5 Robot eats power cube
        try {
            tempRobotMatchData.put("autonSkill",skill);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setClimbSkill(int skill){ // 0 Not in contact with BASE, 1 In contact with BASE, 2 Completed CLIMB
        try {
            tempRobotMatchData.put("climbSkill",skill);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void addScale(String type, int ms){
        try {
            tempRobotMatchData.getJSONArray("scale").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static int readScale(){
        try {
            return tempRobotMatchData.getJSONArray("scale").length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int readSwitchFriendly(){
        try {
            return tempRobotMatchData.getJSONArray("switchFriendly").length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int readSwitchEnemy(){
        try {
            return tempRobotMatchData.getJSONArray("switchEnemy").length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void addSwitchFriendly(String type, int ms){
        try {
            tempRobotMatchData.getJSONArray("switchFriendly").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void addSwitchEnemy(String type, int ms){
        try {
            tempRobotMatchData.getJSONArray("switchEnemy").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void addVault(String type, int ms){
        try {
            tempRobotMatchData.getJSONArray("vault").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void addPortal(String type, int ms){
        try {
            tempRobotMatchData.getJSONArray("portal").put(new JSONObject("{\"type\":\""+ type +"\",\"time\":"+ms+"}"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void removeScale() {
        try {
            tempRobotMatchData.getJSONArray("scale").remove(tempRobotMatchData.getJSONArray("scale").length()-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void removeSwitchFriendly() {
        try {
            tempRobotMatchData.getJSONArray("switchFriendly").remove(tempRobotMatchData.getJSONArray("switchFriendly").length()-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void removeSwitchEnemy() {
        try {
            tempRobotMatchData.getJSONArray("switchEnemy").remove(tempRobotMatchData.getJSONArray("switchEnemy").length()-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void removeVault() {
        try {
            tempRobotMatchData.getJSONArray("vault").remove(tempRobotMatchData.getJSONArray("vault").length()-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void removePortal() {
        try {
            tempRobotMatchData.getJSONArray("portal").remove(tempRobotMatchData.getJSONArray("portal").length()-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void finishMatch(){
        if(tempRobotMatchData==null){
            return;
        }
        robotMatchData.put(tempRobotMatchData);
        tempRobotMatchData=null;
        send();
    }
}
