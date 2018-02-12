package com.example.datastructures.model.stack;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "IsBalancedStack")
public class IsBalancedStackModel {

	private String pattern;
	private String isBalanced;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getIsBalanced() {
		return isBalanced;
	}

	public void setIsBalanced(String isBalanced) {
		this.isBalanced = isBalanced;
	}

}
