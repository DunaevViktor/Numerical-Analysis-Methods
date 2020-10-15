package mcha_3_5;
import java.text.NumberFormat;

public class Mcha_3_5 {

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
        double S = 1.0/12.0;
        int i=1;
        double []YVector = new double [3];
        YVector[0]=y;
        while(i<=2){
            x=x0+i*h;
            double f0=h*FCalculation(x,y);
            double f1=h*FCalculation(x+h/2,y+f0/2);
            double f2=h*FCalculation(x+h,y-f0+2*f1);
            y=y+2*S*(f0+4*f1+f2);
            System.out.println("x="+x+", y="+formatter.format(y));
            YVector[i]=y;
            i++;          
        }
        boolean go=true;
        while(go){
            x=x0+i*h;
            i++;
            double A = 23*FCalculation(x,YVector[2]);
            double B=16*FCalculation(x-h,YVector[1]);
            double C=5*FCalculation(x-2*h,YVector[0]);
            double delta = S*h*(A-B+C);
            double newY = YVector[2]+delta;
            YVector[0]=YVector[1];
            YVector[1]=YVector[2];
            YVector[2]=newY;
            System.out.println("x= "+x+", y= "+formatter.format(YVector[2]));
            if(Math.abs(x-xf)<h/2){
                go=false;
            }
        }
    }  
}
