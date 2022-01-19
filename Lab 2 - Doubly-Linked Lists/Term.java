/**
 * Term.java
 * @author jenniferparrish
 * @author Alex Benny
 * @author Daniel Tran
 * CIS 22C, Lab 2
 */

public class Term
{
    private double coefficient;
    private int exponent;
   
    /**
     * Default constructor for Term
     */
    public Term() {
        coefficient = 0.0;
        exponent = 0;
    }
    
    /**
     * Copy constructor for Term
     * @param t another Term to copy
     */
    public Term(Term t) {
        if(t == null) {
            return;
        }
        coefficient = t.coefficient;
        exponent = t.exponent;
    }
    
    /**
     * Two-argument Term constructor
     * @param exponent the exponent of the Term
     * @param coefficient the coefficient of the Term
     */
    public Term(int exponent, double coefficient) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    /**
     * Accesses the coefficient of the Term
     * @return the Term's coefficient
     */
    public double getCoefficient() {
        return coefficient;
    }
    
    /**
     * Accesses the exponent of the Term
     * @return the Term's exponent
     */
    public int getExponent() {
        return exponent;
    }

    /**
     * Assigns a new coefficient to a Term
     * @param coefficient the Term's new coefficient
     */
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * Assigns a new exponent to a Term
     * @param exponent the Term's new exponent
     */
    public void setExponent(int exponent) {
        this.exponent = exponent;
    }
    
    /**
     * Converts the Term to a String
     * in the form <coefficient>x<exponent>
     * If the exponent is 0, does not include
     * x or the exponent
     * If the exponent is 1, does not include 
     * the 1
     * If the coefficient is 0, returns
     * an empty String
     * @return a String representation of the Term
     */
    @Override 
    public String toString() {
       //YOU FILL IN HERE
    	String str = "";
    	if(coefficient == 0) {
    		return "";
    	}
    	if(exponent == 0) {
    		return str + coefficient;
    	}
    	if(exponent == 1) {
    		return str + coefficient + "x";
    	}
    	else {
    		return str + coefficient + "x" + exponent;
    	}
    }
}