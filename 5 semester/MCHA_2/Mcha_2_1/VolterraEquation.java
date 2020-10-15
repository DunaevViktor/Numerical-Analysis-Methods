import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class VolterraEquation {

    private double leftSide;

    private double rightSide;

    private int N;

    private double h;

    private double lambda = 0.1;

    public VolterraEquation(double leftSide, double rightSide, int n) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.N = n;
        this.h = (this.rightSide - this.leftSide) / this.N;

    }

    private double getA(int i, int limit) {
        if (i == 0 || i == limit) {
            return this.h / 2;
        } else {
            return this.h;
        }
    }

    private double K(double x_i, double s) {

        return Math.exp(x_i + s);
    }

    private double f(double x_i) {

        return 1 + Math.exp(2 * x_i) - Math.exp(x_i);
    }

    public List<Pair<Double, Double>> solve() {

        List<Pair<Double, Double>> y = new ArrayList<>(this.N + 1);
        double[] x = new double[this.N + 1];

        for (int i = 0; i <= this.N; i++) {
            x[i] = this.leftSide + i * this.h;
        }

        for (int i = 0; i <= this.N; i++) {

            double y_i;

            double first = Math.pow((1 - getA(i, i) * K(x[i], x[i]) * this.lambda), -1);

            double second = 0;

            for (int j = 0; j < i; j++) {

                second += this.lambda * getA(j, i) * K(x[i], x[j]) * y.get(j).getValue();
            }

            second = f(x[i]) + second;

            y_i = first * second;

            y.add(new Pair<>(x[i], y_i));
        }

        return y;

    }
}
