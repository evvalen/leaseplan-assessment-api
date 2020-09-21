package com.leaseplan.assessment.pojo.order;

public class Order {

    private String petId;

    private int quantity;

    private String status;

    private boolean complete;

    public Order(String petId, int quantity, String status, boolean complete) {
        this.petId = petId;
        this.quantity = quantity;
        this.status = status;
        this.complete = complete;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
