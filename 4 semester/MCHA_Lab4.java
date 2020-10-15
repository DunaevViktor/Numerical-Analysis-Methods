public class Mcha_4 {
    public static void main(String[] args) {  
        double[][]df0 = {{-1.33, 0.169},{1.2, 1.6}};
        double[] xPrev = {1, 2}; 
        double[] xNow = new double[2];
        double[] fNow = new double[2];
        double[] dxNow = new double[2];
        double eps = 0.00001;
        double temp = 10000000;
        int i = 0;
        while(temp>eps){   
            fNow[0]=Math.sin(xPrev[0]+xPrev[1]) - 1.5*(xPrev[0]); 
            fNow[1]=xPrev[0]*xPrev[0] + xPrev[1]*xPrev[1] - 1;
            dxNow[0]=df0[0][0]*fNow[0]+ df0[0][1]*fNow[1];
            dxNow[1]=df0[1][0]*fNow[0]+ df0[1][1]*fNow[1];
            temp=Math.max(Math.abs(dxNow[0]), Math.abs(dxNow[1]));
            xNow[0]=xPrev[0]-dxNow[0];
            xNow[1]=xPrev[1]-dxNow[1];
            xPrev=xNow;
            i++;
        }
        System.out.println("X = ("+xPrev[0]+ ", "+xPrev[1]+")");
        System.out.println("Количество итераий: "+i);
        double check1 = Math.sin(xPrev[0]+xPrev[1]) - 1.5*(xPrev[0]);
        System.out.println("Проверка F1: "+check1);
        double check2 = xPrev[0]*xPrev[0] + xPrev[1]*xPrev[1] - 1;
        System.out.println("Проверка F2: "+check2);
    }
}