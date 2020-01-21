package com.oceanpeak.ddddemo.Logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.oceanpeak.ddddemo.Logic.atm.Atm;
import com.oceanpeak.ddddemo.Logic.sharedkernel.Money;

@SpringBootTest
public class AtmTest {

	@Test
    public void take_money_exchanges_money_with_commission() {

            Atm atm = new Atm();
            atm.loadMoney(Money.DOLLAR);
            atm.takeMoney(new BigDecimal(1));

            assertEquals(atm.getMoneyInside().getAmount().doubleValue(), 0, 0);

            assertEquals(atm.getMoneyCharged().doubleValue(), 1.01, 0.001);

    }
	
	@Test

    public void commission_is_at_least_one_cent(){
            Atm atm = new Atm();
            atm.loadMoney(Money.CENT);
            atm.takeMoney((new BigDecimal(0.01)).setScale(Money.ROUND, BigDecimal.ROUND_HALF_UP));
            assertEquals(atm.getMoneyCharged().doubleValue(), 0.02, 0.001);

	}
	
	@Test
    public void commission_is_rounded_up_to_the_next_cent(){
		Atm atm = new Atm();
		atm.loadMoney(Money.DOLLAR.add(Money.TENCENT));
		atm.takeMoney(new BigDecimal(1.1).setScale(Money.ROUND, BigDecimal.ROUND_HALF_UP));
		assertEquals(atm.getMoneyCharged().doubleValue(), 1.12, 0.01);

}
}
