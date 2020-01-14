package com.oceanpeak.ddddemo.Logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnackMachine extends AggregateRoot  {

	private Money moneyInside;
	private Money moneyInTransaction;
	
	public SnackMachine() {
		moneyInside = Money.NONE;
		moneyInTransaction = Money.NONE;
		slots = new ArrayList<>();
        slots.add(new Slot(this, 1, null, 0, new BigDecimal(1)));
        slots.add(new Slot(this, 2, null, 0, new BigDecimal(1)));
        slots.add(new Slot(this, 3, null, 0, new BigDecimal(1)));
	}

		
	public void insertMoney(Money money) {

        Money[] coinsAndNotes = { Money.CENT, Money.TENCENT, Money.QUARTER, Money.DOLLAR, 
        		Money.FIVEDOLLAR, Money.TWENTYDOLLAR};

            if (!Arrays.asList(coinsAndNotes).contains(money))
                    throw new IllegalStateException();

            

        moneyInTransaction = Money.add(moneyInTransaction, money);

	}
	
	private List<Slot> slots;

    public List<Slot> getSlots() {
        	return slots;
    }

    public void setSlots(List<Slot> slots) {
            this.slots = slots;
    }

    public void loadSnacks(int position, Snack snack, int quantity, BigDecimal price) {
            Slot slot = slots.stream().filter(x -> x.getPosition() == position).findAny().orElse(null);
            if(slot != null) {
            	slot.setSnack(snack);
                slot.setQuantity(quantity);
                slot.setPrice(price);
            }
    }

	public void returnMoney() {
		moneyInTransaction = Money.NONE;

	}

		
	public void buySnack(int position) {

        Slot slot = slots.stream().filter(x -> x.getPosition() == position).findAny().orElse(null);

        slot.setQuantity(slot.getQuantity()-1);

        moneyInside = Money.add(moneyInside, moneyInTransaction);

		moneyInTransaction = Money.NONE;

	}

	public Money getMoneyInside() {
		return moneyInside;
	}

	public Money getMoneyInTransaction() {
		return moneyInTransaction;
	}
	
	
	
	public void setMoneyInside(Money moneyInside) {
		this.moneyInside = moneyInside;
	}


	public void setMoneyInTransaction(Money moneyInTransaction) {
		this.moneyInTransaction = moneyInTransaction;
	}


	public SnackMachineDto convertToSnackMachineDto() {

        SnackMachineDto snackMachineDto = new SnackMachineDto();

        snackMachineDto.setId(id);
        snackMachineDto.setMoneyInTransaction(moneyInTransaction.getAmount());        
        snackMachineDto.setOneCentCount(moneyInside.getOneCentCount());        
        snackMachineDto.setTenCentCount(moneyInside.getTenCentCount());        
        snackMachineDto.setQuarterCount(moneyInside.getQuarterCount());                
        snackMachineDto.setOneDollarCount(moneyInside.getOneDollarCount());        
        snackMachineDto.setFiveDollarCount(moneyInside.getFiveDollarCount());
        snackMachineDto.setTwentyDollarCount(moneyInside.getTwentyDollarCount());

        return snackMachineDto;

}

}
