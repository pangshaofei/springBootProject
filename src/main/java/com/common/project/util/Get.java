package com.common.project.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Get {
    public static HttpURLConnection getConnection(String url){
        HttpURLConnection connection = null;
        try {
            // 打开和URL之间的连接
            URL postUrl = new URL(url);
            connection = (HttpURLConnection) postUrl.openConnection();
            // 设置通用的请求属性
            connection.setDoOutput(false);//posp请求改为TRUE
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charset", "utf-8");
            connection.setRequestProperty("Accept-Charset", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public String getHttpRespone(String url) throws IOException {

        String line = "";

        String httpResults = "";

        try {
            HttpURLConnection urlcon = this.getConnection(url);
            urlcon.setReadTimeout(5000);
            urlcon.setConnectTimeout(5000);
            urlcon.connect();
            BufferedReader reader=new BufferedReader(new InputStreamReader(urlcon.getInputStream(),"utf-8"));
            //line=bf.readLine();
            while ((line = reader.readLine()) != null) {
                httpResults = httpResults + line.toString();
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return httpResults;

    }
}
