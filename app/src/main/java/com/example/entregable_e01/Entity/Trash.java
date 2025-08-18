package com.example.entregable_e01.Entity;

import java.time.LocalDateTime;

public class Trash {
    private int id;
    private String workerName;
    private String wasteType;
    private double quantity;
    private LocalDateTime fecha;

    public Trash() {
    }

    public Trash(int id, String workerName, String wasteType, double quantity, LocalDateTime fecha) {
        this.id = id;
        this.workerName = workerName;
        this.wasteType = wasteType;
        this.quantity = quantity;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWasteType() {
        return wasteType;
    }

    public void setWasteType(String wasteType) {
        this.wasteType = wasteType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
