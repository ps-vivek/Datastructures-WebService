/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.dao.sll;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.datastructures.model.sll.SingleLinkedListModel;

public interface SingleLinkedListDao extends MongoRepository<SingleLinkedListModel, String>, SingleLinkedListDaoCustom {
}
