package main;

import java.util.Random;
import java.util.TimerTask;

public class Co2Pack extends TimerTask  {
	
	String coid;
	
	boolean fire=false;

    public int x;

	public void run() {
				
		Random r=new Random();
		
			if(x>=5) {				
				fire=true;				
				//System.out.println("Fire Sensor is above level 5");
				//System.exit(0);
                                System.out.println("Final co2 :" +x);
                                
                                
                                
                                
                                 
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