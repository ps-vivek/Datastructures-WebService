/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.dao.stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.datastructures.constants.stack.StackConstants;
import com.example.datastructures.model.stack.IsBalancedStackModel;

public class StackDaoImpl implements StackDaoCustom {

	public static final Logger logger = LoggerFactory.getLogger(StackDaoImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void insertIntoIsBalancedStack(IsBalancedStackModel IsBalancedStackModel) {
		logger.info("StackDaoImpl.insertIntoIsBalancedStack=>Entered");
		mongoTemplate.save(IsBalancedStackModel, "IsBalancedStack");
		logger.info("StackDaoImpl.insertIntoIsBalancedStack=>Exited");
	}

	@Override
	public String fetchIsBalancedStackResult(String pattern)  {
		logger.info("StackDaoImpl.fetchIsBalancedStackResult=>Entered");
		Query findIsBalancedStackResultQuery = new Query();
		findIsBalancedStackResultQuery
				.addCriteria(Criteria.where(StackConstants.PATTERN).is(pattern));
		IsBalancedStackModel isBalancedStackModel = mongoTemplate.findOne(findIsBalancedStackResultQuery,IsBalancedStackModel.class);
		logger.info("StackDaoImpl.fetchIsBalancedStackResult=>Exited");
		return isBalancedStackModel != null ?isBalancedStackModel.getIsBalanced():"";
	}
}
