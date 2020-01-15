package com.oceanpeak.ddddemo.Logic;

import java.math.BigDecimal;

public class Slot {

	private SnackPile snackPile;
    private SnackMachine snackMachine;
    private int position;

    public Slot() {
    }

    public Slot(SnackMachine snackMachine, int position) {

            this.snackMachine = snackMachine;
            this.position = position;
            this.snackPile = new SnackPile(null, 0, new BigDecimal(0));

    }

    public SnackPile getSnackPile() {
            return snackPile;
    }

    public void setSnackPile(SnackPile snackPile) {
            this.snackPile = snackPile;
    }

    public SnackMachine getSnackMachine() {
            return snackMachine;
    }

    public void setSnackMachine(SnackMachine snackMachine) {
            this.snackMachine = snackMachine;
    }

    public int getPosition() {
            return position;
    }

    public void setPosition(int position) {
            this.position = position;
    }

}
