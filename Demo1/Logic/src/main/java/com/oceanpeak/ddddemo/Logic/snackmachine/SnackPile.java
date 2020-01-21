package com.oceanpeak.ddddemo.Logic.snackmachine;

import java.math.BigDecimal;

import com.oceanpeak.ddddemo.Logic.common.ValueObject;

public class SnackPile extends ValueObject<SnackPile> {

    private Snack snack;

    private int quantity;

    private BigDecimal price;

    private SnackPile() {

    }
    
    public static SnackPile Empty = new SnackPile(Snack.None, 0, new BigDecimal(0));

    public SnackPile(Snack snack, int quantity, BigDecimal price) {

    	 if (quantity < 0)
             throw new IllegalStateException();
    	 if (price.compareTo(BigDecimal.ZERO) == -1)
             throw new IllegalStateException();
            this.snack = snack;
            this.quantity = quantity;
            this.price = price;
    }

    public SnackPile subtractOne() {
    	return new SnackPile(snack, getQuantity() - 1, getPrice());
    }

    @Override

    protected boolean equalsCore(SnackPile other) {

            return this.snack == other.snack && this.getQuantity() == other.getQuantity()
                    && this.getPrice() == other.getPrice();

    }

    @Override

    protected int getHashCodeCore() {

            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + price.hashCode();
            result = prime * result + quantity;
            result = prime * result + ((snack == null) ? 0 : snack.hashCode());

            return result;

    }

    public int getQuantity() {
            return quantity;
    }

    public BigDecimal getPrice() {
            return price;
    }

    public Snack getSnack() {
            return snack;
    }
}
