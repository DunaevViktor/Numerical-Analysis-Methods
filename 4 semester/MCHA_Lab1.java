package mcha_lab1;
import java.text.NumberFormat;
public class MCHA_Lab1 {
    public static void main(String[] args) {
        int stop = 6;
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(stop);
        double E = Math.pow(10,-6);
        double xK1=-0.6;
        double xK =xK1+100;
        int i = 1;
        double q = 0.202;
        double m = Math.abs(((-Math.cos(xK)-1)/3) - xK1);
        int k = (int) (Math.log10(E*(1-q)/m)/Math.log10(q));       
        k++;
        System.out.println("Априорная оценка количества итераций: " + k);       
        while(Math.abs(xK-xK1)>E)
        {
            i++;
            xK=xK1;
            xK1 = ((-Math.cos(xK)-1)/3);
        }
        System.out.println("Апостериорная оценка количества итераций: " + i);
        System.out.println("Корень = " + formatter.format(xK1));
        double n = 3*xK1 + Math.cos(xK1) + 1;
        System.out.println("Вектор невязки = "+ formatter.format(n));        
    }        
}
