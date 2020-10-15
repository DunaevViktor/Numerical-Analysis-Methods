package mcha_3_2;
import java.text.NumberFormat;

public class Mcha_3_2 {

       public static double FCalculation(double x, double y){
        double res ;
        res = y-Math.sqrt(y*y+x*x)+x-1;
        return res;
    }

    public static double NbutonFuncCalculation(double y,double x,double yprev,double h){
        double F = y-yprev-h*FCalculation(x,y);
        return F;
    }
    public static double NbutonMethodCycle(double yG, double x, double h){
        double y=yG+h*FCalculation(x,yG);double yprev=yG;
        double delta = Math.abs(y-yprev);
        while(delta>h*h){
            double yJ=y;
            y=y-NbutonFuncCalculation(y,x,yprev,h)/(1-h*FCalculation(x,y));  
            delta =Math.abs(y-yJ);      
        }
        return y;
    }

    public static void main(String[] args) {
        
        int stop=5;
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(stop);
        double x0=1;double xf=2;
        double h=0.1;
        double x = x0;
        double y=1;
        double yprev=y;
        double ysave;
        boolean go=true;
        int i=1;
        while(go){ 
            ysave = y;
            x=x0+i*h;
            i=i+1;
            y=NbutonMethodCycle(y,x,h);          
            yprev = ysave;
            System.out.println("x= "+x+", y= "+formatter.format(y));
            if(Math.abs(x-xf)<h/2)
            {
                go=false;
            }
        }    
    }   
}
