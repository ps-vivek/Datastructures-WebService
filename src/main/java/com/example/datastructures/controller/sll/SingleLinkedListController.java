/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.controller.sll;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.datastructures.constants.sll.SingleLinkedListConstants;
import com.example.datastructures.exception.sll.UniqueListNameViolationException;
import com.example.datastructures.request.sll.SingleLinkedListRequest;
import com.example.datastructures.response.sll.SingleLinkedListResponse;
import com.example.datastructures.service.sll.SingleLinkedListService;
import com.example.datastructures.utils.DataStructureUtils;

@RestController
@RequestMapping("/ds/SLL")
public class SingleLinkedListController {
	
	public static final Logger logger = LoggerFactory.getLogger(SingleLinkedListController.class);

	@Autowired
	SingleLinkedListService sllService;

	@RequestMapping(value = "/insert/appendToTail", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML, consumes = MediaType.APPLICATION_JSON)
	public String appendTOFirst(@RequestBody SingleLinkedListRequest sllRequest) throws JAXBException {
		logger.info("SingleLinkedListController.appendToTail=>Entered");
		SingleLinkedListResponse sllResponse = new SingleLinkedListResponse();
		JAXBContext context = JAXBContext.newInstance(SingleLinkedListResponse.class);
		try {
			if (sllRequest != null && sllRequest.getListName() != null && !sllRequest.getListName().isEmpty() && sllRequest.getListElements() != null
					&& sllRequest.getListElements().size() > 0) {
				sllResponse = sllService.appendToTail(sllRequest);
			} else {
				sllResponse.setStatus(SingleLinkedListConstants.FAILURE);
				sllResponse.setDescription(SingleLinkedListConstants.INVALID_INPUT_ERROR);
			}
		} catch (UniqueListNameViolationException e) {
			logger.error(e.getMessage());
			sllResponse.setStatus(SingleLinkedListConstants.FAILURE);
			sllResponse.setDescription(e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage());
			sllResponse.setStatus(SingleLinkedListConstants.FAILURE);
			sllResponse.setDescription(SingleLinkedListConstants.FAILURE_APPEND_DESCRIPTION);			
		}
		logger.info("SingleLinkedListController.appendToTail=>Exited");
		return DataStructureUtils.convertJavaObjToXml(context, sllResponse);
	}

}
