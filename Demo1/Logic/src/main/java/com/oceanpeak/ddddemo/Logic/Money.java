package com.oceanpeak.ddddemo.Logic;

import java.math.BigDecimal;
import java.math.MathContext;

public class Money extends ValueObject<Money> {
	private int oneCentCount;
	private int tenCentCount;
	private int quarterCount;
	private int oneDollarCount;
	private int fiveDollarCount;
	private int twentyDollarCount;
	
	public static Money NONE = new Money(0, 0, 0, 0, 0, 0);
    public static Money CENT = new Money(1, 0, 0, 0, 0, 0);
    public static Money TENCENT = new Money(0, 1, 0, 0, 0, 0);
    public static Money QUARTER = new Money(0, 0, 1, 0, 0, 0);
    public static Money DOLLAR = new Money(0, 0, 0, 1, 0, 0);
    public static Money FIVEDOLLAR = new Money(0, 0, 0, 0, 1, 0);
    public static Money TWENTYDOLLAR = new Money(0, 0, 0, 0, 0, 1);

	private BigDecimal amount;

	public static MathContext ROUND = new MathContext(2) ;
	public BigDecimal getAmount() {

		return getSingleAmout(oneCentCount, 0.01).
				add(getSingleAmout(tenCentCount, 0.1)).
				add(getSingleAmout(quarterCount, 0.25)).
				add(getSingleAmout(oneDollarCount, 1)).
				add(getSingleAmout(fiveDollarCount, 5)).
				add(getSingleAmout(twentyDollarCount, 20)).round(ROUND);
			
	}
	
	private BigDecimal getSingleAmout(int count, double unit) {
		if (count == 0) return new BigDecimal(0).round(ROUND);
		return new BigDecimal(count).multiply(new BigDecimal(unit)).round(ROUND) ;
	}

	public Money(int oneCentCount, int tenCentCount, int quarterCount, int oneDollarCount, int fiveDollarCount,
			int twentyDollarCount) {

		if (oneCentCount < 0)
			throw new IllegalStateException();
		if (tenCentCount < 0)
			throw new IllegalStateException();
		if (quarterCount < 0)
			throw new IllegalStateException();
		if (oneDollarCount < 0)
			throw new IllegalStateException();
		if (fiveDollarCount < 0)
			throw new IllegalStateException();
		if (twentyDollarCount < 0)
			throw new IllegalStateException();

		this.oneCentCount = oneCentCount;
		this.tenCentCount = tenCentCount;
		this.quarterCount = quarterCount;
		this.oneDollarCount = oneDollarCount;
		this.fiveDollarCount = fiveDollarCount;
		this.twentyDollarCount = twentyDollarCount;

	}

	public static Money add(Money money1, Money money2) {

		Money sum = new Money(

				money1.oneCentCount + money2.oneCentCount, money1.tenCentCount + money2.tenCentCount,
				money1.quarterCount + money2.quarterCount, money1.oneDollarCount + money2.oneDollarCount,
				money1.fiveDollarCount + money2.fiveDollarCount, money1.twentyDollarCount + money2.twentyDollarCount);

		return sum;

	}

	@Override
	protected boolean equalsCore(Money other) {

		return oneCentCount == other.oneCentCount && tenCentCount == other.tenCentCount
				&& quarterCount == other.quarterCount && oneDollarCount == other.oneDollarCount
				&& fiveDollarCount == other.fiveDollarCount && twentyDollarCount == other.twentyDollarCount;

	}

	@Override
	protected int getHashCodeCore() {
		int hashCode = oneCentCount;
		hashCode = (hashCode * 397) ^ tenCentCount;
		hashCode = (hashCode * 397) ^ quarterCount;
		hashCode = (hashCode * 397) ^ oneDollarCount;
		hashCode = (hashCode * 397) ^ fiveDollarCount;
		hashCode = (hashCode * 397) ^ twentyDollarCount;
		return hashCode;
	}

	public int getQuarterCount() {

		return quarterCount;

	}

	public int getOneCentCount() {

		return oneCentCount;

	}

	public int getTenCentCount() {

		return tenCentCount;

	}

	public int getOneDollarCount() {

		return oneDollarCount;

	}

	public int getFiveDollarCount() {

		return fiveDollarCount;

	}

	public int getTwentyDollarCount() {

		return twentyDollarCount;

	}

	public Money substract(Money other) {

		return new Money(
				oneCentCount - other.oneCentCount,
				tenCentCount - other.tenCentCount,
				quarterCount - other.quarterCount,
				oneDollarCount - other.oneDollarCount,
				fiveDollarCount - other.fiveDollarCount,
				twentyDollarCount - other.twentyDollarCount);

	}

	public Money add(Money other) {

		Money sum = new Money(
				oneCentCount + other.oneCentCount,
				tenCentCount + other.tenCentCount,
				quarterCount + other.quarterCount,
				oneDollarCount + other.oneDollarCount,
				fiveDollarCount + other.fiveDollarCount,
				twentyDollarCount + other.twentyDollarCount);

		return sum;

	}
	
	
	public boolean canAllocate(BigDecimal amount){

        Money money = allocateCore(amount);
        return money.getAmount().compareTo(amount) == 0;

    }

    public Money allocate(BigDecimal amount){

        if (!canAllocate(amount))
            throw new IllegalStateException();

        return allocateCore(amount);

    }

    private Money allocateCore(BigDecimal amount){

        int twentyDollarCount = Math.min(amount.divide(new BigDecimal(20),BigDecimal.ROUND_UP).intValue(), this.twentyDollarCount);
        amount = amount.subtract(new BigDecimal(twentyDollarCount * 20));

        int fiveDollarCount = Math.min(amount.divide(new BigDecimal(5),BigDecimal.ROUND_UP).intValue(), this.fiveDollarCount);
        amount = amount.subtract(new BigDecimal(fiveDollarCount * 5));

        int oneDollarCount = Math.min(amount.intValue(), this.oneDollarCount);
        amount = amount.subtract(new BigDecimal(oneDollarCount));

        int quarterCount = Math.min(amount.divide(new BigDecimal(0.25),BigDecimal.ROUND_UP).intValue(), this.quarterCount);
        amount = amount.subtract(new BigDecimal(quarterCount * 0.25));

        int tenCentCount = Math.min(amount.divide(new BigDecimal(0.1),BigDecimal.ROUND_UP).intValue(), this.tenCentCount);
        amount = amount.subtract(new BigDecimal(tenCentCount * 0.1));

        int oneCentCount = Math.min(amount.divide(new BigDecimal(0.01),BigDecimal.ROUND_UP).intValue(), this.oneCentCount);
        amount = amount.subtract(new BigDecimal(oneCentCount * 0.01));
        return new Money(
            oneCentCount,
            tenCentCount,
            quarterCount,
            oneDollarCount,
            fiveDollarCount,
            twentyDollarCount);
    }

}
