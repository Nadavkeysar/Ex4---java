package pack;

/**
 * This class represents a simple "Monom" of shape a*x^b,
 *  where a is a real number and b none negative integer
 * see: https://en.wikipedia.org/wiki/Monomial
 * The class support simple operations as:
 * construction, value at x, derivative, add and multiply.
 *  When constructor gets Monom as string, number of possible
 * shapes: String[] monoms = {"2", "-x","-3.2x^2","0"};
 * @author Boaz
 *
 */

public class Monom {

    double _coefficient;
    int _power;


    /**
     * Default constructor. set class values to 0.
     */
    public Monom(){
        this.set_coefficient(0);
        this.set_power(0);
    }

    /**
     * Creates a Monom.
     * @param a -> the Monoms coefficient
     * @param b -> the Monoms power
     */
    public Monom(double a, int b){
        if(b<0)
        {
            throw new RuntimeException("Power must be Non-Negative."+get_power());
        }
        this.set_coefficient(a);
        this.set_power(b);
    }
    /**
     * Copy constructor.
     * @param ot -> different Monom to copy from.
     */
    public Monom(Monom ot) {
        this(ot.get_coefficient(), ot.get_power()); //this is a call to the constructor.
    }



    /**
     * Creates Monom from String.
     * Uses init_from_string function.
     * @param s -> the String that will be a monom
     */
    public Monom(String s) {
        Monom th = init_from_string(s);
        this.set_coefficient(th.get_coefficient());
        this.set_power(th.get_power());
    }
    /**
     * Wrapper function to initiate a Monom from a given String.
     * ensure correctness of the String for a proper output.
     * @param s represent the input String
     * @return return a monom acording to the String input
     */
    private static Monom init_from_string(String s) {
        if(s.startsWith("+")) s = s.replaceFirst("\\+", "");
        if(s == null) {
            throw new RuntimeException("String is Null.");
        }
        s = s.replaceAll("\\s",""); //to avoid spaces

        double  coef = 1;
        int pow = 0;
        Monom ans = new Monom();

        if(s.contains("x")) {
            int ind = s.indexOf("x");
            String co = s.substring(0, ind);
            try{
                double c = Double.parseDouble(co);
                coef = c;
            }
            catch(Exception e) {coef = 1;}

            if(s.contains("^")){
                int powerIndex = s.indexOf("^");
                String po = s.substring(powerIndex +1);
                try{
                    int p = Integer.parseInt(po);
                    pow = p;
                }
                catch(Exception e) {pow = 1;}
            }else
                pow = 1;
        }else
            coef = Double.parseDouble(s); //just a  number.
        ans = new Monom(coef, pow);
        return ans;
    }
    /**
     * get power.
     * @return return the power of the monom
     */
    public int get_power() {
        return _power;
    }
    /**
     * get coeeficient.
     * @return return the coefficient of the monom
     */
    public double get_coefficient() {
        return _coefficient;
    }
    /**
     * set coefficient.
     * @param a represent the new coefficient
     */
    private void set_coefficient(double a){
        this._coefficient = a;
    }
    /**
     * set power.
     * @param a represent the new power
     */
    private void set_power(int p) {
        this._power = p;
    }
    /**
     * this function get value for the monom 'x', and return the 'x' value
     * @param x the x value for the monom
     * @return return the 'y' value of the monom
     */
    public double f(double x) {
        return get_coefficient() * (Math.pow(x, get_power()));
    }
    /**
     * this function calculates the derivative for x.
     * @param x represent the point for the derivative
     * @return value of derivative.
     */
    public Monom derivative(){
    	if(this._coefficient == 0 || this._power == 0) return new Monom();
        return new Monom(this._coefficient*this._power, this._power-1);
    }

    /**
     * this function make sum add 2 monom (the sum of the 2 monoms) if they have diferent power it send exeption
     * @param f2 -> the new monom that the function will add to the monom
     */
    public void add(Monom f2){
        if(f2.get_power() == this._power){
            this._coefficient += f2.get_coefficient();
        }
        else
            throw new IllegalArgumentException("illegal move");
    }

    /**
     * this function substract the monom with the new input monom
     * @param f2 -> the new monom that sub the monon
     */
    public void substract(Monom f2){
        if(f2.get_power() == this._power){
            this._coefficient -= f2.get_coefficient();
        }
        else
            throw new IllegalArgumentException("illegal move");
    }

    /**
     * this function multyply the monom with the new input monom
     * @param f2 -> the new monom that multiply the monon
     */
    public void multiply(Monom f2){
        this._coefficient *= f2.get_coefficient();
        this._power += f2.get_power();
    }
    
    public static Monom multiply_Monom(Monom f1, Monom f2){
        double coef = f1.get_coefficient();
        int pow = f1.get_power();
        
        coef *= f2.get_coefficient();
        pow += f2.get_power();
        
        Monom m = new Monom(coef, pow);
        return m;
    }

    /**
     * this function is disply the monom as a String
     */
    public String toString(){
        return get_coefficient()+"*X^("+get_power()+")";
    }

    /**
     * tests if this is equal to m
     * @param m -> Monom m to test against.
     */
    public boolean equals(Monom m)
    {
    	if(this._coefficient == 0.0 && m._coefficient == 0.0) return true;
        if(this._coefficient == m._coefficient && this._power == m._power)
            return true;

        return false;
    }

    /**
     * compares two Monoms.
     * @param m -> Monom m to test against.
     * @return int
     */
    public int compareTo(Monom m)
    {
        if(this._coefficient == m._coefficient && this._power == m._power)
            return 0;

        if(this._power < m._power)
            return -1;
        else
            return 1;
    }
    public boolean isZero() {
    	if(this._coefficient == 0.0) return true;
    	return false;
    }
    
    public static void main(String[] args) {

        Monom m1 = new Monom();
        System.out.println("m1 to string: " + m1.toString());

        Monom m2 = new Monom(m1);
        System.out.println("m2to string: " + m2.toString());

        Monom m3 = new Monom("+5x");
        System.out.println("m3 to string: " + m3.toString());
        
        System.out.println("testing compareTo function:");
        System.out.println(m3.compareTo(m2));

        System.out.println("testing equals:");
        System.out.println(m3.equals(m2));
    }

}

