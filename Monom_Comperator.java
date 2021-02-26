package pack;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	/**
	 * this function help us to compere between two monoms
	 * @param arg0 represent the first monom 
	 * @param arg1 represent the second monom 
	 * @return -1  , 1 acording to how are bigger then how or 0 if they equals 
	 */
	@Override
	public int compare(Monom arg0, Monom arg1) {
		return arg1.get_power() - arg0.get_power();
	}


}
