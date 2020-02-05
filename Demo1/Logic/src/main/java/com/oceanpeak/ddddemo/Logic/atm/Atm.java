package com.oceanpeak.ddddemo.Logic.atm;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.oceanpeak.ddddemo.Logic.common.AggregateRoot;
import com.oceanpeak.ddddemo.Logic.sharedkernel.Money;

public class Atm extends AggregateRoot {

	private static BigDecimal commissionRate = (new BigDecimal(0.01)).setScale(Money.ROUND, BigDecimal.ROUND_HALF_UP);
	private Money moneyInside = Money.NONE;
	private BigDecimal moneyCharged = new BigDecimal(0);
	
	/* This is just a note*/
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;

	public void takeMoney(BigDecimal amount) {

		if (canTakeMoney(amount.floatValue()) != "")
			throw new IllegalStateException();
		Money output = moneyInside.allocate(amount);
		moneyInside = moneyInside.substract(output);
		BigDecimal amountWithCommission = new BigDecimal(
				Float.toString(caluculateAmountWithCommission(amount.floatValue())));
		moneyCharged = moneyCharged.add(amountWithCommission);
		
		//headOffice.setBalance(headOffice.getBalance() + amountWithCommission.floatValue());

	}

	public String canTakeMoney(float amount) {
		if (amount <= 0f)
			return "Invalid amount";
		if (moneyInside.getAmount().floatValue() < amount)
			return "Not enough money";
		if (!moneyInside.canAllocate(new BigDecimal(Float.toString(amount))))
			return "Not enough change";
		return "";

	}

	public float caluculateAmountWithCommission(float amount) {

		float commission = amount * commissionRate.floatValue();
		float lessThanCent = commission % 0.01f;
		if (lessThanCent > 0) {
			commission = commission - lessThanCent + 0.01f;
		}
		return amount + commission;
	}

	public void loadMoney(Money money) {
		moneyInside = moneyInside.add(money);
	}

	public Money getMoneyInside() {
		return moneyInside;
	}

	protected void setMoneyInside(Money moneyInside) {
		this.moneyInside = moneyInside;
	}

	public BigDecimal getMoneyCharged() {
		return moneyCharged;
	}

	protected void setMoneyCharged(BigDecimal moneyCharged) {
		this.moneyCharged = moneyCharged;
	}

	public AtmDto convertToAtmDto() {

		AtmDto atmDto = new AtmDto();
		atmDto.setId(id);

		atmDto.setMoneyCharged(moneyCharged);
		atmDto.setOneCentCount(moneyInside.getOneCentCount());
		atmDto.setTenCentCount(moneyInside.getTenCentCount());
		atmDto.setQuarterCount(moneyInside.getQuarterCount());
		atmDto.setOneDollarCount(moneyInside.getOneDollarCount());
		atmDto.setFiveDollarCount(moneyInside.getFiveDollarCount());
		atmDto.setTwentyDollarCount(moneyInside.getTwentyDollarCount());

		atmDto.setDomainEvents(getDomainEvents());
		return atmDto;

	}
}
