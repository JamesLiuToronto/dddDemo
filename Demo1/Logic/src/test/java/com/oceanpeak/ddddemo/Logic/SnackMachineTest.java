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

		assertEquals(snackMachine.getMoneyInTransaction().getAmount(), new BigDecimal(1.01).round(Money.ROUND));

	}
	
	@Test

	public void money_in_transaction_goes_to_money_inside_after_purchase() {

		SnackMachine snackMachine = new SnackMachine();

		snackMachine.insertMoney(Money.DOLLAR);

		snackMachine.insertMoney(Money.DOLLAR);

		snackMachine.buySnack();

		assertEquals(snackMachine.getMoneyInTransaction().getAmount(), new BigDecimal(0).round(Money.ROUND));

		assertEquals(snackMachine.getMoneyInside().getAmount(), new BigDecimal(2).round(Money.ROUND));

	}
	
	@Test
   public void cannot_insert_more_than_one_coin_or_note_at_a_time() {
          
		Assertions.assertThrows(IllegalStateException.class, () -> {
			failedFunction();
		  });
		 

    }
	
	private void failedFunction() {
		 SnackMachine snackMachine = new SnackMachine();
         Money twoCent = Money.add(Money.CENT, Money.CENT);
         snackMachine.insertMoney(twoCent);
	}

}
