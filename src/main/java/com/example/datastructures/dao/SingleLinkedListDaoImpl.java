/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.datastructures.constants.SingleLinkedListConstants;
import com.example.datastructures.exception.UniqueListNameViolationException;
import com.example.datastructures.model.SingleLinkedListModel;

public class SingleLinkedListDaoImpl implements SingleLinkedListDaoCustom {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void insertSllIntoDb(SingleLinkedListModel sllModel) {
		mongoTemplate.save(sllModel, "SingleLinkedList");

	}

	@Override
	public boolean isListNameAvailable(String listName) throws UniqueListNameViolationException {
		Query findSllByNameCountQuery = new Query();
		findSllByNameCountQuery
				.addCriteria(Criteria.where(SingleLinkedListConstants.SINGLE_LINKED_LIST_NAME).is(listName));
		int size = mongoTemplate.find(findSllByNameCountQuery, SingleLinkedListModel.class).size();

		if (size > 1) {
			throw new UniqueListNameViolationException(SingleLinkedListConstants.DUPLICATE_LIST_NAME_RETRIEVED_MESSAGE);
		}

		return size == 0;
	}
}
