package mcha_1_1;

public class Mcha_1_1 {

    public static class Lab {
    private double e;
    private double a;
    private double b;

    public Lab() {
        e = Math.pow(10, -5);
        a = 1;
        b = 2;
    }

    private double F(double x) {
        return Math.log(x) / x;
    }

    public double N_MiddleRectangle() {
        return Math.ceil(Math.pow((Math.pow(b - a, 3) * (0.125) / (24 * e)), 0.5));
    }

    private double rMiddleRectangle(double h){
        double answer = 0;
        answer = (Math.pow(h,2)*(b-a)* (0.125))/24;
        return answer;
    }

    public double middleRectangleMethod() {
        double answer = 0;
        double N = N_MiddleRectangle();
        double h = (b - a) / N;
        for (int i = 0; i < N; i++) {
            answer += F(a + i * h + h / 2);
        }
        answer *= h;
        System.out.println("N middle rectangle method: " + N);
        System.out.println("h middle rectangle method: " + h);
        System.out.println("R middle rectangle method: " + rMiddleRectangle(h));
        return answer;
    }

    public double rightRectangleMethod(double N, double h) {
        double answer = 0;
        for (int i = 1; i <= N; i++) {
            answer += F(a + i * h);
        }
        answer *= h;
        return answer;
    }

    private int getFactorial(int num) {
        int answer = 1;
        for (int i = 0; i < num; i++) {
            answer *= num - i;
        }
        return answer;
    }

    private double getDerivetive(int k) {

        switch (k) {
            case 0:
                return 0.347;
            case 1:
                return 1;
            case 2:
                return -0.125;
            case 3:
                return 11;
            case 4:
                return -1;
        }
        return k;
    }

    public double getRGauss(int n) {
        double R = 0;
        double a = (Math.pow(2, 2 * n + 3) / ((2 * n + 3) * getFactorial(2 * n + 2)));
        double b1 = getFactorial(n + 1);
        double b2 = getFactorial(2 * n + 2);
        double b = Math.pow(b1 / b2, 2);
        double c = getDerivetive(2 * n + 2);
        R = Math.abs(a * b * c);
        return R;
    }

    public int getNumOfPoints() {
        int n = 0;
        while (getRGauss(n) >= e) {
            n++;
        }
        return n + 1;
    }

    private double newFunction(double t) {
        return (Math.log((t+3)/2))/(t+3);
    }

    public double GaussFormula() {
        int n = getNumOfPoints();
        System.out.println("Number of Gauss points: " + n);
        System.out.println("R Gauss: " + getRGauss(n));
        double answer = 1 * newFunction(-0.5773503) + 1 * newFunction(0.5773503);
        //?
        return answer;
    }

    private double rungeR(double h1, double h2, double N1, double N2){
        return Math.abs((rightRectangleMethod(N1, h1) - rightRectangleMethod(N2, h2)) / (1 - h2 / h1));
    }

    public double RungeMethod(){
        double h1 = 0.5;
        double h2 = h1 / 2;
        int N1 = (int)((b-a)/h1);
        int N2 = (int)((b-a)/h2);
        while(rungeR(h1, h2, N1, N2) >= e){
            h1 = h2;
            h2 = h1 / 2;
            N1 = (int)((b-a)/h1);
            N2 = (int)((b-a)/h2);
        }
        System.out.println("R Runge: " + rungeR(h1,h2,N1,N2));
        System.out.println("N Runge: " + N2);
        System.out.println("h Runge: " + h2);
        return rightRectangleMethod(N2, h2);
    }

}
    
    public static void main(String[] args) {
       
        Lab l = new Lab();
        System.out.println("Integral Runge: " + l.RungeMethod() + "\r\n");
        System.out.println("Integral Gauss: " + l.GaussFormula() + "\r\n");
        System.out.print("Integral Middle Rectangle: " + l.middleRectangleMethod());
    } 
}
