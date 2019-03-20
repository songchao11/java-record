package com.song.record.hutool;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;

/**
 * Created by song on 2019/1/23 14:30
 */
public class HttpDemo {

    public static void main(String[] args){
//        String authorization = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1NDgyMjc0MzQsImp0aSI6ImRmZjg0Njg1MWRjYTQwMTFiNzk3YjIyYTkwYTBlZDdjIiwicGhvbmVOdW1iZXIiOiIxNTM3NTE4MzUzNSIsInRlcm1pbmFsVHlwZSI6IjQiLCJ1c2VySWQiOiIyMyIsInVzZXJUeXBlIjoiMyIsInVzZXJuYW1lIjoiMTUzNzUxODM1MzUiLCJ1dWlkIjoiNDFlYzkxMTg3M2ZmNGMyMTkyYzIxNDQwYzM2M2NhZjgifQ.iUR-QwA41Btrn24NLLlQzq8CPrPXW_td1yzdGceDdYeXiZTS2G--j9dmplHw1V9jySmqzD_lKb2LJW57v63xfw";
//        String url1 = "http://192.168.0.244:20196/platform/security/signIn";
//        String url2 = "http://192.168.0.244:20196/platform/security/currentUser";
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("username", "15375183535");
//        map.put("password", "62bd77187e8a6f972a9ac67ed734efae");
//        map.put("terminateType", 4);
//        map.put("accessKey", "string");
//        map.put("nonce", "string");
//        map.put("timestamp", 0);
//        map.put("signature", "string");
//        HashMap<String, Object> map2 = new HashMap<>();
//        map2.put("accessKey", "string");
//        map2.put("nonce", "string");
//        map2.put("timestamp", 0);
//        map2.put("signature", "string");
//        JSONObject json = JSONUtil.parseObj(map2);
//        System.out.println(json.toString());
//        String result = HttpRequest.post(url2)
//                .body(json)
//                .header("Authorization", authorization)
//                .execute()
//                .body();
//        JSONObject obj = JSONUtil.parseObj(result);
//        JSONObject obj2 = JSONUtil.parseObj(obj.get("data"));
//        System.out.println(obj2.get("username"));
//        System.out.println(result);
        HashMap<String, Object> map = new HashMap<>();
        map.put("city", "北京");
        String result = HttpUtil.get("https://www.baidu.com", map);
        System.out.println(result);
    }

}
