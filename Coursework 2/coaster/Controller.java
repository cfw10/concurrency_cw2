

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
	// declarations required


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
		// complete implementation for part I
		// update for part II
		// use "passengers.setValue(integer value)" to update diplay
		while (numPass < mcar){
			synchronized (carLock){
				carLock.wait();
			}
		}
		passengers.setValue((numPass -= mcar));
		synchronized (platLock){
			platLock.notify();
		}
		return mcar; // dummy value to allow compilation
	}

	public synchronized void goNow() {
		// complete implementation for part II
	}

}
