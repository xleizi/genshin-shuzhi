package http;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import core.TimeCounter;

public class DropInfo {
    public static String[] getUrljuese(String args, int day) throws JSONException {
        JSONObject jsonObj = new JSONObject(args);
        String[]  list = new String[23];

        int retcode = (int) jsonObj.get("retcode");
        if(retcode!=0){


        }else{
            JSONArray array = (JSONArray) jsonObj.getJSONObject("data").getJSONArray("list");
            // list = new String[array.length()];
            Log.i("数组长度",  String.valueOf(array.length()));
            //遍历这个json格式的数组
            //int a = 1;
            /**
             *
            for (int n=0;n<array.length();n++){
                JSONArray dropday = (JSONArray) array.getJSONObject(n).getJSONArray("drop_day");
                if(dropday.length()>2){
                    for (int i=0;i<dropday.length();i++){
                        int drop = (int) dropday.getInt(i);
                        if(drop == day){
                            int type = (int) array.getJSONObject(n).getInt("break_type");
                            if(type == 2){
                                //String url = (String) array.getJSONObject(n).getString("img_url");
                                //String name = (String) array.getJSONObject(n).getString("title");
                                //Log.i("url", name +  String.valueOf(n));
                                //Log.i("url", url);
                                //添加到集合里面去
                                //list[a] = url;
                                a = a + 1;
                            }
                        }
                    }
                }
            }
            list = new String[a];
            */
            int a = 0;
            for (int n=0;n<array.length();n++){
                JSONArray dropday = (JSONArray) array.getJSONObject(n).getJSONArray("drop_day");
                if(dropday.length()>2){
                    for (int i=0;i<dropday.length();i++){
                        int drop = (int) dropday.getInt(i);
                        if(drop == day){
                            int type = (int) array.getJSONObject(n).getInt("break_type");
                            if(type == 2){
                                String url = (String) array.getJSONObject(n).getString("img_url");
                                String name = (String) array.getJSONObject(n).getString("title");
                                //Log.i("url", name +  String.valueOf(n));
                                //Log.i("url", url);
                                //添加到集合里面去
                                list[a] = url;
                                a = a + 1;
                            }
                        }
                    }
                }
            }
        }
        Log.i("url0", "url0" + list[0]);
        return list;
    }

    public static String[] getUrlwuqi(String args, int day) throws JSONException {
        JSONObject jsonObj = new JSONObject(args);
        String[]  list = new String[36];

        int retcode = (int) jsonObj.get("retcode");
        if(retcode!=0){




        }else{
            //获取当前树脂数
            JSONArray array = (JSONArray) jsonObj.getJSONObject("data").getJSONArray("list");
            // list = new String[array.length()];
            Log.i("数组长度",  String.valueOf(array.length()));
            //遍历这个json格式的数组
            //int a = 1;
            /**
             *
             for (int n=0;n<array.length();n++){
             JSONArray dropday = (JSONArray) array.getJSONObject(n).getJSONArray("drop_day");
             if(dropday.length()>2){
             for (int i=0;i<dropday.length();i++){
             int drop = (int) dropday.getInt(i);
             if(drop == day){
             int type = (int) array.getJSONObject(n).getInt("break_type");
             if(type == 1){
             //String url = (String) array.getJSONObject(n).getString("img_url");
             //String name = (String) array.getJSONObject(n).getString("title");
             //Log.i("url", name +  String.valueOf(n));
             //Log.i("url", url);
             //添加到集合里面去
             //list[a] = url;
             a = a + 1;
             }
             }
             }
             }
             }
             list = new String[a];
             */
            int a = 0;
            for (int n=0;n<array.length();n++){
                JSONArray dropday = (JSONArray) array.getJSONObject(n).getJSONArray("drop_day");
                if(dropday.length()>2){
                    for (int i=0;i<dropday.length();i++){
                        int drop = (int) dropday.getInt(i);
                        if(drop == day){
                            int type = (int) array.getJSONObject(n).getInt("break_type");
                            if(type == 1){
                                String url = (String) array.getJSONObject(n).getString("img_url");
                                String name = (String) array.getJSONObject(n).getString("title");
                                //Log.i("url", name +  String.valueOf(n));
                                //Log.i("url", url);
                                //添加到集合里面去
                                list[a] = url;
                                a = a + 1;
                            }
                        }
                    }
                }
            }
        }
        Log.i("url0", "url0=" + list[0]);
        return list;
    }
}
