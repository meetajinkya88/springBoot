package com.centime.assignment.view.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.centime.assignment.entity.StringConstants;

@Component
public class PersonViewHandlerFactory {

	@Autowired
	private ApplicationContext applicationContext;

	
	public IPersonViewHandler getPersonViewHandlerInstance(int viewType)
	{
		return applicationContext.getBean(getPersonViewHandlerQualifier(viewType), IPersonViewHandler.class);
	}
	private String getPersonViewHandlerQualifier(int viewType) {
        return StringConstants.PERSON_VIEW_HANDLER +  viewType;
	}
	

}
