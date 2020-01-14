package com.oceanpeak.ddddemo.Logic;

import java.math.BigDecimal;

public class Slot {

	private Snack snack;
	private int quantity;
	private BigDecimal price;
	private SnackMachine snackMachine;
	private int position;

	public Slot() {
	}

	public Slot(SnackMachine snackMachine, int position, Snack snack, int quantity, BigDecimal price) {

		this.snackMachine = snackMachine;
		this.position = position;
		this.snack = snack;
		this.quantity = quantity;
		this.price = price;
	}

	public Snack getSnack() {
		return snack;
	}

	public void setSnack(Snack snack) {
		this.snack = snack;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
