package mcha_1_2;
public class Mcha_1_2 {
    public static double D2func (double x){
        double res = (2*Math.log(x)-3)/(Math.pow(x,3));
        return res;
    }
    public static double D4func (double x){
        double res = (24*Math.log(x)-50)/(Math.pow(x,5));
        return res;
    }
    public static double func (double x){
        double res = Math.log(x)/x;
        return res;
    }
    public static void main(String[] args) {
        
        double a=1;
        double b= 2;
        double E = 0.00001;
        double h =Math.sqrt((-1)*(12*E)/(D2func(0.1)*(b-a)));
        System.out.println("M2="+D2func(0.1));
        h=h/100;        
        System.out.println("H1="+h);  
              
        double Its=0;
        Its=h*(func(a)+func(b))*0.5;
        for(int i=1;i<(b-a)/h;i++){
            Its=Its+h*func(a+i*h);
        }
        double N = (b-a)/h;
       
        System.out.println("N="+N);
        System.out.println("I(trapecii)="+Its);
    }   
}
