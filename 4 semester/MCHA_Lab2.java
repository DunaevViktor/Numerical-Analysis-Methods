package mcha_lab2;
import java.text.NumberFormat;

public class Mcha_lab2 {

    public static void main(String[] args) {
        int stop = 6;
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(stop);
        
        double x0 = -0.6;
        double x1 = 0;
        int count = 0;
        double a = 0.1;
        double eps = Math.pow(10,-6);
	do {
            x1 = x0;
            x0 = x1-((3*x1 + Math.cos(x1) + 1)/(3 - Math.sin(x1)));
            count++;
        } while (Math.abs(x1 - x0) > eps);

        double x4 = 1.5;
        double x3 = 0;
        x3 = x4;
        x4 = x3-((3*x3 + Math.cos(x3) + 1)/(3 - Math.sin(x3)));
        double aprior = (Math.log(eps * a) / Math.log(a*Math.abs(x4-x3)));
        double n = 3*x0 + Math.cos(x0) + 1;
        
        System.out.println("Априорная оценка количества итераций: " + formatter.format(aprior));
        System.out.println("Апостериорная оценка количества итераций: " + count);
        System.out.println("Корень = " + formatter.format(x0));
        System.out.println("Вектор невязки: " + formatter.format(n)); 
    }
    
}
