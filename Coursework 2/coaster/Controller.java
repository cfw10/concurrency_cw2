

/**
 * Complete the implementation of this class in line with the FSP model
 */

import display.*;

public class Controller {

	public static int Max = 9;
	protected NumberCanvas passengers;

	int numPass = 0; 
	private final Object passLock = new Object();
	private final Object carpassLock = new Object();
	// declarations required


	public Controller(NumberCanvas nc) {
		passengers = nc;
	}

//new passenger arrives on the platform
	public void newPassenger() throws InterruptedException {
		// complete implementation
		// use "passengers.setValue(integer value)" to update diplay
		//while (numPass == Max){/*wait for the number of passengers to be lass than the maximum the platform can hold*/}
		passengers.setValue(++numPass);
		synchronized (carpassLock){
			carpassLock.notify();
		}
	}

	public int getPassengers(int mcar) throws InterruptedException {
		// complete implementation for part I
		// update for part II
		// use "passengers.setValue(integer value)" to update diplay
		while (numPass < mcar){
			synchronized (carpassLock){
				carpassLock.wait();
			}
		}
		passengers.setValue((numPass -= mcar));
		return mcar; // dummy value to allow compilation
	}

	public synchronized void goNow() {
		// complete implementation for part II
	}

}
