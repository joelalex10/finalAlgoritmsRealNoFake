package Algoritmos;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.Arrays.stream;

public class Voggel {

    int[] destino;
    int[] origen;
    int[][] costos;

    int nOrigen;
    int nDestino;

    boolean[] rowDone;
    boolean[] colDone;
    int[][] result;

    ExecutorService es = Executors.newFixedThreadPool(2);

    public Voggel(int origen[], int destino[], int costos[][]){
        this.origen = origen;
        this.destino = destino;
        this.costos = costos;

        nOrigen = origen.length;
        nDestino = destino.length;

        rowDone = new boolean[origen.length];
        colDone = new boolean[destino.length];
        result = new int[origen.length][destino.length];

    }




    public void ejecutar() throws Exception {
        int supplyLeft = stream(origen).sum();
        int totalCost = 0;

        while (supplyLeft > 0) {
            int[] cell = nextCell();
            int r = cell[0];
            int c = cell[1];

            int quantity = Math.min(destino[c], origen[r]);
            destino[c] -= quantity;
            if (destino[c] == 0)
                colDone[c] = true;

            origen[r] -= quantity;
            if (origen[r] == 0)
                rowDone[r] = true;

            result[r][c] = quantity;
            supplyLeft -= quantity;

            totalCost += quantity * costos[r][c];
        }

        stream(result).forEach(a -> System.out.println(Arrays.toString(a)));
        System.out.println("Total cost: " + totalCost);

        es.shutdown();
    }

    int[] nextCell() throws Exception {
        Future<int[]> f1 = es.submit(() -> maxPenalty(nOrigen, nDestino, true));
        Future<int[]> f2 = es.submit(() -> maxPenalty(nDestino, nOrigen, false));

        int[] res1 = f1.get();
        int[] res2 = f2.get();

        if (res1[3] == res2[3])
            return res1[2] < res2[2] ? res1 : res2;

        return (res1[3] > res2[3]) ? res2 : res1;
    }

    int[] diff(int j, int len, boolean isRow) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int minP = -1;
        for (int i = 0; i < len; i++) {
            if (isRow ? colDone[i] : rowDone[i])
                continue;
            int c = isRow ? costos[j][i] : costos[i][j];
            if (c < min1) {
                min2 = min1;
                min1 = c;
                minP = i;
            } else if (c < min2)
                min2 = c;
        }
        return new int[]{min2 - min1, min1, minP};
    }
    int[] maxPenalty(int len1, int len2, boolean isRow) {
        int md = Integer.MIN_VALUE;
        int pc = -1, pm = -1, mc = -1;
        for (int i = 0; i < len1; i++) {
            if (isRow ? rowDone[i] : colDone[i])
                continue;
            int[] res = diff(i, len2, isRow);
            if (res[0] > md) {
                md = res[0];  // max diff
                pm = i;       // pos of max diff
                mc = res[1];  // min cost
                pc = res[2];  // pos of min cost
            }
        }
        return isRow ? new int[]{pm, pc, mc, md} : new int[]{pc, pm, mc, md};
    }



}
