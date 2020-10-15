import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class FredholmEquation {

    private double leftSide;

    private double rightSide;

    private int N;

    private double h;

    private double lambda = 0.1;

    public FredholmEquation(double leftSide, double rightSide, int n) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        N = n;
        this.h = (this.rightSide - this.leftSide) / this.N;
    }


    private double K(double x_i, double s) {

        return Math.exp(x_i + s);
    }

    private double f(double x_i) {

        return 1 + Math.exp(2 * x_i) - Math.exp(x_i);
    }

    public List<Pair<Double, Double>> solveRightRectangles() throws Exception {

        double[][] sysMatrix = new double[this.N + 1][this.N + 1];
        double[] x = new double[this.N + 1];
        double[] f_i = new double[this.N + 1];
        // вычисление Xi

        for (int i = 0; i <= this.N; i++) {
            x[i] = this.leftSide + i * this.h;
        }
        for (int i = 0; i <= this.N; i++) {

            f_i[i] = f(x[i]);


            for (int j = 0; j <= this.N; j++) {

                sysMatrix[i][j] -= this.lambda * h * K(x[i], x[j]);
            }

            sysMatrix[i][i] += 1;

        }
        List<Pair<Double, Double>> y = new ArrayList<>(this.N);

        double[] solution = Utils.theGauss(sysMatrix, f_i);

        for (int i = 0; i < solution.length; i++) {
            y.add(new Pair<>(x[i], solution[i]));
        }

        return y;

    }
}
