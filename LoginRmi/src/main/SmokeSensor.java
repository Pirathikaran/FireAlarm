/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;
import java.util.TimerTask;

/**
 *
 * @author User
 */
public class SmokeSensor extends TimerTask  {
    	
	String id;
	
	boolean fire=false;

    public int x;

	public void run() {
				
		Random r=new Random();
		
			if(x>=5) {				
				fire=true;				
				//System.out.println("Fire Sensor is above level 5");
				//System.exit(0);
                                System.out.println("Final value :" +x);
                                
                                
                                
                                
                                 
			}
			else {
				x=r.nextInt(11);		
			}			
			getNum(x);
			//System.out.println("The level is : "+x);	
	}	
	
	public void getNum(int a){
        this.x = a;
    }
	
	public int setNum() {
    	return x;
    }	
	
    
}
