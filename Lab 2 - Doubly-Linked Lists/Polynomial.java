
/**
 * Polynomial.java
 * @author Alex Benny
 * @author Daniel Tran
 * CIS 22C, Lab 2
 */

public class Polynomial {
    private LinkedList<Term> poly;
    
    /**
     * Default Polynomial constructor
     * whose purpose is to initialize poly
     * to an empty LinkedList
     */
    public Polynomial() {
    	poly = new LinkedList<Term>();
       
    }
    
    /**
     * Polynomial copy constructor
     * whose purpose is to initialize poly
     * and then copy the data from p
     * into this Polynomial
     * @param p a Polynomial to copy
     * Hint: Make sure that you create
     * a deep copy not a shallow copy
     * Also: don't forget to check the
     * edge case
     */
    public Polynomial(Polynomial p) {
    	poly = new LinkedList<Term>();
    	if (p  == null || p.poly.getLength() == 0)
    	{
    		poly = new LinkedList<Term>();
    		
    	}
        else 
        {
        	p.poly.positionIterator();
            while (!p.poly.offEnd()) 
            {
            	Term temp = new Term(p.poly.getIterator().getExponent(), p.poly.getIterator().getCoefficient());
            			//p.poly.getIterator();
            	
            	
                this.addTerm(temp.getExponent(),temp.getCoefficient());
                p.poly.advanceIterator();
	        }
        }
    	
    }
    
    /**
     * Adds a new Term to the end of the Polynomial
     * @param exponent the exponent of the Term
     * @param coefficient the coefficient of the Term
     */
     public void addTerm(int exponent, double coefficient) {
    	 Term x = new Term(exponent,coefficient);
    	 poly.addLast(x);
    	 
     }

    /**
     * Assigns a new coefficient to an existing Term
     * @param exponent the exponent of the Term
     * (i.e. its position within the Polynomial)
     * @param newCoefficient the coefficient to assign
     * @precondition 0 <= exponent <= poly.first.exponent
     * @throws IllegalArgumentException when exponent 
     * larger than the exponent of the leading order 
     * Term or less than 0
     */
    public void updateTerm(int exponent, double newCoefficient) throws IllegalArgumentException {
    	 if(!(0 <= exponent && exponent <= poly.getFirst().getExponent())) {
    		 throw new IllegalArgumentException("No data accessed at updateTerm()");
    		 
    	 }
    	 this.poly.positionIterator();
    	 while(!this.poly.offEnd()) {
    		 if(this.poly.getIterator().getExponent() == exponent) {
    			 this.poly.getIterator().setCoefficient(newCoefficient);
    			 this.poly.advanceIterator();
    		 }
    		 else {
    			 this.poly.advanceIterator();
    		 }
    	 }
    }

    /**
     * A helper method for evaluate.
     * Evaluates a single term in the Polynomial by plugging in the value
     * For example: If the term is 3.0x2 and the value is 5.0
     * This function will return 75.0 since 3.0*5.0*5.0=75.0
     * @param t a Term to evaluate
     * @param value the value to plug in for x
     * @return the result of evaluating the Term 
     */
    private double evaluateTerm(final Term t, double value) {
    	
    	
    	double term = Math.pow(value, t.getExponent());
    	term = t.getCoefficient() * term;
        return term;
    }
    
    /**
     * Evaluates the Polynomial by plugging in 
     * the given value at all terms
     * @param value the value to plug into
     * the equation
     * @return the result of evaluating
     * the polynomial by plugging in value for x
     */
    
    public double evaluate(double value) {
    	poly.positionIterator();
    	double evaluate = 0;
    	while(!poly.offEnd()) 
    	{    		
    		evaluate += evaluateTerm(poly.getIterator(), value);
    		poly.advanceIterator();
   	 	}
        return evaluate;
    }
    
    /**
     * Adds two Terms - helper method for add
     * @param t1 the first Term to add
     * @param t2 the second Term to add
     * @return the resulting Term from adding t1 and t2
     * @precondition t1 and t2 cannot be null
     * @precondition t1.exponent == t2.exponent
     * @throws NullPointerException when either t1 or
     * t2 is null
     * @throws IllegalArgumentException when the exponents
     * for t1 and t2 are not the same
     */
    private Term addTerms(Term t1, Term t2) {
    	if (t1 == null || t2 == null)
    	{
            throw new  NullPointerException("Parameter(s) is null");
    	}
    	if (t1.getExponent() != t2.getExponent())
    	{
    		throw new IllegalArgumentException("Paramaters are equal to each other");
    	}
    	Term add = new Term();
    	add.setCoefficient(t1.getCoefficient() + t2.getCoefficient());
    	add.setExponent(t1.getExponent()); //
        return add;
    }
    
    /**
     * Adds p to this Polynomial
     * @param p another Polynomial to add to this
     * @return a new Polynomial resulting
     * from adding p to this
     * Handles two exceptions from calling
     * addTerms by using a try-catch
     */
    /**
     * Adds p to this Polynomial
     * @param p another Polynomial to add to this
     * @return a new Polynomial resulting
     * from adding p to this
     * Handles two exceptions from calling
     * addTerms by using a try-catch
     */
    public Polynomial add(final Polynomial p) { // need to deal with exponents
        Polynomial add = new Polynomial();
        
        p.poly.positionIterator();
        this.poly.positionIterator();
    
        while (!p.poly.offEnd() && !this.poly.offEnd())
        {
            if(p.poly.getIterator().getExponent() > this.poly.getIterator().getExponent())
            {
                add.addTerm(p.poly.getIterator().getExponent(), p.poly.getIterator().getCoefficient());
                if (!p.poly.offEnd())
                {
                    p.poly.advanceIterator();
                }
            }
            else if (p.poly.getIterator().getExponent() < this.poly.getIterator().getExponent())
            {
                add.addTerm(this.poly.getIterator().getExponent(), this.poly.getIterator().getCoefficient());
                if (!this.poly.offEnd())
                {
                    this.poly.advanceIterator();
                }
            }
            else if (p.poly.getIterator().getExponent() == this.poly.getIterator().getExponent())
            {
                Term added = addTerms(this.poly.getIterator(),p.poly.getIterator());
                add.poly.addLast(added);
                if (!p.poly.offEnd())
                {
                    p.poly.advanceIterator();
                }
                if (!this.poly.offEnd())
                {
                    this.poly.advanceIterator();
                }
            }
        }
        
        
        
     return add;
    }
    
    /**
     * Subtracts p from this Polynomial
     * @param p another Polynomial to subtract from this
     * @return a new Polynomial resulting
     * from subtracting p from this
     * @postcondition p remains unchanged
     */
    public Polynomial subtract(final Polynomial p) {
        Polynomial sub = new Polynomial();
        
        p.poly.positionIterator();
        this.poly.positionIterator();
    
        while (!p.poly.offEnd() && !this.poly.offEnd())
        {
            if(p.poly.getIterator().getExponent() > this.poly.getIterator().getExponent())
            {
                
                p.poly.getIterator().setCoefficient(-p.poly.getIterator().getCoefficient());
                sub.addTerm(p.poly.getIterator().getExponent(), p.poly.getIterator().getCoefficient());
                if (!p.poly.offEnd())
                {
                    p.poly.advanceIterator();
                }
            }
            else if (p.poly.getIterator().getExponent() < this.poly.getIterator().getExponent())
            {
                sub.addTerm(this.poly.getIterator().getExponent(), this.poly.getIterator().getCoefficient());
                if (!this.poly.offEnd())
                {
                    this.poly.advanceIterator();
                }
            }
            else if (p.poly.getIterator().getExponent() == this.poly.getIterator().getExponent())
            {
                
                
                Term added = new Term(this.poly.getIterator().getExponent(),
                this.poly.getIterator().getCoefficient() - p.poly.getIterator().getCoefficient());
                
                sub.poly.addLast(added);
                if (!p.poly.offEnd())
                {
                    p.poly.advanceIterator();
                }
                if (!this.poly.offEnd())
                {
                    this.poly.advanceIterator();
                }
            }
        }
        
        
        
     return sub;
    }
    
    /**
     * Converts this Polynomial to a String in the form
     * <coefficient1>x<exponent1> + <coefficient2>x<exponenent2> + ...
     * For example: 2.5x4 + 3x3 + 8.1x1 + 7.5
     * @return the Polynomial represented as a String
     */
    @Override public String toString() {
    	poly.positionIterator();
    	String str = "";
    	while(!poly.offEnd()) 
    	{    		
    		str += poly.getIterator().toString();
    		poly.advanceIterator();
    		if (!poly.offEnd() && poly.getIterator().toString() != "")
    		{
    			str += " + ";
    		}
   	 	}
        return str + "\n";
    }
    
}