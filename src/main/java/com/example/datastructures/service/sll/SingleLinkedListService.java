/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.service.sll;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.datastructures.constants.sll.SingleLinkedListConstants;
import com.example.datastructures.dao.sll.SingleLinkedListDao;
import com.example.datastructures.model.sll.SingleLinkedListModel;
import com.example.datastructures.model.sll.SingleLinkedListStructure;
import com.example.datastructures.request.sll.SingleLinkedListRequest;
import com.example.datastructures.response.sll.SingleLinkedListResponse;

@Service
public class SingleLinkedListService {
	public static final Logger logger = LoggerFactory.getLogger(SingleLinkedListService.class);

	@Autowired
	private SingleLinkedListDao sllDao;

	SingleLinkedList head;
	int count = 0;

	private class SingleLinkedList {
		String data = "";
		SingleLinkedList next;

		public SingleLinkedList(String data, SingleLinkedList next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * Appending into single linked list
	 *
	 * Appending to start of the list. If the given list is empty Appending to list
	 * as last element if the given list contains at least one value
	 *
	 * @param value
	 */
	public SingleLinkedListResponse appendToTail(SingleLinkedListRequest sllRequest) throws Exception {
		// Check whether the list exists in the db
		// If it exists, throw an exception, saying list is already present and can't be
		// edited
		// If it does not exist, add the list to mongo db
		logger.info("SingleLinkedListService.appendToTail=>Entered");
		SingleLinkedListResponse sllResponse = new SingleLinkedListResponse();
		boolean isListNameAvailable = sllDao.isListNameAvailable(sllRequest.getListName());
		if (isListNameAvailable) {
			for (String value : sllRequest.getListElements()) {
				if (head == null) {
					head = new SingleLinkedList(value, null);
				} else {
					// List has a minimum of one value
					// Iterate the list and capture the last element of the list
					SingleLinkedList list = head;
					SingleLinkedList currentTail = null;
					while (list != null) {
						currentTail = list;
						list = list.next;

					}
					// If the last element is not null, then assign the last elements next, to newly
					// created node
					if (currentTail != null) {
						SingleLinkedList newTail = new SingleLinkedList(value, null);
						currentTail.next = newTail;
					}

				}
			}
			insertSllIntoDb(head, sllRequest.getListName());
			sllResponse.setStatus(SingleLinkedListConstants.SUCCESS);
			sllResponse.setDescription(SingleLinkedListConstants.SUCESS_APPEND_DESCRIPTION);

		} else {
			sllResponse.setStatus(SingleLinkedListConstants.FAILURE);
			sllResponse.setDescription(SingleLinkedListConstants.DUPLICATE_LIST_NAME_EXCEPTION_MESSAGE);
		}
		logger.info("SingleLinkedListService.appendToTail=>Exited");
		return sllResponse;
	}

	public void insertSllIntoDb(SingleLinkedList head, String listName) {
		logger.info("SingleLinkedListService.insertSllIntoDb=>Entered");
		SingleLinkedListModel sllModel = new SingleLinkedListModel();
		sllModel.setSllName(listName);
		List<SingleLinkedListStructure> sllList = new ArrayList<>();
		SingleLinkedList temp = head;
		while (temp != null) {
			SingleLinkedListStructure sllNode = new SingleLinkedListStructure();
			sllNode.setData(temp.data);
			sllNode.setNext(temp.next != null ? temp.next.toString() : "null");
			sllList.add(sllNode);
			logger.debug("SingleLinkedListService.insertSllIntoDb=>" + temp.data + " " + temp.next + ",");
			temp = temp.next;
		}
		sllModel.setSllData(sllList);
		logger.debug("SingleLinkedListService.insertSllIntoDb=SllModel:" + sllModel);
		sllDao.insertSllIntoDb(sllModel);
		logger.info("SingleLinkedListService.insertSllIntoDb=>Exited");

	}
}
