package chebishev_man;
import java.text.NumberFormat;

public class Chebishev_MAN {

       public static void main(String[] args) {
        int stop = 6;
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(stop);
        double E = Math.pow(10,-6);
        double xK1=-0.6;
        double xK =xK1+100;
        int i = 1;  
        while(Math.abs(xK-xK1)>E)
        {
            i++;
            xK=xK1;
            double f  = 3*xK + Math.cos(xK) + 1;
            double f1 = 3 - (Math.sin(xK));
            double f2 =  -(Math.cos(xK));
            xK1 = xK - f / f1 - ( Math.pow(f,2)* f2 ) / ( 2 * Math.pow(f1,3));           
        }
        System.out.println("Апостериорная оценка количества итераций: " + i);
        System.out.println("Корень = " + formatter.format(xK1));
        double n = 3*xK1 + Math.cos(xK1) + 1;
        System.out.println("Вектор невязки = "+ formatter.format(n));        
    }    

    
}
