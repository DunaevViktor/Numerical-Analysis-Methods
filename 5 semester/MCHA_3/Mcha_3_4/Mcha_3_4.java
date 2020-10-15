package mcha_3_4;
import java.text.NumberFormat;

public class Mcha_3_4 {

     public static double FCalculation(double x, double y){
        double res ;
        res = y-Math.sqrt(y*y+x*x)+x-1;
        return res;
    }
     
    public static void main(String[] args) {
        
        int stop=5;
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(stop);
        double x0=1;double xf=2;
        double y=1;//y[0]=1
        double y2=1;
        double h = 0.1;
        double x=x0;double halfH = h/2;
        double C = 1.0/6.0;
        boolean go=true;int i=1;
        while(go){
            x=x0+i*h;
            double f0=h*FCalculation(x,y);
            double f1=h*FCalculation(x+h/2,y+f0/2);
            double f2=h*FCalculation(x+h,y-f0+2*f1);
            y=y+C*(f0+4*f1+f2);
            System.out.println("x= "+x+", y= "+formatter.format(y));
            i++;
            if(Math.abs(x-xf)<h/2){
                go=false;
            }
        }
    }  
}
