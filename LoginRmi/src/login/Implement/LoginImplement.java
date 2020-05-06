/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.Implement;

/**
 *
 * @author User
 */
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
public class LoginImplement extends UnicastRemoteObject implements login.Interface.LoginInterface{
    
    public LoginImplement() throws RemoteException{
        
    }

    @Override
    public boolean getLogin(String user, String pass) throws RemoteException {
        boolean found= false;
        try{


 CloseableHttpClient httpclient = HttpClients.createDefault();

      //Creating a HttpGet object
      HttpGet httpget = new HttpGet("http://localhost:5001/user/"+user);

      //Printing the method used
      System.out.println("Request Type: "+httpget.getMethod());

      //Executing the Get request
      HttpResponse httpresponse = httpclient.execute(httpget);

      Scanner sc = new Scanner(httpresponse.getEntity().getContent());

      //Printing the status line
      System.out.println(httpresponse.getStatusLine());
      while(sc.hasNext()) {
    	  String userArr = (String) sc.nextLine();
         System.out.println(userArr);
         System.out.println("User:"+userArr.contains(user));
         System.out.println("Password:"+userArr.contains(pass));
         if(userArr.contains(user) &&userArr.contains(pass)) {
        	  found=true;
         }             
            
        }
        }
      catch(Exception e){
            e.printStackTrace();
        }
        return found;
        
    }
    
    
}
