package Algoritmos;

import java.util.*;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;

public class Kramer {
    private static int[] resultDemand;
    private static int[] resultSuply;
    private static int[][] resultCosts;
    private static Shipment[][] matrix;
    private int totalCosts;
    int[] demand;
    int[] supply;
    int[][] costs;


    public Kramer(int[] demand, int[] supply, int[][] costs){
        this.demand = demand;
        this.supply = supply;
        this.costs = costs;

    }
    public void init() {


        List<Integer> src = new ArrayList<>();
        List<Integer> dst = new ArrayList<>();

        for (int i = 0; i < supply.length; i++)
            src.add(supply[i]);

        for (int i = 0; i < demand.length; i++)
            dst.add(demand[i]);

        int totalSrc = src.stream().mapToInt(i -> i).sum();
        int totalDst = dst.stream().mapToInt(i -> i).sum();
        if (totalSrc > totalDst)
            dst.add(totalSrc - totalDst);
        else if (totalDst > totalSrc)
            src.add(totalDst - totalSrc);

        resultSuply = src.stream().mapToInt(i -> i).toArray();
        resultDemand = dst.stream().mapToInt(i -> i).toArray();

        resultCosts = new int[resultSuply.length][resultDemand.length];
        matrix = new Shipment[resultSuply.length][resultDemand.length];

        for (int i = 0; i < supply.length; i++)
            for (int j = 0; j < demand.length; j++)
                resultCosts[i][j] = costs[i][j];
    }

    public void northWestCornerRule() {

        for (int r = 0, northwest = 0; r < resultSuply.length; r++)
            for (int c = northwest; c < resultDemand.length; c++) {

                int quantity = Math.min(resultSuply[r], resultDemand[c]);
                if (quantity > 0) {
                    matrix[r][c] = new Shipment(quantity, resultCosts[r][c], r, c);

                    resultSuply[r] -= quantity;
                    resultDemand[c] -= quantity;

                    if (resultSuply[r] == 0) {
                        northwest = c;
                        break;
                    }
                }
            }
    }

    public void minimizar() {
        double maxReduction = 0;
        Shipment[] move = null;
        Shipment leaving = null;

        fixDegenerateCase();

        for (int r = 0; r < resultSuply.length; r++) {
            for (int c = 0; c < resultDemand.length; c++) {

                if (matrix[r][c] != null)
                    continue;

                Shipment trial = new Shipment(0, resultCosts[r][c], r, c);
                Shipment[] path = getClosedPath(trial);

                double reduction = 0;
                double lowestQuantity = Integer.MAX_VALUE;
                Shipment leavingCandidate = null;

                boolean plus = true;
                for (Shipment s : path) {
                    if (plus) {
                        reduction += s.costPerUnit;
                    } else {
                        reduction -= s.costPerUnit;
                        if (s.quantity < lowestQuantity) {
                            leavingCandidate = s;
                            lowestQuantity = s.quantity;
                        }
                    }
                    plus = !plus;
                }
                // Cambiar a > si quiere el maximo
                // Cambiar a < si quiere el minimo
                if (reduction < maxReduction) {
                    move = path;
                    leaving = leavingCandidate;
                    maxReduction = reduction;
                    maxReduction = reduction;
                }
            }
        }

        if (move != null) {
            double q = leaving.quantity;
            boolean plus = true;
            for (Shipment s : move) {
                s.quantity += plus ? q : -q;
                matrix[s.r][s.c] = s.quantity == 0 ? null : s;
                plus = !plus;
            }
            minimizar();
        }
    }

    public void maximizar() {
        double maxReduction = 0;
        Shipment[] move = null;
        Shipment leaving = null;

        fixDegenerateCase();

        for (int r = 0; r < resultSuply.length; r++) {
            for (int c = 0; c < resultDemand.length; c++) {

                if (matrix[r][c] != null)
                    continue;

                Shipment trial = new Shipment(0, resultCosts[r][c], r, c);
                Shipment[] path = getClosedPath(trial);

                double reduction = 0;
                double lowestQuantity = Integer.MAX_VALUE;
                Shipment leavingCandidate = null;

                boolean plus = true;
                for (Shipment s : path) {
                    if (plus) {
                        reduction += s.costPerUnit;
                    } else {
                        reduction -= s.costPerUnit;
                        if (s.quantity < lowestQuantity) {
                            leavingCandidate = s;
                            lowestQuantity = s.quantity;
                        }
                    }
                    plus = !plus;
                }
                if (reduction > maxReduction) {
                    move = path;
                    leaving = leavingCandidate;
                    maxReduction = reduction;
                }
            }
        }

        if (move != null) {
            double q = leaving.quantity;
            boolean plus = true;
            for (Shipment s : move) {
                s.quantity += plus ? q : -q;
                matrix[s.r][s.c] = s.quantity == 0 ? null : s;
                plus = !plus;
            }
            maximizar();
        }
    }
    static LinkedList<Shipment> matrixToList() {
        return stream(matrix)
                .flatMap(row -> stream(row))
                .filter(s -> s != null)
                .collect(toCollection(LinkedList::new));
    }

    static Shipment[] getClosedPath(Shipment s) {
        LinkedList<Shipment> path = matrixToList();
        path.addFirst(s);
        while (path.removeIf(e -> {
            Shipment[] nbrs = getNeighbors(e, path);
            return nbrs[0] == null || nbrs[1] == null;
        }));
        Shipment[] stones = path.toArray(new Shipment[path.size()]);
        Shipment prev = s;
        for (int i = 0; i < stones.length; i++) {
            stones[i] = prev;
            prev = getNeighbors(prev, path)[i % 2];
        }
        return stones;
    }

    static Shipment[] getNeighbors(Shipment s, LinkedList<Shipment> lst) {
        Shipment[] nbrs = new Shipment[2];
        for (Shipment o : lst) {
            if (o != s) {
                if (o.r == s.r && nbrs[0] == null)
                    nbrs[0] = o;
                else if (o.c == s.c && nbrs[1] == null)
                    nbrs[1] = o;
                if (nbrs[0] != null && nbrs[1] != null)
                    break;
            }
        }
        return nbrs;
    }

    public void fixDegenerateCase() {
        final int eps = Integer.MIN_VALUE;

        if (resultSuply.length + resultDemand.length - 1 != matrixToList().size()) {

            for (int r = 0; r < resultSuply.length; r++)
                for (int c = 0; c < resultDemand.length; c++) {
                    if (matrix[r][c] == null) {
                        Shipment dummy = new Shipment(eps, resultCosts[r][c], r, c);
                        if (getClosedPath(dummy).length == 0) {
                            matrix[r][c] = dummy;
                            return;
                        }
                    }
                }
        }
    }

    public int [][] printResult() {
        int mat[][]=new int[resultSuply.length][resultDemand.length];
        int totalCosts = 0;

        for (int r = 0; r < resultSuply.length; r++) {
            for (int c = 0; c < resultDemand.length; c++) {

                Shipment s = matrix[r][c];
                if (s != null && s.r == r && s.c == c) {
                    System.out.printf("[ %2s ]", (int) s.quantity);
                    mat[r][c] = s.quantity;
                    totalCosts += (s.quantity * s.costPerUnit);
                } else
                    System.out.printf("[  -  ]");
            }
            System.out.println();
        }
        setTotalCosts(totalCosts);
        System.out.printf("%nTotal costs: %s%n%n", totalCosts);
        return mat;
    }

    public int getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(int totalCosts) {
        this.totalCosts = totalCosts;
    }
}