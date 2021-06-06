package Algoritmos;

public  class Shipment {
    double costPerUnit;
    int r, c;
    int quantity;

    public Shipment(int q, double cpu, int r, int c) {
        quantity = q;
        costPerUnit = cpu;
        this.r = r;
        this.c = c;
    }
}
