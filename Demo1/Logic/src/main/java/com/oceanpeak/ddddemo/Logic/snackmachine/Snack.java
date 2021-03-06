package com.oceanpeak.ddddemo.Logic.snackmachine;

import com.oceanpeak.ddddemo.Logic.common.AggregateRoot;

public class Snack extends AggregateRoot {
	public static Snack None = new Snack(0, "None");
	public static Snack Chocolate = new Snack(1, "Chocolate");
	public static Snack Soda = new Snack(2, "Soda");
	public static Snack Gum = new Snack(3, "Gum");
	private String name;

	private Snack(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public SnackDto convertToSnackDto() {
		SnackDto snackDto = new SnackDto();
		snackDto.setId(id);
		snackDto.setName(name);
		return snackDto;
	}
}