import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class FredholmIterations {
    private double leftSide;

    private double rightSide;

    private int N;

    private double h;

    private double lambda = 0.1;


    public FredholmIterations(double leftSide, double rightSide, int n) {
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

    public List<Pair<Double, Double>> solveRightRectangles() {

        double[] x = new double[this.N + 1];
        double[] yPrevious = new double[this.N + 1];
        double[] yCurrent = new double[this.N + 1];
        // вычисление Xi
        for (int i = 0; i <= this.N; i++) {
            x[i] = this.leftSide + i * this.h;
            yCurrent[i] = f(x[i]);
        }
        int iterationsCount = 0;
        do {

            System.arraycopy(yCurrent, 0, yPrevious, 0, this.N + 1);

            for (int i = 0; i <= this.N; i++) {

                double sum = 0;

                for (int j = 0; j <= this.N; j++) {

                    sum += this.lambda * h * K(x[i], x[j]) * yPrevious[j];
                }
                yCurrent[i] = sum + f(x[i]);
            }

            iterationsCount++;

        } while (Utils.norm(yCurrent, yPrevious) > 0.001);

        System.out.println(iterationsCount);

        List<Pair<Double, Double>> result = new ArrayList<>(this.N);
        for (int i = 0; i < yCurrent.length; i++) {
            result.add(new Pair<>(x[i], yCurrent[i]));
        }
        return result;
    }

}

