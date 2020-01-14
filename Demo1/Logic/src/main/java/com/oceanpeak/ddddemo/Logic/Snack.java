package com.oceanpeak.ddddemo.Logic;

public class Snack extends AggregateRoot {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Snack() {
	}

	public Snack(String name) {
		this.name = name;
	}

}
