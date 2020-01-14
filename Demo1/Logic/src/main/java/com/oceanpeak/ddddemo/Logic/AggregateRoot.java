package com.oceanpeak.ddddemo.Logic;

public abstract class AggregateRoot extends Entity {

    private  int version ;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
    
    
    
}
