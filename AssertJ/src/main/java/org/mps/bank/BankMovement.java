package org.mps.bank;

public class BankMovement {
    public static enum MOVEMENT_TYPE {WITHDRAW, DEPOSIT};
    private MOVEMENT_TYPE movement_type;
    public MOVEMENT_TYPE getMovement_type() {
        return movement_type;
    }
    public void setMovement_type(MOVEMENT_TYPE movement_type) {
        this.movement_type = movement_type;
    }
    private int amount;
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public BankMovement(MOVEMENT_TYPE movement_type, int amount){
        this.movement_type = movement_type;
        this.amount = amount;
    }
    
}
