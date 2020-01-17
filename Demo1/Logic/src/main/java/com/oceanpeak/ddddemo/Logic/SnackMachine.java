package com.oceanpeak.ddddemo.Logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnackMachine extends AggregateRoot  {

	private Money moneyInside;
	private BigDecimal moneyInTransaction;
	private List<Slot> slots;
	
	public SnackMachine() {
		moneyInside = Money.NONE;
		moneyInTransaction = new BigDecimal(0);
		slots = new ArrayList<>();
		slots.add(new Slot(this, 1));
        slots.add(new Slot(this, 2));
        slots.add(new Slot(this, 3));
	}

	
	public void loadMoney(Money money) {
		moneyInside = moneyInside.add(money);
	}
		
	public void insertMoney(Money money) {
		
		
		Money[] coinsAndNotes = { Money.CENT, Money.TENCENT, Money.QUARTER, Money.DOLLAR, Money.FIVEDOLLAR,
				Money.TWENTYDOLLAR };

		if (!Arrays.asList(coinsAndNotes).contains(money))
			throw new IllegalStateException();

		moneyInTransaction = moneyInTransaction.add(money.getAmount());
		moneyInside = moneyInside.add(money);
	}
	
	
    public List<Slot> getSlots() {
        	return slots;
    }

    public void setSlots(List<Slot> slots) {
            this.slots = slots;
    }

    public void loadSnacks(int position, SnackPile snackPile) {
    	Slot slot = slots.stream().filter(x -> x.getPosition() == position).findAny().orElse(null);
        if(slot != null) {
                slot.setSnackPile(snackPile);
        }
    }

	public void returnMoney() {
		Money moneyToReturn = moneyInside.allocate(moneyInTransaction);
        moneyInside = moneyInside.substract(moneyToReturn);
		moneyInTransaction = new BigDecimal(0);

	}

		
	public void buySnack(int position) {

		 Slot slot = getSlot(position);
         if(slot.getSnackPile().getPrice().compareTo(moneyInTransaction) == 1) {
                 throw new IllegalStateException();
         }

         slot.setSnackPile(slot.getSnackPile().subtractOne());
         Money change = moneyInside.allocate(moneyInTransaction.subtract(slot.getSnackPile().getPrice()));
         
         if(change.getAmount().compareTo(moneyInTransaction.subtract(slot.getSnackPile().getPrice())) == -1 ) {
             throw new IllegalStateException();
     }
         
         moneyInside = moneyInside.substract(change);
         moneyInTransaction = new BigDecimal(0);

	}

	public Money getMoneyInside() {
		return moneyInside;
	}

	public BigDecimal getMoneyInTransaction() {
		return moneyInTransaction;
	}
	
	
	
	public void setMoneyInside(Money moneyInside) {
		this.moneyInside = moneyInside;
	}


	public void setMoneyInTransaction(BigDecimal moneyInTransaction) {
		this.moneyInTransaction = moneyInTransaction;
	}


	public SnackMachineDto convertToSnackMachineDto() {

        SnackMachineDto snackMachineDto = new SnackMachineDto();

        snackMachineDto.setId(id);
        snackMachineDto.setMoneyInTransaction(moneyInTransaction);  
        
        List<SlotDto> slotDtoList = new ArrayList<>();
        for(Slot slot : slots) 
        	slotDtoList.add(slot.convertToSlotDto());
        snackMachineDto.setSlotDtoList(slotDtoList);  
        
        snackMachineDto.setOneCentCount(moneyInside.getOneCentCount());        
        snackMachineDto.setTenCentCount(moneyInside.getTenCentCount());        
        snackMachineDto.setQuarterCount(moneyInside.getQuarterCount());                
        snackMachineDto.setOneDollarCount(moneyInside.getOneDollarCount());        
        snackMachineDto.setFiveDollarCount(moneyInside.getFiveDollarCount());
        snackMachineDto.setTwentyDollarCount(moneyInside.getTwentyDollarCount());

        return snackMachineDto;

	}
	
	
	public Slot getSlot(int position) {
		 return slots.stream().filter(x -> x.getPosition() == position).findAny().orElse(null);

    }

    public SnackPile getSnackPile(int position) {

            return getSlot(position).getSnackPile();

    }

    

}
