/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.dao.stack;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.datastructures.model.stack.IsBalancedStackModel;

public interface StackDao extends MongoRepository<IsBalancedStackModel, String>, StackDaoCustom {
}
