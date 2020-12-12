package com.example.weather;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MailWeather {
    HttpsURLConnection urlConnection;
    Context _context;
    URL url;

    public MailWeather()
    {


    }
String result="";
    public String getWeather(String site)
    {
        String weather="none";
        int index =result.indexOf("weather__temp");
        if (index>0)
        {
            weather=  result.substring( index+15, index+24);
            index =weather.indexOf("<");
            weather=  weather.substring(0,index);
        }
        return weather;
    }
 public String getSrc (String site)
 {
     String src="none";

     int index =result.indexOf("weather__icon");
     if (index>0)
     {
         src=  result.substring( index+13);
         index= src.indexOf("url");
         src=  src.substring(index+5,src.indexOf(")")-1);
     }
     return "https:"+src;
 }
    public void getSiteThread (String city)
    {
        try {
            String site ="https://yandex.ru/";

            url = new URL(site);
            urlConnection =(HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            Scanner scanner = new Scanner(stream).useDelimiter("\\A");
            while (scanner.hasNext())
            {
                result+=scanner.next();
            }



        } catch (Exception e) {
            result= e.getMessage();
       }
    }

    public String getSite(String city) {
      Thread thread=   new Thread(new Runnable() {
             @Override
             public void run() {
                 getSiteThread("city");
             }
         });

      thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return   result;
    }
}
