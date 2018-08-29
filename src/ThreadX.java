import java.util.concurrent.Semaphore;

public class ThreadX extends Thread {
    private Semaphore semaforoX;
    private Semaphore semaforoS;
    private int soma, n;
    private int[] x;
    private int[] b;
    private int[][] matriz;

    public ThreadX(Semaphore semaforoX, Semaphore semaforoS, int[] x, int[] b, int[][] matriz) {
        this.semaforoX = semaforoX;
        this.semaforoS = semaforoS;
        this.x = x;
        this.b = b;
        this.matriz = matriz;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < x.length; i++) {
                semaforoX.acquire();
                semaforoS.release(2);
                n = 0;

                while(n == 0) {
                    if (semaforoX.tryAcquire()) {
                        x[i] = (b[i] - soma) / matriz[i][i];
                        n = 1;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("X = ");
        for (int i = 0;i < b.length;i++) {
            System.out.print(x[i] + " ");
        }
    }

    public void setSoma(int s) {
        this.soma = s;
    }
}