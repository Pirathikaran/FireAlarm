/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.server;

/**
 *
 * @author User
 */


import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import login.Implement.LoginImplement;
import login.Interface.LoginInterface;
import main.PackSensor;
import main.PackSensorI;


public class LoginServer {
    
    public static void main(String[] args)throws Exception {
        
        try{
                    Registry reg=LocateRegistry.createRegistry(1050);
                    LoginInterface obj=new LoginImplement();
                    PackSensorI obj2=new PackSensor();
                    reg.rebind("login", obj);
                    reg.rebind("sensor", obj2);
                    System.out.println("Server is ready");
                    

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
