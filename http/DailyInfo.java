package http;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import core.Avatar_Name;
import core.TimeCounter;

public class DailyInfo {

    public static String[] getDaily(String args) throws JSONException {
        String strResin = null;
        String strtask = null;
        String recTime = null;
        String qianTime = null;
        String needTime = null;
        String strbaoqian = null;
        String qianrecTime = null;
        String qianneedTime = null;
        JSONObject jsonObj = new JSONObject(args);
        int retcode = (int) jsonObj.get("retcode");
        if(retcode!=0){
            strResin = "读取失败";
            strtask = "读取失败";
            recTime = "读取失败";
            needTime = "读取失败";
            strbaoqian = "读取失败";
            qianrecTime = "读取失败";
            qianneedTime = "读取失败";
        }else{
            //获取当前树脂数
            String str1 = (String) jsonObj.getJSONObject("data").getString("current_resin");
            String str2 = (String) jsonObj.getJSONObject("data").getString("max_resin");
            strResin = "当前树脂数为：" + str1 + "/" + str2;
            String str3 = (String) jsonObj.getJSONObject("data").getString("current_home_coin");
            String str4 = (String) jsonObj.getJSONObject("data").getString("max_home_coin");
            strbaoqian = "当前洞天宝钱数为：" + str3 + "/" + str4;
            int rt = (int) jsonObj.getJSONObject("data").getInt("resin_recovery_time");
            recTime = "树脂将于" + TimeCounter.AimTime(rt) + "完全恢复";
            needTime = "树脂恢复还需" + TimeCounter.NeededTime(rt);
            int qiant = (int) jsonObj.getJSONObject("data").getInt("home_coin_recovery_time");
            qianrecTime = "宝钱将于" + TimeCounter.AimTime(qiant) + "完全恢复";
            qianneedTime = "宝钱恢复还需" + TimeCounter.NeededTime(qiant);
            boolean task = jsonObj.getJSONObject("data").getBoolean("is_extra_task_reward_received");
            if(task){
                strtask = "今日每日任务已提交~";
            }else{
                strtask = "今日每日任务未提交！！！！";
            }

        }
        String[]  a = {strResin, recTime, needTime, strtask, strbaoqian, qianrecTime, qianneedTime};
        return a;
    }

    public static int resinRecoveryTime(String args) throws JSONException{
        JSONObject jsonObj = new JSONObject(args);
        int retcode = (int) jsonObj.get("retcode");
        int recoveryTime = 0;
        if(retcode==0){
            recoveryTime = (int) jsonObj.getJSONObject("data").getInt("resin_recovery_time");
        }
        return recoveryTime;
    }

    public static int qianRecoveryTime(String args) throws JSONException{
        JSONObject jsonObj = new JSONObject(args);
        int retcode = (int) jsonObj.get("retcode");
        int recoveryTime = 0;
        if(retcode==0){
            recoveryTime = (int) jsonObj.getJSONObject("data").getInt("home_coin_recovery_time");
        }
        return recoveryTime;
    }

    public static boolean rewardReceived(String args) throws JSONException{
        JSONObject jsonObj = new JSONObject(args);
        int retcode = (int) jsonObj.get("retcode");
        boolean taskReceived = false;
        if(retcode==0){
            taskReceived = (boolean)jsonObj.getJSONObject("data").getBoolean("is_extra_task_reward_received");
        }
        return taskReceived;
    }

    public static Map<String, String> avatarname = new HashMap<>();

    public static String[] getExp(String args) throws JSONException {
        String strExp = "";
        String strExpUnit1 = "";
        String strExpUnit2 = "";
        String strExpUnit3 = "";
        String strExpUnit4 = "";
        String strExpUnit5 = "";
        String strExpUnit6 = "";
        String strExpUnit7 = "";
        String strExpUnit8 = "";
        String strExpUnit9 = "";
        String strExpUnit10 = "";
        String Role1 = "空白";
        String Role2 = "空白";
        String Role3 = "空白";
        String Role4 = "空白";
        String Role5 = "空白";
        String imageUrl1;
        String imageUrl2;
        String imageUrl3;
        String imageUrl4;
        String imageUrl5;
        String reurl;
        String strEx1 = "";
        String strEx2 = "";
        String strEx3 = "";
        String strEx4 = "";
        String strEx5 = "";
        avatarname = Avatar_Name.GetAvatarName();
        reurl = "https://upload-bbs.mihoyo.com/game_record/genshin/character_side_icon/UI_AvatarIcon_Side_";
        JSONObject jsonObj = new JSONObject(args);
        int retcode = (int) jsonObj.get("retcode");
        if(retcode!=0){
            //strResin = "读取失败";
        }else{
            String str1 = (String) jsonObj.getJSONObject("data").getString("current_expedition_num");
            String str2 = (String) jsonObj.getJSONObject("data").getString("max_expedition_num");
            strExp = "派遣探索：(" + str1 + "/" + str2 + ")";
            int num = Integer.parseInt(str1);
            if(num>0){
                strEx1 = (String) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(0).getString("status");
                imageUrl1 = (String) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(0).getString("avatar_side_icon");
                Role1 = imageUrl1.replace(reurl, "");
                Role1 = Role1.replace(".png", "");
                Role1 = avatarname.get(Role1);
                if(strEx1.equals("Ongoing")){
                    int time1 = (int) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(0).getInt("remained_time");

                    //strExpUnit1 = Role1 + "探索将于" + TimeCounter.AimTime(time1) + "完成";
                    strExpUnit1 = Role1 + "将于" + TimeCounter.AimTime(time1) + "完成";
                    strExpUnit2 = Role1 +"还需" + TimeCounter.NeededTime(time1);
                    //strExpUnit2 = Role1 +"还需" + TimeCounter.NeededTime(time1) + "探索完成";
                }else{
                    strExpUnit1 = Role1 +"探索完成~";
                }
            }
            else{
                strExpUnit1 = "探索位1未在探索";
            }
            if(num>1){
                strEx2 = (String) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(1).getString("status");
                imageUrl2 = (String) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(1).getString("avatar_side_icon");
                Role2 = imageUrl2.replace(reurl, "");
                Role2 = Role2.replace(".png", "");
                Role2 = avatarname.get(Role2);
                if(strEx2.equals("Ongoing")){
                    int time2 = (int) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(1).getInt("remained_time");

                    strExpUnit3 = Role2 + "将于" + TimeCounter.AimTime(time2) + "完成";
                    strExpUnit4 = Role2 + "还需" + TimeCounter.NeededTime(time2);
                }else{
                    strExpUnit3 = Role2 +"探索完成~";
                }
            }
            else{
                strExpUnit3 = "探索位2未在探索";
            }
            if(num>2){
                strEx3 = (String) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(2).getString("status");
                imageUrl3 = (String) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(2).getString("avatar_side_icon");
                Role3 = imageUrl3.replace(reurl, "");
                Role3 = Role3.replace(".png", "");
                Role3 = avatarname.get(Role3);
                if(strEx3.equals("Ongoing")){
                    int time3 = (int) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(2).getInt("remained_time");

                    strExpUnit5 = Role3 + "将于" + TimeCounter.AimTime(time3) + "完成";
                    strExpUnit6 = Role3 + "还需" + TimeCounter.NeededTime(time3);
                }else{
                    strExpUnit5 = Role3 +"探索完成~";
                }
            }
            else{
                strExpUnit5 = "探索位3未在探索";
            }
            if(num>3){
                strEx4 = (String) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(3).getString("status");
                imageUrl4 = (String) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(3).getString("avatar_side_icon");
                Role4 = imageUrl4.replace(reurl, "");
                Role4 = Role4.replace(".png", "");
                Role4 = avatarname.get(Role4);
                if(strEx4.equals("Ongoing")){
                    int time4 = (int) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(3).getInt("remained_time");

                    strExpUnit7 = Role4 + "将于" + TimeCounter.AimTime(time4) + "完成";
                    strExpUnit8 = Role4 + "还需" + TimeCounter.NeededTime(time4);
                }else{
                    strExpUnit7 = Role4 +"探索完成~";
                }
            }
            else{
                strExpUnit7 = "探索位4未在探索";
            }
            if(num>4){
                strEx5 = (String) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(4).getString("status");
                imageUrl5 = (String) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(4).getString("avatar_side_icon");
                Role5 = imageUrl5.replace(reurl, "");
                Role5 = Role5.replace(".png", "");
                Role5 = avatarname.get(Role5);
                if(strEx5.equals("Ongoing")){
                    int time5 = (int) jsonObj.getJSONObject("data").getJSONArray("expeditions").getJSONObject(4).getInt("remained_time");

                    strExpUnit9 = Role5 + "将于" + TimeCounter.AimTime(time5) + "完成";
                    strExpUnit10 = Role5 + "还需" + TimeCounter.NeededTime(time5);
                }else{
                    strExpUnit9 = Role5 +"探索完成~";
                }
            }
            else{
                strExpUnit9 = "探索位5未在探索";
            }
        }
        String[]  a = { strExp,
                strExpUnit1, strExpUnit2,
                strExpUnit3, strExpUnit4,
                strExpUnit5, strExpUnit6,
                strExpUnit7, strExpUnit8,
                strExpUnit9, strExpUnit10,
                Role1, Role2,
                Role3, Role4, Role5,
                strEx1,strEx2,
                strEx3,strEx4,strEx5};
        return a;
    }
}
