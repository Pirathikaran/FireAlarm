package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Math.floor;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;

public class PackSensor extends UnicastRemoteObject implements PackSensorI{

    public PackSensor() throws RemoteException{
        super();
    }
        @Override
	public int startSensor(String id) throws RemoteException {
            
            new Thread(() -> {
                Timer t1 = new Timer();
                SmokeSensor s1 = new SmokeSensor(); 
                Co2Pack co1=new Co2Pack();
                
                
                t1.schedule(s1, 0, 5000);
                t1.schedule(co1, 0, 5000);
                
                for(; ;) {
                    
                    System.out.println("the sensor smoke value = "+s1.setNum());
                    System.out.println("the co2 value = " +co1.setNum());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PackSensor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int a =s1.setNum();
                    int b=co1.setNum();
                    String c = "Deactivated";
                    
                    if( a>=5 || b>=5){
                        c = "Activated";
                    };
                    int ID=Integer.parseInt(id);
                    
                    try{



        String putEndpoint = "http://localhost:5001/alarm/update/"+id;
 
        CloseableHttpClient httpclient = HttpClients.createDefault();
 
        HttpPut httpPut = new HttpPut(putEndpoint);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
 
              JSONObject json = new JSONObject();
 
      json.put("smoke", a);
      json.put("co2", b);
      json.put("status", c);

 
        StringEntity stringEntity = new StringEntity(json.toString());
        httpPut.setEntity(stringEntity);
        System.out.println("Executing request " + httpPut.getRequestLine());
 
        HttpResponse response = httpclient.execute(httpPut);
 
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
 
        //Throw runtime exception if status code isn't 200 
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
 
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    
                    
                    System.out.println(a);

                }   }).start(); 
            return 0;
        }

}
