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
	
	

}
