import java.util.concurrent.Semaphore;

public class Trabalho4 {
    public static void main(String[] args) {
        int[][] matriz = { { 2, 0, 0 },
                           { 1, 4, 0 },
                           { 1, 1, 1 } };

        int b[] = { 2, -3, 1 };
        int x[] = {0, 0, 0};

        Semaphore semaforoS = new Semaphore(1);
        Semaphore semaforoX = new Semaphore(1);

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.printf(String.valueOf(matriz[i][j] + " "));
            }
            System.out.println();
        }

        System.out.print("B = ");
        for (int i = 0; i < b.length; i++)
            System.out.print(b[i] + " ");
        System.out.println();

        try {
            //semaforoX.release();
            semaforoS.acquire();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadX threadX = new ThreadX(semaforoX, semaforoS, x, b, matriz);
        ThreadSomatorio threadS = new ThreadSomatorio(semaforoS, semaforoX, matriz, x, threadX);
        threadX.start();
        threadS.start();
    }
}