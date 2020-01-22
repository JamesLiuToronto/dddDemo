package com.oceanpeak.ddddemo.Logic.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.oceanpeak.ddddemo.Logic.atm.BalanceChangedEvent;

@Component
public class BalanceChangedEventHandler implements ApplicationListener<BalanceChangedEvent>{

	@Autowired
    private HeadOfficeInstance headOfficeInstance;
	@Autowired
    private HeadOfficeRepository headOfficeRepository;

    @Override
    public void onApplicationEvent(BalanceChangedEvent domainEvent) {
            HeadOfficeDto headOfficeDto = headOfficeInstance.getInstance();
            HeadOffice headOffice = headOfficeDto.convertToHeadOffice();
            headOffice.changeBalance(domainEvent.getDelta());
            headOfficeRepository.save(headOffice.convertToHeadOfficeDto());
    }
}
