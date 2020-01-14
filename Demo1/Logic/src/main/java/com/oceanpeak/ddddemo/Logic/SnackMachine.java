package com.oceanpeak.ddddemo.Logic;

import java.util.Arrays;

public class SnackMachine extends Entity  {

	private Money moneyInside;
	private Money moneyInTransaction;
	
	public SnackMachine() {
		moneyInside = Money.NONE;
		moneyInTransaction = Money.NONE;
	}

		
	public void insertMoney(Money money) {

        Money[] coinsAndNotes = { Money.CENT, Money.TENCENT, Money.QUARTER, Money.DOLLAR, 
        		Money.FIVEDOLLAR, Money.TWENTYDOLLAR};

            if (!Arrays.asList(coinsAndNotes).contains(money))
                    throw new IllegalStateException();

            

        moneyInTransaction = Money.add(moneyInTransaction, money);

}

	public void returnMoney() {
		moneyInTransaction = Money.NONE;

	}

	public void buySnack() {

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
