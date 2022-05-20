package com.autumn.system.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SendData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String parkingId;
	private String interfaceURL;

	private List<SendDataDetail> data=new ArrayList<SendDataDetail>();

	
	public String getInterfaceURL() {
		return interfaceURL;
	}

	public void setInterfaceURL(String interfaceURL) {
		this.interfaceURL = interfaceURL;
	}

	public String getParkingId() {
		return parkingId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}

	public List<SendDataDetail> getData() {
		return data;
	}

	public void setData(List<SendDataDetail> data) {
		this.data = data;
	}

	
	
}
