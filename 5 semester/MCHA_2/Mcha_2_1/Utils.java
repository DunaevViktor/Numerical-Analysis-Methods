// Personal code style
public final class Utils {

    public static double[] theGauss(double matrixA[][], double vectorB[]) throws Exception {

        if (matrixA.length != matrixA[0].length || matrixA.length != vectorB.length) {

            class InvalidSystemSizeException extends Exception {
                public InvalidSystemSizeException(String error) {
                    super(error);
                }
            }
            throw new InvalidSystemSizeException("Wrong matrix size");
        }

        for (int i = 0; i < matrixA.length; i++) {

            double leadingElement = matrixA[i][i];

            for (int j = 0; j < matrixA.length; j++) {

                matrixA[i][j] /= leadingElement;

            }

            vectorB[i] /= leadingElement;

            for (int k = 0; k < matrixA.length; k++) {

                if (k != i) {

                    double pseudoLeadingElement = matrixA[k][i];

                    for (int z = i; z < matrixA.length; z++) {

                        matrixA[k][z] += -pseudoLeadingElement * matrixA[i][z];

                    }
                    vectorB[k] += -pseudoLeadingElement * vectorB[i];
                }

            }

        }

        return vectorB;
    }

    public static double norm(double[] a, double[] b) {
        double norm = Math.abs(b[0] - a[0]);
        for (int i = 1; i < a.length; i++) {
            double vecNorm = Math.abs(a[i] - b[i]);
            norm = norm > vecNorm ? norm : vecNorm;
        }
        return norm;

    }

}
