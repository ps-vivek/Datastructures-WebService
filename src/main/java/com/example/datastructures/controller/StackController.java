/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.controller;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datastructures.constants.StackConstants;
import com.example.datastructures.response.StackResponse;
import com.example.datastructures.service.StackService;
import com.example.datastructures.utils.DataStructureUtils;

@RestController
@RequestMapping("/ds/stack")
public class StackController {
	
	public static final Logger logger = LoggerFactory.getLogger(StackController.class);

	@Autowired
	StackService stackService;

	@RequestMapping(value = "/isbalanced", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML)
	public String isBalanced(@RequestParam (value = "pattern", required=true)  String pattern) throws JAXBException {
		
		logger.info("StackController.isBalanced=>Entered");
		StackResponse stackResponse = new StackResponse();
		JAXBContext context = JAXBContext.newInstance(StackResponse.class);
		try {
			stackResponse.setStatus(StackConstants.SUCCESS);
			if (pattern != null && !"".equals(pattern)) {
				if("YES".equals(stackService.isBalanced(pattern))){
					stackResponse.setDescription(StackConstants.IS_BALANCED_TRUE);
				}else {
					stackResponse.setDescription(StackConstants.IS_BALANCED_FALSE);
				}
			} else {
				stackResponse.setStatus(StackConstants.FAILURE);
				stackResponse.setDescription(StackConstants.INVALID_INPUT_ERROR);
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			stackResponse.setStatus(StackConstants.FAILURE);
			stackResponse.setDescription(StackConstants.FAILURE_IN_CHECKING_ISBALANCED);			
		}
		logger.info("StackController.isBalanced=>Exited");
		return DataStructureUtils.convertJavaObjToXml(context, stackResponse);
	}

}
