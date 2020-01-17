package com.oceanpeak.ddddemo.Logic;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SnackMachineTest {

	@Test
	public void return_money_empties_money_in_transaction() {

		SnackMachine snackMachine = new SnackMachine();
		snackMachine.insertMoney(Money.DOLLAR);
		snackMachine.returnMoney();

		assertEquals(snackMachine.getMoneyInside().getAmount(), new BigDecimal(0).round(Money.ROUND));

	}
	
	@Test

	public void inserted_money_goes_to_money_in_transaction() {

		SnackMachine snackMachine = new SnackMachine();

		snackMachine.insertMoney(Money.DOLLAR);
		snackMachine.insertMoney(Money.CENT);

		assertEquals(snackMachine.getMoneyInTransaction().round(Money.ROUND), new BigDecimal(1.01).round(Money.ROUND)) ;

	}
	
	/*
	@Test

	public void money_in_transaction_goes_to_money_inside_after_purchase() {

		SnackMachine snackMachine = new SnackMachine();

		snackMachine.insertMoney(Money.DOLLAR);
		snackMachine.insertMoney(Money.DOLLAR);

		snackMachine.buySnack(1);

		assertEquals(snackMachine.getMoneyInTransaction().round(Money.ROUND), 
				new BigDecimal(0).round(Money.ROUND));

		assertEquals(snackMachine.getMoneyInside().getAmount().round(Money.ROUND), 
				new BigDecimal(2).round(Money.ROUND));

	}
	*/
	@Test
   public void cannot_insert_more_than_one_coin_or_note_at_a_time() {
          
		Assertions.assertThrows(IllegalStateException.class, () -> {
			failedFunction();
		  });
		 

    }
	
	
	@Test

    public void buySnack_trades_inserted_money_for_a_snack() {

            SnackMachine snackMachine = new SnackMachine();
            snackMachine.loadSnacks(1, new SnackPile(Snack.Chocolate, 10, new BigDecimal(1)));
            
            snackMachine.insertMoney(Money.DOLLAR);

            snackMachine.buySnack(1);

            assertEquals(snackMachine.getMoneyInTransaction(), new BigDecimal(0));        
			assertEquals(snackMachine.getMoneyInside().getAmount(), new BigDecimal(1));

            Slot slot = snackMachine.getSlots().stream().filter(x -> x.getPosition() == 1).findAny().orElse(null);
            assertEquals(slot.getSnackPile().getQuantity(), 9);

    }
	
	private void failedFunction() {
		 SnackMachine snackMachine = new SnackMachine();
         Money twoCent = Money.add(Money.CENT, Money.CENT);
         snackMachine.insertMoney(twoCent);
	}
	
	@Test

	public void snack_machine_returns_money_with_highest_denomination_first() {

		SnackMachine snackMachine = new SnackMachine();

		snackMachine.loadMoney(Money.DOLLAR);

		snackMachine.insertMoney(Money.QUARTER);

		snackMachine.insertMoney(Money.QUARTER);

		snackMachine.insertMoney(Money.QUARTER);

		snackMachine.insertMoney(Money.QUARTER);

		snackMachine.returnMoney();

		assertEquals(snackMachine.getMoneyInside().getQuarterCount(), 4);
		assertEquals(snackMachine.getMoneyInside().getOneDollarCount(), 0);

	}
	
	@Test

	public void after_purchase_change_is_returned() {

		SnackMachine snackMachine = new SnackMachine();
		snackMachine.loadSnacks(1, new SnackPile(Snack.Chocolate, 1, new BigDecimal(0.5)));
		snackMachine.loadMoney(new Money(0, 10, 0, 0, 0, 0));
		snackMachine.insertMoney(Money.DOLLAR);
		snackMachine.buySnack(1);
		
		assertEquals(snackMachine.getMoneyInside().getAmount(), new BigDecimal(1.5));
        assertEquals(snackMachine.getMoneyInTransaction(), new BigDecimal(0));

    }

	@Test
	public void cannot_buy_snack_if_not_enough_change() {
		
		Assertions.assertThrows( IllegalStateException.class, () -> {
			SnackMachine snackMachine = new SnackMachine();
			snackMachine.loadSnacks(1, new SnackPile(Snack.Chocolate, 1, new BigDecimal(0.5)));
			snackMachine.insertMoney(Money.DOLLAR);
			snackMachine.buySnack(1);
		  });
		
		

	}

}
