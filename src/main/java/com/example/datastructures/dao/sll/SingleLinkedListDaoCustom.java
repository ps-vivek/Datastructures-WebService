/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.dao.sll;

import com.example.datastructures.exception.sll.UniqueListNameViolationException;
import com.example.datastructures.model.sll.SingleLinkedListModel;

public interface SingleLinkedListDaoCustom {

	void insertSllIntoDb(SingleLinkedListModel sllModel);

	boolean isListNameAvailable(String listName) throws UniqueListNameViolationException;
}
