/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.response.sll;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "status", "description" })
public class SingleLinkedListResponse {

	private String status;
	private String description;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
