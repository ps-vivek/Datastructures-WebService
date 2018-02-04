/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.dao;

import com.example.datastructures.exception.UniqueListNameViolationException;
import com.example.datastructures.model.SingleLinkedListModel;

public interface SingleLinkedListDaoCustom {

	void insertSllIntoDb(SingleLinkedListModel sllModel);

	boolean isListNameAvailable(String listName) throws UniqueListNameViolationException;
}
