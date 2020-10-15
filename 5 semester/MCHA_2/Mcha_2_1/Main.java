import javafx.util.Pair;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        FredholmEquation fredholmEquation = new FredholmEquation(0, 1, 10);
        try {
            System.out.println("Уравнение Фредгольма: ");
            List<Pair<Double, Double>> fredholmsolution = fredholmEquation.solveRightRectangles();
            for (Pair<Double, Double> aFredholmsolution : fredholmsolution) {
                System.out.println(aFredholmsolution.getKey() + ": " + aFredholmsolution.getValue());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Уравнение Волтерра: ");
        VolterraEquation volterraEquation = new VolterraEquation(0, 1, 10);
        List<Pair<Double, Double>> voltrerraResult = volterraEquation.solve();
        for (Pair<Double, Double> aVoltrerraResult : voltrerraResult) {
            System.out.println(aVoltrerraResult.getKey() + ": " + aVoltrerraResult.getValue());
        }

        FredholmIterations FI = new FredholmIterations(0, 1, 10);
        List<Pair<Double, Double>> FIResult = FI.solveRightRectangles();
        System.out.println("Уравнение Фредгольма МПИ: ");
        for (Pair<Double, Double> aFIResult : FIResult) {
            System.out.println(aFIResult.getKey() + ": " + aFIResult.getValue());
        }
        VolterraIterations VI = new VolterraIterations(0, 1, 10);
        List<Pair<Double, Double>> VIResult = VI.solveRightTrapeeze();
        System.out.println("Уравнение Вольтерра МПИ: ");
        for (Pair<Double, Double> aVIResult : VIResult) {
            System.out.println(aVIResult.getKey() + ": " + aVIResult.getValue());
        }
    }
}
