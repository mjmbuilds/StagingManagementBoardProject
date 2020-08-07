package com.staging.model;

import java.util.ArrayList;
import java.util.List;

// meant to hold a list of primary ID's in the order that their index = the index field value
public class IndexList {
	private List<String> idList;
	
	public IndexList() {
		this.idList = new ArrayList<String>();
	}

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

}
