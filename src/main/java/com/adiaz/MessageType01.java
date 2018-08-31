package com.adiaz;

public class MessageType01 extends MessageAbstract implements MessageInterface {

    @Override
    public void process(SalesStock salesStock) {
        salesStock.addSale(getName(), getPrice(), 1);
    }
}