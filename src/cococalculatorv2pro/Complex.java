package cococalculatorv2pro;

/**
 * Maneja números complejos y sus operaciones
 * @author ivan_
 */
public class Complex 
{
    private Double real;
    private Double imaginary;
    
    /**
     * Inicializa en 0
     */
    Complex()
    {
        this.real = 0.0;
        this.imaginary = 0.0;
    }
    
    /**
     * Inicializa según parámetros
     * @param r Parte real
     * @param i Parte imaginaria
     */
    Complex(double r, double i)
    {
        this.real=r;
        this.imaginary=i;
    }
    
    /**
     * 
     * @return parte real 
     */
    public Double getReal() 
    {
        return real;
    }

    /**
     * Setea la parte real
     * @param real 
     */
    public void setReal(Double real) 
    {
        this.real = real;
    }

    /**
     * 
     * @return parte imaginaria
     */
    public Double getImaginary() 
    {
        return imaginary;
    }

    /**
     * Setea parte imaginaria
     * @param imaginary 
     */
    public void setImaginary(Double imaginary) 
    {
        this.imaginary = imaginary;
    }
    
    /**
     * Realiza la operacion de a+b
     * @param a
     * @param b
     * @return a+b
     */
    public Complex add(Complex a, Complex b)
    {
        return new Complex(a.getReal()+b.getReal(),a.getImaginary()+b.getImaginary());
    }
    
    /**
     * Convierte el número en un string legible
     * @return número complejo
     */
    @Override
    public String toString()
    {
        String complement;
        if(this.imaginary>0)
        {
            complement = "+"+imaginary+"i)";
        }
        else
        {
            complement = "-"+imaginary+"i)";
        }
        return "("+this.real+complement;
    }

    /**
     * Realiza a-b
     * @param a
     * @param b
     * @return  a-b
     */
    public Complex subtract(Complex a, Complex b) 
    {
        return new Complex(a.getReal()-b.getReal(),a.getImaginary()-b.getImaginary());
    }
    
    /**
     * Realiza a*b
     * @param a
     * @param b
     * @return a*b
     */
    public Complex multiply(Complex a, Complex b)
    {
        return new Complex(a.getReal()*b.getReal()-a.getImaginary()*b.getImaginary(),a.getReal()*b.getImaginary()+b.getReal()*a.getImaginary());
    }
    
    /**
     * Realiza a/b y retorna en formato para imprimir
     * @param a
     * @param b
     * @return a/b en String
     */
    public String divide(Complex a, Complex b)
    {
        Complex conj = new Complex(b.getReal(), (b.getImaginary()*-1.0));
        Complex aNew = a.multiply(a, conj);
        Complex bNew = b.multiply(b, conj);
        String out =  "("+aNew.getReal()+"/"+bNew.getReal();
        if(aNew.getImaginary()>0)
        {
            out+="+";
        }
        out += aNew.getImaginary()+"i/"+bNew.getReal()+")";
        return out;
    }
}