package com.xdx.partice.provider;

import com.alibaba.fastjson.JSON;
import com.xdx.partice.dto.AccesstokenDTO;
import com.xdx.partice.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xdxstart
 * @create 2021-05-07 15:32
 */
@Component
public class GithubProvider {
    public String getAccesstoken(AccesstokenDTO accesstokenDTO){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accesstokenDTO));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String[] split = string.split("&");
                String tokenstring = split[0];
                String token = tokenstring.split("=")[1];
                return token;

            } catch (Exception e) {
                e.printStackTrace();
            }
    return null;
    }
    public GithubUser getuser(String accesstoken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accesstoken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
