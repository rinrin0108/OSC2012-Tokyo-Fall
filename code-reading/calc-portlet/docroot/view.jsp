<%
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>


<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<jsp:useBean id="result" class="java.lang.String" scope="request" />
<jsp:useBean id="textbox1" class="java.lang.String" scope="request" />
<jsp:useBean id="textbox2" class="java.lang.String" scope="request" />

<portlet:defineObjects />

This is the <b>Calc</b> portlet.

<form
	action="<portlet:actionURL />"
	method="post">
<input type="textbox" name="textbox1" value="<%= textbox1 %>">
+
<input type="textbox" name="textbox2" value="<%= textbox2 %>">
<input type="submit" id="equalsButton" title="equals" value="=">
<%= result %>
</form>

