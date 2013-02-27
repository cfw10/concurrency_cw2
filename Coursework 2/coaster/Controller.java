

/**
 * Complete the implementation of this class in line with the FSP model
 */

import display.*;

public class Controller {

	public static int Max = 9;
	protected NumberCanvas passengers;

	int numPass = 0; 
	private final Object platLock = new Object();
	private final Object carLock = new Object();

	boolean leave = false;

	public Controller(NumberCanvas nc) {
		passengers = nc;
	}

//new passenger arrives on the platform
	public void newPassenger() throws InterruptedException {
		// complete implementation
		// use "passengers.setValue(integer value)" to update diplay
		//while (numPass == Max){/*wait for the number of passengers to be lass than the maximum the platform can hold*/}
		while (numPass >= Max){
			synchronized (platLock){
				platLock.wait();
			}
		}
		passengers.setValue(++numPass);	
		synchronized (carLock){
			carLock.notify();
		}
	}

	public int getPassengers(int mcar) throws InterruptedException {
		int passInCar = 0;
		while (((numPass < mcar) && !(leave)) || (numPass == 0)){
			synchronized (carLock){
				carLock.wait();
			}
		}
		if ((numPass < mcar) && (leave)){
			passInCar = numPass;
			numPass = 0;
		}
		else{
			passInCar = mcar;
			numPass -= mcar;
		}
		leave = false;
		passengers.setValue(numPass);				
		synchronized (platLock){
			platLock.notify();
		}
		return passInCar; // dummy value to allow compilation
	}

	public synchronized void goNow() {
		synchronized (carLock){
			if (numPass > 0)
				leave = true;
			carLock.notify();
		}
	}

}
