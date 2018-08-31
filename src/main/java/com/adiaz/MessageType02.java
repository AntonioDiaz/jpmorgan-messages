package com.adiaz;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageType02 extends MessageAbstract implements MessageInterface {

    @SerializedName("occurrences")
    @Expose
    private Integer occurrences;


    public Integer getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(Integer occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    public void process(SalesStock salesStock) {
        salesStock.addSale(getName(), getPrice(), occurrences);
    }
}
