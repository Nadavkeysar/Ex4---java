package pack;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;


/**This class represents a Polynom with add, multiply
 * functionality, 
 * it also should support the following:
 * 1. Riemann's Integral: 
 *    https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values 
 *   (currently support root only f(x)=0).
 * 3. Derivative
 * @author Boaz */

/**This class represents a Polynom with add, multiply
 * functionality, 
 * it also should support the following:
 * 1. Riemann's Integral: 
 *    https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values 
 *   (currently support root only f(x)=0).
 * 3. Derivative
 * @author Boaz */


public class Polynom{

	ArrayList<Monom> list;	
	private static final Monom_Comperator copmerator = new Monom_Comperator();
	/**
	 * This constractor is defalt constractor, zero Polygon 
	 */
	public Polynom(){
		list = new ArrayList<Monom>();
	}

	/**
	 * this constractor is construct a polynom from a String input
	 * @param str represent the string input
	 */
	//	public Polynom(String str){	
	//		list = new ArrayList<Monom>();
	//		if(str.startsWith("+")) str = str.replaceFirst("\\+", "");
	//
	//		String[] arr = str.split("[+]");
	//
	//		boolean flag = false;
	//
	//		for(int i = 0; i < arr.length; i++){
	//			String[] arr2 = arr[i].split("-");
	//			for(int j = 0; j < arr2.length; j++){
	//				if(arr2[j].equals("")){//this means the first Monom is negative. so we flag it for later.
	//					flag = true;
	//				}
	//				else{
	//					if(arr.length == 1){ //if the array is sized 1 than the Monom is surely positive.
	//						list.add(new Monom(arr2[j]));
	//						break;
	//					}
	//
	//					if(flag){ //case 1: first slot is empty -> 2nd slot is a negative monom
	//						list.add(new Monom("-" + arr2[j]));
	//
	//						flag = false;
	//					}	else if(j == 0 && !flag){ //case 2: first slot is not empty -> 1st slot is a positive monom
	//						list.add(new Monom(arr2[j]));
	//
	//					}else //after spliting by "-" the rest are negative.
	//						list.add(new Monom("-" + arr2[j]));	
	//
	//				}	
	//			}
	//
	//		} //sort the list so the polynom is printed correctly.
	//		list = sortAndMerge(list);
	//	}

	/**
	 * this constractor is copy a polinom to new polinom
	 * @param p2 represent the polinom that we want to copy
	 */
	public Polynom(Polynom p2){
		this();
		Iterator<Monom> iter = p2.iteretor();
		while(iter.hasNext()) {
			Monom m = iter.next();
			this.add(new Monom(m));
		}
	}
	/**
	 * this function is disply the polinom as a String
	 */
	public String toString() {
		if(this.isZero()) {
			return "0";
		}
		else{
			return list.toString();
		}
	}

	/**
	 * this function is use the monom function and get a value for x and return the value of the polinom 'y' at the point 'x' 
	 */

	public double f(double x){
		double ans = 0;
		Iterator<Monom> iter = this.iteretor();
		while(iter.hasNext()) {
			Monom m = iter.next();
			ans += m.f(x);
		}
		return ans;
	}


	/**
	 * this function is adding a new monom to the polinom that we work with
	 * @param m1 represent the monom that we want to add
	 */

	public void add(Monom m1) {
		if(m1._coefficient == 0.0) return;
		boolean is_there=false;
		Monom help;
		Iterator<Monom> mymonom = this.iteretor();
		while(mymonom.hasNext())
		{
			help = mymonom.next();
			if(help._power==m1._power)
			{
				help.add(m1);
				is_there = true;
				break;
			}
		}
		if(!is_there)
		{
			list.add(m1);
			list.sort(copmerator);
		}
	}


	/**
	 * this function is adding a new polinom to the polinom we work with
	 * @param p1 represent the polinom that we want to add
	 */
	public void add(Polynom p1)
	{
		Iterator<Monom> iterP1=p1.iteretor();
		while(iterP1.hasNext())
		{
			Monom takeMon = iterP1.next();
			this.add(takeMon);
		}
	}
	/**
	 * this function is substract new monom to the polinom that we work with
	 * @param m1 represent the monom that we want to add
	 */
	public void substract(Monom m1) {
		boolean is_there=false;
		Monom help;
		Iterator<Monom> mymonom = this.iteretor();
		while(mymonom.hasNext())
		{
			help = mymonom.next();
			if(help._power==m1._power)
			{
				help.substract(m1);
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i)._coefficient == 0.0) list.remove(i);
				}
				is_there = true;
				break;
			}
		}
		if(!is_there)
		{
			m1 = new Monom(-(m1._coefficient), m1._power);
			list.add(m1);
			list.sort(copmerator);
		}
	}

	/**
	 * this function is substract new polinom to the polinom that we work with
	 * @param p1 represent the monom that we want to add
	 */

	public void substract(Polynom p1)
	{
		Iterator<Monom> iterP1=p1.iteretor();
		while(iterP1.hasNext())
		{
			Monom takeMon = iterP1.next();
			this.substract(takeMon);

		}
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i)._coefficient == 0.0) list.remove(i);
		}
	}


	/**
	 * this function get polinom and it multiply it with the polinom that we warkink with
	 * its standart multiply, each monom multiply every monom in the second polinom 
	 */
	public void multiply(Polynom p1) {
		//Iterator<Monom> iterP1 = p1.iteretor();
		Iterator<Monom> thisIter = this.iteretor();

		Polynom tempPoly = new Polynom();

		while(thisIter.hasNext())
		{
			Monom takeMon = thisIter.next();
			//                    System.out.println("our mon: "+ takeMon);
			//                    Monom temp = new Monom(takeMon);

			for(int i = 0; i < p1.size(); i++){
				double coef = p1.list.get(i).get_coefficient();
				int pow = p1.list.get(i).get_power();

				Monom temp = Monom.multiply_Monom(takeMon, new Monom(coef, pow));
				//                        System.out.println("temp: " + temp);
				tempPoly.add(temp);

			}

		}

		this.list = tempPoly.list;
		//            System.out.println(tempPoly);
	}


	public int size(){
		return this.list.size();
	}

	/**
	 * this function is get polinom and it compere the two polinom
	 * if they equal return true
	 */

	public boolean equals(Polynom p1) {
		boolean answer = false;

		int s1 = this.size();
		int s2 = p1.size();

		if(s1 != s2) return false;
		if(s1 == 0 && s2 == 0) return true;

		Iterator<Monom> thisIter = this.iteretor();
		Iterator<Monom> p1Iter = p1.iteretor();

		while(thisIter.hasNext() && p1Iter.hasNext()){
			Monom m0 = thisIter.next();
			Monom m1 = p1Iter.next();
			if(!m0.equals(m1)){
				answer = false;
			}
			else{
				answer = true;
			}
		}
		return answer;
	}
	/**
	 * thie functuin return 0 if this is zero polinom
	 */

	public boolean isZero() {
		if(this.list.size() == 0) return true;
		Iterator<Monom> thisIter = this.iteretor();
		//int i = 0;
		while(thisIter.hasNext()) {
			Monom temp = thisIter.next();
			if(temp._coefficient != 0.0) return false;
			//i++;

		}
		return true;
	}


	/**
	 * this function cheak if there is a root between 2 pointe of the graph, acording the "coshee sentens" (MISPAT EREH HABINAIM)
	 * it chack the root between 2 points in range of epsilon
	 * @param x0 represent one point in 'x' line
	 * @param x1 represent the secont point in 'x' line
	 * @param eps represent the range of mistake
	 */
	public double root(double x0, double x1, double eps) {//eps epsilon
		double bigger=Math.max(x0, x1);		
		double smaller=Math.min(x0, x1);		
		double mid=0;//mid point (x) between x0,x1
		double c=0;//y val at mid point
		if(this.f(x0)*this.f(x1)>=0)
		{
			System.out.println("eror input");
			return -1;
		}
		else
		{
			while(bigger-smaller>=eps)
			{
				mid=(bigger+smaller)/2;
				c=this.f(mid);
				if(c==0)
				{
					return mid;
				}
				else if(c>0)
				{
					bigger=mid;
				}
				else
				{
					smaller=mid;
				}
			}
			return mid;
		}
	}

	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return the the derivative of the polynom
	 */

	public Polynom derivative() {
		Iterator<Monom>myIter = this.iteretor();
		while(myIter.hasNext())
		{
			Monom get_one =myIter.next();
			if(get_one._power==0)
			{
				myIter.remove();
			}
			else
			{
				get_one._coefficient*=get_one._power;
				get_one._power--;
			}
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(this.list.get(i)._coefficient == 0.0) this.list.remove(i);
		}
		//if(myIter==null)
		//{
		//	return null;
		//}
		return this;
	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */

	public double area(double x0, double x1, double eps) {
		double bigger=Math.max(x0, x1);		
		double smaller=Math.min(x0, x1);	
		double sum=0;
		for(double i=smaller;i<bigger;i=i+eps)
		{
			if(f(i)>0)
			{
				sum+=f(i)*eps;
			}
		}
		return sum;
	}

	public Iterator<Monom> iteretor() {
		return this.list.iterator();
	}

	public static void main(String[] args)
	{
		Monom m1 = new Monom(5,3);
		Monom m2 = new Monom("-2.6x^6");
		Monom m3 = new Monom("-1");

		//create p1 polynom and add monoms
		Polynom p1 = new Polynom();
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);

		//create p2 polynom with deep copy of p1 polynom
		Polynom p2 = new Polynom(p1);
		p2.add(m1);

		System.out.println(p1);
		System.out.println(p2);

		p1.substract(p2);
		System.out.println(p1);
		System.out.println();

		Polynom p = new Polynom();
		p.add(new Monom(1,5));
		p.add(new Monom(1,2));
		p.add(new Monom(-1,5));
		System.out.println(p);
		p.derivative();
		System.out.println(p);
		Polynom pn = new Polynom();
		pn.add(new Monom(2,1));
		pn.add(new Monom());
		System.out.println(pn);
		
		System.out.println();
		p = new Polynom();
		p.add(new Monom(1,5));
		p.add(new Monom(1,2));
		p.add(new Monom(-1,5));
		System.out.println(p);
		p.derivative();
		System.out.println(p);
		pn = new Polynom();
		pn.add(new Monom(2,1));
		pn.add(new Monom());
		pn.add(new Monom());
		System.out.println(pn);
		System.out.println();
		
		p1 = new Polynom();
		p1.add(new Monom());
		p1.add(new Monom());
		p1.add(new Monom());
		System.out.println(p1);
		p2 = new Polynom();
		p2.add(new Monom(1,1));
		p2.add(new Monom(5,0));
		System.out.println(p2);
		p1.substract(p2);
		System.out.println(p1);
		Polynom p3 = new Polynom();
		p3.add(new Monom(-1,1));
		p3.add(new Monom(-5,0));
		System.out.println(p3);

	}	 

}