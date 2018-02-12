/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.model.sll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SingleLinkedList")
public class SingleLinkedListModel {

	private String sllName;

	private List<SingleLinkedListStructure> sllData = new ArrayList<>();

	public String getSllName() {
		return sllName;
	}

	public void setSllName(String sllName) {
		this.sllName = sllName;
	}

	public List<SingleLinkedListStructure> getSllData() {
		return sllData;
	}

	public void setSllData(List<SingleLinkedListStructure> sllData) {
		this.sllData = sllData;
	}

}
