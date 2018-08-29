import java.util.concurrent.Semaphore;

public class ThreadSomatorio extends Thread {
    private Semaphore semaforoS;
    private Semaphore semaforoX;
    private int[][] matriz;
    private int soma;
    private ThreadX threadX;
    private int[] x;
    private int i;

    public ThreadSomatorio(Semaphore semaforoS, Semaphore semaforoX, int[][] matriz, int[] x, ThreadX threadX) {
        this.semaforoS = semaforoS;
        this.semaforoX = semaforoX;
        this.matriz = matriz;
        this.soma = 0;
        this.threadX = threadX;
        this.x = x;
        this.i = 0;
    }

    @Override
    public void run() {
        while(i < x.length) {
            try {
                if (semaforoS.tryAcquire()) {
                    semaforoS.acquire();

                    soma = 0;
                    for (int j = 0; j < i; j++) {
                        soma += matriz[i][j] * x[j];
                    }
                    threadX.setSoma(soma);
                    i += 1;
                    semaforoX.release(2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}