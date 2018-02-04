/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.datastructures.constants.SingleLinkedListConstants;
import com.example.datastructures.exception.UniqueListNameViolationException;
import com.example.datastructures.model.SingleLinkedListModel;

public class SingleLinkedListDaoImpl implements SingleLinkedListDaoCustom {

	public static final Logger logger = LoggerFactory.getLogger(SingleLinkedListDaoImpl.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void insertSllIntoDb(SingleLinkedListModel sllModel) {
		logger.info("SingleLinkedListDaoImpl.insertSllIntoDb=>Entered");
		mongoTemplate.save(sllModel, "SingleLinkedList");
		logger.info("SingleLinkedListDaoImpl.insertSllIntoDb=>Exited");
	}

	@Override
	public boolean isListNameAvailable(String listName) throws UniqueListNameViolationException {
		logger.info("SingleLinkedListDaoImpl.isListNameAvailable=>Entered");
		Query findSllByNameCountQuery = new Query();
		findSllByNameCountQuery
				.addCriteria(Criteria.where(SingleLinkedListConstants.SINGLE_LINKED_LIST_NAME).is(listName));
		int size = mongoTemplate.find(findSllByNameCountQuery, SingleLinkedListModel.class).size();
		if (size > 1) {
			throw new UniqueListNameViolationException(SingleLinkedListConstants.DUPLICATE_LIST_NAME_RETRIEVED_MESSAGE);
		}
		logger.info("SingleLinkedListDaoImpl.isListNameAvailable=>Exited");
		return size == 0;
	}
}
