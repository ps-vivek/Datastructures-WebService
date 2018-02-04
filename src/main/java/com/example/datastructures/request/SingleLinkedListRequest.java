/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.request;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedListRequest {

	private String listName;

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public List<String> getListElements() {
		return listElements;
	}

	public void setListElements(List<String> listElements) {
		this.listElements = listElements;
	}

	private List<String> listElements = new ArrayList<String>();

}
