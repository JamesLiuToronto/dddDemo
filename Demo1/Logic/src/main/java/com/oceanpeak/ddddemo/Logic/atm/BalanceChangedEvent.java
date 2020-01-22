package com.oceanpeak.ddddemo.Logic.atm;

import org.springframework.context.ApplicationEvent;
public class BalanceChangedEvent extends ApplicationEvent {
   
	private static final long serialVersionUID = -4718130828413754051L;
	public float delta;
    public BalanceChangedEvent(Object source, float delta) {
        super(source);
        this.delta = delta;
    }

    public float getDelta() {
        return delta;

    }

}
