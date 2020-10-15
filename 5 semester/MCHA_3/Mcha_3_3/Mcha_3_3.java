package mcha_3_3;
import java.text.NumberFormat;

public class Mcha_3_3 {
//метод последовательного повышения порядка точности
//- формула средних прямоугольников
    
    public static double FCalculation(double x, double y){
        double res ;
        res = y-Math.sqrt(y*y+x*x)+x-1;
        return res;
    }
    
    public static void main(String[] args) {
        
        int stop=5;
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(stop);
        double x0=1;
        double xf=2;
        double y=1;//y[0]=1
        double y2=1;
        double h = 0.1;
        double x=x0;
        double halfH = h/2;
        boolean go=true;
        int i=1;
        while(go){              
            double preY = y+(halfH)*FCalculation(x,y);
            y = y +h*FCalculation(x+halfH,preY);
            x=x0+i*h;
            i=i+1;
            System.out.println("x= "+x+", y= "+formatter.format(y));
           if(Math.abs(x-xf)<h/2){
               go=false;
           }
        }               
    }   
}
