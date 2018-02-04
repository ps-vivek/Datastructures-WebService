/**
 * Author: Vivek
 * Date: 02/04/2018
 */
package com.example.datastructures.controller;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.datastructures.constants.SingleLinkedListConstants;
import com.example.datastructures.request.SingleLinkedListRequest;
import com.example.datastructures.response.SingleLinkedListResponse;
import com.example.datastructures.service.SingleLinkedListService;
import com.example.datastructures.utils.DataStructureUtils;

@RestController
@RequestMapping("/ds/SLL")
public class SingleLinkedListController {

	@Autowired
	SingleLinkedListService sllService;

	@RequestMapping(value = "/insert/appendFirst", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML, consumes = MediaType.APPLICATION_JSON)
	public String appendTOFirst(@RequestBody SingleLinkedListRequest sllRequest) throws JAXBException {
		SingleLinkedListResponse sllResponse = new SingleLinkedListResponse();
		JAXBContext context = JAXBContext.newInstance(SingleLinkedListResponse.class);
		try {
			if (sllRequest != null && sllRequest.getListName() != null && sllRequest.getListElements() != null
					&& sllRequest.getListElements().size() > 0) {
				sllResponse = sllService.appendToSLL(sllRequest);
			} else {
				sllResponse.setStatus(SingleLinkedListConstants.FAILURE);
				sllResponse.setDescription(SingleLinkedListConstants.FAILURE_APPEND_DESCRIPTION);
			}
		} catch (Exception e) {
			sllResponse.setStatus(SingleLinkedListConstants.FAILURE);
			sllResponse.setDescription(SingleLinkedListConstants.FAILURE_APPEND_DESCRIPTION);
		}

		return DataStructureUtils.convertJavaObjToXml(context, sllResponse);
	}

}
