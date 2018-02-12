/**
 * Author: Vivek
 * Date: 02/11/2018
 */
package com.example.datastructures.service.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.datastructures.constants.stack.StackConstants;
import com.example.datastructures.dao.stack.StackDao;
import com.example.datastructures.model.stack.IsBalancedStackModel;

@Service
public class StackService {
	
	@Autowired
	StackDao stackDao;
	
	public static final Logger logger = LoggerFactory.getLogger(StackService.class);

	Map<Character, Character> symbolMap = new HashMap<>();

	public StackService() {
		loadSymbolMap();
	}

	/*
	 * 1) Break the string and iterate into characters. Initialize stack, 2) If char
	 * is '{', '[' or '(' add in a stack else go to next step 3) If char is any
	 * closing symbol, then check whether the corresponding last element in stack is
	 * its opening symbol. If opening symbol is different than closing symbol, then
	 * brackets are not balanced. 4) Repeat the above step for all the characters.
	 * If there are no elements in stack at end of iterating all characters, then
	 * the pattern is balanced one.
	 */
	public String isBalanced(String pattern) throws Exception {
		String isBalanced = "";
		IsBalancedStackModel isBalancedStackModel;
		
		try {
		//Check whether the pattern is already present in db. If so directly return the result
		isBalanced = stackDao.fetchIsBalancedStackResult(pattern);
		if(isBalanced != null && !"".equals(isBalanced)) {
			return isBalanced;
		}
		
		isBalancedStackModel = new IsBalancedStackModel();
		isBalancedStackModel.setPattern(pattern);
		isBalanced = isBalancedHelper(pattern);
		if(isBalanced.equals(StackConstants.IS_BALANCED_TRUE)) {
			isBalancedStackModel.setIsBalanced(StackConstants.IS_BALANCED_TRUE);
		}else {
			isBalancedStackModel.setIsBalanced(StackConstants.IS_BALANCED_FALSE);
		}
		// Insert the pattern along with the result to db
		stackDao.insertIntoIsBalancedStack(isBalancedStackModel);
		}
		catch(Exception e) {
			throw new Exception(e);
		}
		return isBalanced;

	}

	private void loadSymbolMap() {
		symbolMap.put(StackConstants.CLOSE_PARANTHESIS, StackConstants.OPEN_PARANTHESIS);
		symbolMap.put(StackConstants.CLOSE_FLOWER_BRACES, StackConstants.OPEN_FLOWER_BRACES);
		symbolMap.put(StackConstants.CLOSE_SQUARE_BRACES, StackConstants.OPEN_SQUARE_BRACES);
	}

	private boolean isStackBalanced(Stack<Character> stack, Character ch) {
		boolean isStackBalanced = false;
		if (!stack.isEmpty() && stack.peek().equals(symbolMap.get(ch))) {
			stack.pop();
			isStackBalanced = true;
		}
		return isStackBalanced;
	}
	public String isBalancedHelper(String pattern) {
		Stack<Character> stack = new Stack<Character>();

		for (Character ch : pattern.toCharArray()) {
			if ((ch.equals(StackConstants.OPEN_PARANTHESIS)) || (ch.equals(StackConstants.OPEN_FLOWER_BRACES))
					|| (ch.equals(StackConstants.OPEN_SQUARE_BRACES))) {
				stack.push(ch);
				continue;
			}

			switch (ch) {
			case ']':
				if (!isStackBalanced(stack, StackConstants.CLOSE_SQUARE_BRACES))
					return StackConstants.IS_BALANCED_FALSE;
				break;

			case '}':
				if (!isStackBalanced(stack, StackConstants.CLOSE_FLOWER_BRACES))
					return StackConstants.IS_BALANCED_FALSE;
				break;

			case ')':
				if (!isStackBalanced(stack, StackConstants.CLOSE_PARANTHESIS))
					return StackConstants.IS_BALANCED_FALSE;
				break;

			}

		}
		if (!stack.isEmpty()) {
			return StackConstants.IS_BALANCED_FALSE;
		}


		return StackConstants.IS_BALANCED_TRUE;
		
	}
}
