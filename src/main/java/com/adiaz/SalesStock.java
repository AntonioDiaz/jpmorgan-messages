package com.adiaz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesStock {

    private Map<String, List<Sale>> stock = new HashMap<>();


    public void addSale(String type, Double salePrice, Integer saleAmount){
        if (!stock.containsKey(type)) {
            List<Sale> list = new ArrayList<>();
            list.add(new Sale(saleAmount, salePrice));
            stock.put(type, list);
        } else {
            stock.get(type).add(new Sale(saleAmount, salePrice));
        }
    }


    public void applyAdjustment(Adjustment adjustment, String type, Double adjustmentAmount ) {
        for (Sale sale : stock.get(type)) {
            switch (adjustment) {
                case ADD:
                    sale.price += adjustmentAmount;
                    break;
                case SUBTRACT:
                    sale.price -= adjustmentAmount;
                    break;
                case MULTIPLY:
                    sale.price *= adjustmentAmount;
                    break;
            }
        }
    }

    public String generateLog() {
        String log = "";
        for (String s : stock.keySet()) {
            Double totalSaleForThisInc = 0D;
            for (Sale sale : stock.get(s)) {
                totalSaleForThisInc += sale.amount * sale.price;
            }
            log += s + " - " + totalSaleForThisInc;

        }
        return log;
    }

    public class Sale {
        private Integer amount;
        private Double price;

        public Sale(Integer amount, Double price) {
            this.amount = amount;
            this.price = price;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

    public Map<String, List<Sale>> getStock() {
        return stock;
    }

    public enum Adjustment {
        ADD, SUBTRACT, MULTIPLY
    }

}
