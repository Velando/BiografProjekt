package com.company;

/**
 * Created by Sebastian on 11-12-2014.
 */
public class Money {
    private int amount;
    private String currency;

    public Money(int amount, String currency){
        if (currency == null)
            throw new IllegalArgumentException("Requiare a valid currency!");
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Money mo = (Money)o;
        if (this.amount == mo.amount && this.currency.equals(mo.currency)) {
            return true;
        }
        return false;
    }
}
