

/**
 * Complete the implementation of this class in line with the FSP model
 */

public class PlatformAccess {

	/* declarations required */
	boolean carHere = false;
	private final Object platLock = new Object();

	public void arrive() throws InterruptedException {
		while (carHere){
			synchronized (platLock){
				platLock.wait();
			}
		}
		carHere = true;
	}

	public synchronized void depart() {
		synchronized (platLock){
			platLock.notify();
		}
		carHere = false;
	}

}
