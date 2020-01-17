package com.oceanpeak.ddddemo.Logic;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Entity;

@Entity
public class SlotDto {
	@Id
	@GeneratedValue
	private long id;
	private int quantity;
	private BigDecimal price;
	private int position;
	@OneToOne(cascade = CascadeType.ALL)
	private SnackDto snackDto;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public SnackDto getSnackDto() {
		return snackDto;
	}

	public void setSnackDto(SnackDto snackDto) {
		this.snackDto = snackDto;
	}

	public Slot convertToSlot() {
		Slot slot = new Slot();
		slot.setId(id);
		slot.setPosition(position);
		slot.setSnackPile(new SnackPile(snackDto.convertToSnack(), quantity, price));
		return slot;
	}

	

}
