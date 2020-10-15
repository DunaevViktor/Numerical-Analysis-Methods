package mcha_3_1;
import java.text.NumberFormat;

public class Mcha_3_1 {

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
        double x=x0;
        int i=1;
        boolean go=true;
        while(go){                 
            y2=y+(h)*FCalculation(x,y);
            x=x0+i*h;  
            i=i+1;
            y=y2;
            System.out.println("x= "+x+", y= "+formatter.format(y));
            if(Math.abs(xf-x)<h/2){
                go=false;
            }
        }       
    }      
}
    
