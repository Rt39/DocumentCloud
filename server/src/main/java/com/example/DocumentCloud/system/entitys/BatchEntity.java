package com.autumn.system.entitys;

import com.autumn.system.tools.CN_MessageEnum;


public class BatchEntity {
	
	private int result = CN_MessageEnum.InitialValue.value();
	
	private int batchCount = 0;

	public void setResult(int result) {
		this.result = result;
	}

	public int getResult() {
		return result;
	}

	public void setBatchCount(int batchCount) {
		this.batchCount = batchCount;
	}

	public int getBatchCount() {
		return batchCount;
	}
	
	

}
