/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.dao.stack;

import com.example.datastructures.model.stack.IsBalancedStackModel;

public interface StackDaoCustom {

	void insertIntoIsBalancedStack(IsBalancedStackModel isBalancedStackModel);

	String fetchIsBalancedStackResult(String pattern);
}
