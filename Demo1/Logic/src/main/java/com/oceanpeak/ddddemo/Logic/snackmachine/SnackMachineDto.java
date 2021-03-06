package com.oceanpeak.ddddemo.Logic.snackmachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import com.oceanpeak.ddddemo.Logic.sharedkernel.Money;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

@Entity
public class SnackMachineDto {
	
	@Id
    @GeneratedValue
    private long id;

    private int oneCentCount;
    private int tenCentCount;
    private int quarterCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;
    private BigDecimal moneyInTransaction;
    
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "snackMachineId")
    private List<SlotDto> slotDtoList;
	
	@Transient
    private BigDecimal amount ;

    public BigDecimal getAmount() {
            return  amount;
    }

    @PostLoad
    public void setAmount() {
    	float amt = oneCentCount * 0.01f + tenCentCount * 0.10f + quarterCount * 0.25f + oneDollarCount * 1f
                        + fiveDollarCount * 5f + twentyDollarCount * 20f;
    	amount= new BigDecimal(Float.toString(amt)) ;

    }
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getOneCentCount() {
		return oneCentCount;
	}
	public void setOneCentCount(int oneCentCount) {
		this.oneCentCount = oneCentCount;
	}
	public int getTenCentCount() {
		return tenCentCount;
	}
	public void setTenCentCount(int tenCentCount) {
		this.tenCentCount = tenCentCount;
	}
	public int getQuarterCount() {
		return quarterCount;
	}
	public void setQuarterCount(int quarterCount) {
		this.quarterCount = quarterCount;
	}
	public int getOneDollarCount() {
		return oneDollarCount;
	}
	public void setOneDollarCount(int oneDollarCount) {
		this.oneDollarCount = oneDollarCount;
	}
	public int getFiveDollarCount() {
		return fiveDollarCount;
	}
	public void setFiveDollarCount(int fiveDollarCount) {
		this.fiveDollarCount = fiveDollarCount;
	}
	public int getTwentyDollarCount() {
		return twentyDollarCount;
	}
	public void setTwentyDollarCount(int twentyDollarCount) {
		this.twentyDollarCount = twentyDollarCount;
	}
	public BigDecimal getMoneyInTransaction() {
		return moneyInTransaction;
	}
	public void setMoneyInTransaction(BigDecimal moneyInTransaction) {
		this.moneyInTransaction = moneyInTransaction;
	}
	
	
	
	public List<SlotDto> getSlotDtoList() {
		return slotDtoList;
	}
	public void setSlotDtoList(List<SlotDto> slotDtoList) {
		this.slotDtoList = slotDtoList;
	}
	public SnackMachine convertToSnackMachine() {

		SnackMachine snackMachine = new SnackMachine();
        snackMachine.setId(id);
        snackMachine.setMoneyInTransaction(moneyInTransaction);
        snackMachine.setMoneyInside(new Money(oneCentCount,tenCentCount,quarterCount,
        		oneDollarCount,fiveDollarCount,twentyDollarCount));

        List<Slot> slotList = new ArrayList<>();

        for(SlotDto slotDto : slotDtoList) {
                slotList.add(slotDto.convertToSlot());
        }
        snackMachine.setSlots(slotList);

        return snackMachine;

}
	
	
	
	

}
