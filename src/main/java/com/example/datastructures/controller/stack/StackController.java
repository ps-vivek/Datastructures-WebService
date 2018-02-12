/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.controller.stack;

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

import com.example.datastructures.constants.stack.StackConstants;
import com.example.datastructures.response.stack.StackResponse;
import com.example.datastructures.service.stack.StackService;
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
					stackResponse.setDescription(StackConstants.PATTERN_BALANCED);
				}else {
					stackResponse.setDescription(StackConstants.PATTERN_NOT_BALANCED);
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
