package xyz.liangwh.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeClientHandler implements Runnable{

    private Socket client ;

    public TimeClientHandler(Socket client){
        this.client = client;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(),true);
            String body = null;
            String currentTime = null;
            while (true){
                body = in.readLine();
                if(body == null){
                    break;
                }
                body = body.trim();
                System.out.println("timeServer recevie client body:"+body);
                if("QUERY_TIME".equals(body)){
                    currentTime = new Date().toString();
                }else if("Q".equals(body)){
                    break;
                }else{
                    currentTime = "BAD_COMMEND";
                }
                System.out.println(currentTime);
                out.println(currentTime);
                //此处如果用write，需要手动flush
                //out.flush();
            }


        }catch (Exception e){
                e.printStackTrace();
        }finally {

            try {
                in.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                out.close();
            }catch (Exception oute){
                oute.printStackTrace();
            }
            try {
                client.close();
            }catch (Exception cliente){
                cliente.printStackTrace();
            }
        }

    }
}
