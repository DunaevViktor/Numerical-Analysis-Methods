package mcha5;

class LagrangePolynom {
    private double[] x;
    private double[] y;

    LagrangePolynom(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    public double[] theoretical(double[] p){
            double[] result = new double[p.length];
            for(int j = 0;j<p.length;j++){
            double w = p[j] - x[0];
            for (int i = 1; i <= x.length-1; ++i)
            {
                w *= (p[j] - x[i]);
                w /= i;
            }
                w = Math.abs(w);
                result[j] = -1088664.07 * w;
            }
        return result;
}
    
    private double calculate(double value) {
        double result = 0.0;
        for (int i = 0; i < y.length; i++) {
            double P = 1.0;
            for (int j = 0; j < x.length; j++) {
                if (j != i) {
                    P *= (value - x[j]) / (x[i] - x[j]);
                }
            }
            result += P * y[i];
        }
        return result;
    }

    public double[] findSolution(double[] points) {
        double[] result = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            result[i] = calculate(points[i]);
        }
        return result;
    }
}

class NewtonPolynom {
    private double[] x;
    private double[] y;
    private double[][] rr;
    private int size;

    NewtonPolynom(double[] x, double[] y) {
        this.size = x.length;
        this.x = x;
        this.y = y;
        rr = new double[size][size];
    }

    public void createMatrix() {
        for (int i = 0; i < size; ++i)
            rr[i][0] = y[i];
        for (int j = 1; j < size; ++j)
            for (int i = 0; i < size - j; ++i)
                rr[i][j] = (rr[i + 1][j - 1] - rr[i][j - 1]) / (x[i + j] - x[i]);

    }

    private double calculate(double value) {
        double res = 0, add;
        for (int i = 0; i < size; ++i) {
            add = rr[0][i];
            for (int j = 0; j < i; ++j)
                add *= (value - x[j]);
            res += add;
        }
        return res;

    }

    public double[] findSolution(double[] points) {
        createMatrix();
        double[] result = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            result[i] = calculate(points[i]);
        }
        return result;
    }

    public double[][] getRr() {
        return rr;
    }
}

class Equidistant {
    private double[] x;
    private double[] y;
    private double[][] kr;
    private double h;
    private int size;

    public Equidistant(double[] x, double[] y) {
        this.size = x.length;
        this.h = x[1] - x[0];
        this.x = x;
        this.y = y;
        this.kr = new double[size][size];
        this.createMatrix();
    }

    private void createMatrix() {
        for (int i = 0; i < size; ++i)
            kr[i][0] = y[i];
        for (int j = 1; j < size; ++j)
            for (int i = 0; i < size - j; ++i)
                kr[i][j] = (kr[i + 1][j - 1] - kr[i][j - 1]);
    }

    private double newtonPolynom (double value)
    {
        double t = (value - x[0]) / h;
        double res = kr[0][0];
        double mul = t;
        for (int i = 1; i < size; ++i)
        {
            res += (kr[0][i] * mul);
            mul *= ((t - i) / (i + 1));
        }
        return res;
    }

    public double[] findSolution(double[] points) {
        double[] result = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            result[i] = newtonPolynom(points[i]);
        }
        return result;
    }

    public double[][] getKr(){
        return kr;
    }
}

class Hermit {
    private double[] x;
    private double[] y;
    private double[] ders;
    private double[][] rr;
    private int size;

    public Hermit(double[] x, double[] y, double[] ders) {
        this.size = x.length;
        this.x = x;
        this.y = y;
        this.ders = ders;
        this.rr = new double[size][size];
        this.createMatrix();
    }

    private void createMatrix() {
        for (int i = 0; i < size; ++i)
            rr[i][0] = y[i];
        for (int i = 0; i < size - 1; ++i)
            rr[i][1] = (x[i] == x[i + 1] ? ders[i] : (rr[i + 1][0] - rr[i][0]) / (x[i + 1] - x[i]));
        for (int j = 2; j < size; ++j)
            for (int i = 0; i < size - j; ++i)
                rr[i][j] = (rr[i + 1][j - 1] - rr[i][j - 1]) / (x[i + j] - x[i]);
    }

    private double hermitPolynom(double value) {
        double res = 0, add;
        for (int i = 0; i < size; ++i) {
            add = rr[0][i];
            for (int j = 0; j < i; ++j)
                add *= (value - x[j]);
            res += add;
        }
        return res;
    }

    public double[] findSolution(double[] points) {
        double[] result = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            result[i] = hermitPolynom(points[i]);
        }
        return result;
    }

    public double[][] getRr() {
        return rr;
    }
}

public class Mcha5 {

    public static void main(String[] args) {
        
        double[] x = {1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2};
        double[] y = {1.4305, 1.4483, 1.4568, 1.4543, 1.4397, 1.4119, 1.3702, 1.3143, 1.2440, 1.1594, 1.0606};
        double[] p = {1.025, 1.55, 1.975};
        LagrangePolynom lp = new LagrangePolynom(x, y);
        double[] res = lp.findSolution(p);
        
        double[] theor = lp.theoretical(p); 
        for(int i = 0;i<res.length;i++){
            System.out.println("TR"+(i+1)+" = "+theor[i]);
            }
        
        for(int i = 0;i<res.length;i++){
            System.out.println(res[i]);
        }
        System.out.println();
        //////////////////////////////
        NewtonPolynom np = new NewtonPolynom(x,y);
        np.createMatrix();
        res = np.findSolution(p);
        for(int i = 0;i<res.length;i++){
            System.out.println(res[i]);
        }
        double [][]rr = np.getRr();
        for(int i = 0;i<11;i++){
            for(int j = 0;j<11;j++){
                System.out.print(rr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        //////////////////////////////
        Equidistant e = new Equidistant(x,y);
        res = e.findSolution(p);
        for(int i = 0;i<res.length;i++){
            System.out.println(res[i]);
        }
        double [][]kr = e.getKr();
        for(int i = 0;i<11;i++){
            for(int j = 0;j<11;j++){
                System.out.print(kr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        /////////////////////////////
        double[] x1 = {1,1, 1.5,1.5, 2,2};
        double[] y1 = {1.4305,1.4305, 1.4119,1.4119, 1.0606,1.0606};
        double[] yder = {0.2185,0.2185, -0.3464,-0.3464, -1.0574,-1.0574};
        double[] p1 = {1.025,1.025, 1.55,1.55, 1.975,1.975};
        Hermit h = new Hermit(x1,y1, yder);
        res = h.findSolution(p);
        for(int i = 0;i<res.length;i++){
            System.out.println(res[i]);
        }
        rr = h.getRr();
        for(int i = 0;i<rr.length;i++){
            for(int j = 0;j<rr.length;j++){
                System.out.print(rr[i][j]+" ");
            }
            System.out.println();
        }
    }
        
    
    
}
