package com.oceanpeak.ddddemo.Logic;

import java.math.BigDecimal;

public class Slot extends Entity{

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
    
    public SlotDto convertToSlotDto() {
    	SlotDto slotDto = new SlotDto();
    	slotDto.setId(id);
    	slotDto.setPosition(position);
    	slotDto.setPrice(snackPile.getPrice());
    	slotDto.setQuantity(snackPile.getQuantity());
    	slotDto.setSnackDto(snackPile.getSnack().convertToSnackDto());
    	return slotDto;
   }

}
