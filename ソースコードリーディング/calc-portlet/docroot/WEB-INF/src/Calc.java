package com.helloliferay.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class Calc extends GenericPortlet {
	public void init() throws PortletException {
	}

	public void doView(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		response.setContentType("text/html");
		PortletContext context = getPortletContext();
		String result = request.getParameter("result");
		if(result == null) {
			result = "";
		}
		request.setAttribute("result",result);
		String textbox1 = request.getParameter("textbox1");
		if(textbox1 == null) {
			textbox1 = "";
		}
		request.setAttribute("textbox1",textbox1);
		String textbox2 = request.getParameter("textbox2");
		if(textbox2 == null) {
			textbox2 = "";
		}
		request.setAttribute("textbox2",textbox2);
		
		PortletRequestDispatcher rd = context.getRequestDispatcher("/view.jsp");
		rd.include(request, response);
	}

	public void processAction(ActionRequest request, ActionResponse response) throws IOException, PortletException {
		String text1 = request.getParameter("textbox1");
		String text2 = request.getParameter("textbox2");
		String result;
		
		response.setRenderParameter("textbox1",text1);
		response.setRenderParameter("textbox2",text2);
		if(check(text1) && check(text2)){
			result = String.valueOf(Double.parseDouble(text1) + Double.parseDouble(text2));
			response.setRenderParameter("result",result);
		}
	}

	private boolean check(String str) {
		if(str == null){
			System.out.println("empty");
			return false;
		}
		try {
			Double.parseDouble(str);
		} catch(NumberFormatException e) {
			System.out.println("not Numeric");
			return false;
		}
		return true;
	}
}

