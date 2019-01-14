package demo.bw.com.week3.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static String get(String urlstr)throws Exception{
        URL url=new URL(urlstr);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        //请求方式
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        InputStream stream=connection.getInputStream();
        String  inputstr= getInputStr(stream);
        return inputstr;
    }
    private static String getInputStr(InputStream stream) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(stream));
        StringBuffer sb=new StringBuffer();
        String str=null;
        while ((str=br.readLine())!=null){
            sb.append(str);
        }
        return sb.toString();
    }

    public static String post(String urlstr,String mobile,String pass)throws Exception{

        String inputStr="";
        URL url=new URL(urlstr);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        //请求方式
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000);
        OutputStream outputStream=connection.getOutputStream();
        String params = "mobile=" + mobile + "&password=" + pass;
        outputStream.write(params.getBytes());
        if (connection.getResponseCode()==200){
            InputStream stream=connection.getInputStream();
            inputStr=getInputStr(stream);
        }
        return inputStr;
    }

}
