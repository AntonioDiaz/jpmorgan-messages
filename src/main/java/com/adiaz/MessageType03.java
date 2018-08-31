package com.adiaz;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageType03 extends MessageAbstract implements MessageInterface {


    @SerializedName("operation")
    @Expose
    private String operation;


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public void process(SalesStock salesStock) {
        SalesStock.Adjustment adjustment = SalesStock.Adjustment.valueOf(operation);
        salesStock.applyAdjustment(adjustment, getName(), getPrice());
    }
}

