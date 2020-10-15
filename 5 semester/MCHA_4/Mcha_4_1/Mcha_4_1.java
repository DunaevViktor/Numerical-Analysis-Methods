package mcha_4_1;
import java.text.NumberFormat;

public class Mcha_4_1 {

    public static double F (double x){
        double res = 2*(x*x+1)*Math.sin(x);
        return res;
    }
    public static double P1(double x){
        double res = 2*x;
        return res;
    }
    public static double P2 (double x){
        double res = -1;
        return res;
    }

    public static double[][] FirstCalculation (double h, double x0)
    {//y[0][0]=0; y[0][0]=0;
        double [][] res = new double [2][11];
        res[0][0]=0;res[1][0]=0;
        for(int j=0;j<10;j++){
            double xj=x0+j*h;
            double res0Brackets=F(xj)-P1(xj)*res[1][j]-P2(xj)*res[0][j];
            res[0][j+1]=res[0][j]+h*(res[1][j]+h*0.5*res0Brackets);
            double A = res[1][j]+h*0.5*res0Brackets;
            double B = res[0][j]+h*0.5*res[1][j];
            res[1][j+1]=res[1][j]+h*(F(xj+h*0.5)-P1(xj+h*0.5)*A-P2(xj+h*0.5)*B);
        }
        return res;
    }

    public static double[][] SecondCalculation (double h,double x0)
    {//y[0][0]=1; y[0][0]=0;
        double [][] res = new double [2][11];
        res[0][0]=1;res[1][0]=0;//!!!
        for(int j=0;j<10;j++){
            double xj=x0+j*h;
            double res0Brackets=(P1(xj)*res[1][j]+P2(xj)*res[0][j]);
            res[0][j+1]=res[0][j]+h*(res[1][j]-h*0.5*res0Brackets);
            double A = res[1][j]-h*0.5*res0Brackets;
            double B = res[0][j]+h*0.5*res[1][j];
            res[1][j+1]=res[1][j]-h*(P1(xj+h*0.5)*A+P2(xj+h*0.5)*B);
        }
        return res;
    }
    
    public static double[][] ThirdCalculation (double h,double x0)
    {//y[0][0]=0; y[0][0]=1;
        double [][] res = new double [2][11];
        res[0][0]=0;res[1][0]=1;//!!!
        for(int j=0;j<10;j++){
            double xj=x0+j*h;
            double res0Brackets=(P1(xj)*res[1][j]+P2(xj)*res[0][j]);
            res[0][j+1]=res[0][j]+h*(res[1][j]-h*0.5*res0Brackets);
            double A = res[1][j]-h*0.5*res0Brackets;
            double B = res[0][j]+h*0.5*res[1][j];
            res[1][j+1]=res[1][j]-h*(P1(xj+h*0.5)*A+P2(xj+h*0.5)*B);
        }
        return res;
    }
    
    public static void print (double[][]Matr){
        int stop=6;
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(stop);
        for(int i=0;i<2;i++){
            for(int j=0;j<11;j++){
                System.out.print(formatter.format(Matr[i][j])+"  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        
        double x0=0;
        double y00=0;
        double A0=1;double B0=0;double N0=0;
        double A1=1;double B1=1; double N1=-0.5*Math.cos(0.5);
        double a = 0; double b=0.5;
        double h=(b-a)/10;
        double[][] FirstM = FirstCalculation(h,x0);
        double[][] SecondM = SecondCalculation(h,x0);
        double[][] ThirdM = ThirdCalculation(h,x0);       
        print(FirstM);
        print(SecondM);
        print(ThirdM);             
        double[][] GM = new double [3][2];
        double A = B0*SecondM[0][10]+B1*SecondM[1][10];
        double B=B0*ThirdM[0][10]+B1*ThirdM[0][10];
        double K=B0*FirstM[0][10]+B1*FirstM[1][10];
        double C2 = (A0*(N1-K)-A*N0)/(A0*B-A*A1);
        double C1 = (N0-A1*C2)/A0;
        System.out.println("C1= "+C1+", C2= "+C2);
        System.out.println("xtemp= " + x0 + ", u= " + y00);
        for(int i=1;i<11;i++){            
            double u=FirstM[0][i]+C1*SecondM[0][i]+C2*ThirdM[0][i];
            double xtemp = x0+i*h;
            System.out.println("xtemp= "+xtemp+", u= "+u);
            System.out.println();
        }       

    }  
}
