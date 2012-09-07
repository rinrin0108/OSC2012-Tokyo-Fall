# Liferay Portal�̃R�[�h���[�f�B���O�̃|�C���g���܂Ƃ߂܂���

----
## hello-liferay-portlet

### view.jsp

```jsp
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

<portlet:defineObjects />

This is the <b>Hello-liferay</b> portlet.
```

- `<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>`
    - uri�łǂ̃J�X�^���^�O���g�p���邩��ݒ�
        - `"http://java.sun.com/portlet_2_0"`��java�̕W���Z�b�g
    - prefix�Őړ����ݒ�
        - `<portlet:�`>`�ŗ��p�\�ɂȂ�
- `<portlet:defineObjects />`
    - portlet_2_0.tld�Ŏw�肳�ꂽ�N���X�uorg.apache.pluto.tags.DefineObjectsTag286�v��defineObjects���\�b�h�����s�����
        - �|�[�g���b�g�ɕK�v�ȏ����ݒ���s��

### WEB-INF/liferay-plugin-package.properties

```properties
name=Hello-liferay
module-group-id=liferay
module-incremental-version=1
tags=
short-description=
change-log=
page-url=http://www.liferay.com
author=Liferay, Inc.
licenses=LGPL
liferay-versions=6.1.1
```

- Liferay�����W���[���iwar�t�@�C���P�ʁj���Ǘ����邽�߂̃v���p�e�B���L�q�����t�@�C��

### WEB-INF/liferay-display.xml

```xml
<?xml version="1.0"?>
<!DOCTYPE display PUBLIC "-//Liferay//DTD Display 6.1.0//EN" "http://www.liferay.com/dtd/liferay-display_6_1_0.dtd">

<display>
	<category name="category.sample">
		<portlet id="hello-liferay" />
	</category>
</display>
```

- `<category name="category.sample">`
    - �|�[�g���b�g�̃J�e�S����ݒ�F�u�T���v���v
- `<portlet id="hello-liferay" />`
    - �|�[�g���b�gID��ݒ�F�uhello-liferay�v

### WEB-INF/liferay-portlet.xml

```xml
<?xml version="1.0"?>
<!DOCTYPE liferay-portlet-app PUBLIC "-//Liferay//DTD Portlet Application 6.1.0//EN" "http://www.liferay.com/dtd/liferay-portlet-app_6_1_0.dtd">

<liferay-portlet-app>
	<portlet>
		<portlet-name>hello-liferay</portlet-name>
		<icon>/icon.png</icon>
		<instanceable>false</instanceable>
		<header-portlet-css>/css/main.css</header-portlet-css>
		<footer-portlet-javascript>/js/main.js</footer-portlet-javascript>
		<css-class-wrapper>hello-liferay-portlet</css-class-wrapper>
	</portlet>
	<role-mapper>
		<role-name>administrator</role-name>
		<role-link>Administrator</role-link>
	</role-mapper>
	<role-mapper>
		<role-name>guest</role-name>
		<role-link>Guest</role-link>
	</role-mapper>
	<role-mapper>
		<role-name>power-user</role-name>
		<role-link>Power User</role-link>
	</role-mapper>
	<role-mapper>
		<role-name>user</role-name>
		<role-link>User</role-link>
	</role-mapper>
</liferay-portlet-app>
```

- `<css-class-wrapper>hello-liferay-portlet</css-class-wrapper>`
    - css�t�@�C���Ɂu`.hello-liferay-portlet{...}`�v�Ə������Ƃɂ��A���̃X�^�C����K�p�ł���
- `<role-mapper>�`</role-mapper>`
    - ���[���̒�`

### WEB-INF/portlet.xml

```xml
<?xml version="1.0"?>

<portlet-app xmlns="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" version="2.0">
	<portlet>
		<portlet-name>hello-liferay</portlet-name>
		<display-name>Hello-liferay</display-name>
		<portlet-class>com.liferay.util.bridges.mvc.MVCPortlet</portlet-class>
		<init-param>
			<name>view-template</name>
			<value>/view.jsp</value>
		</init-param>
		<expiration-cache>0</expiration-cache>
		<supports>
			<mime-type>text/html</mime-type>
		</supports>
		<portlet-info>
			<title>Hello-liferay</title>
			<short-title>Hello-liferay</short-title>
			<keywords>Hello-liferay</keywords>
		</portlet-info>
		<security-role-ref>
			<role-name>administrator</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>guest</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>power-user</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>user</role-name>
		</security-role-ref>
	</portlet>
</portlet-app>
```

- `<portlet-class>com.liferay.util.bridges.mvc.MVCPortlet</portlet-class>`
    - JSP�Ŏw��ł���Action�N���X��ݒ�
        - `<form action="<portlet:actionURL />" method="post">...</form>`�Ƃ��Ƃ����Ŏw�肵��Action�N���X���Ă΂��
- `<init-param>�`</init-param>`
    - `<name>view-template</name>`�܂���`<name>view-jsp</name>`�Ŏw�肵��jsp���f�t�H���g�ŕ\�������
    - `<init-param>`�́A���p������jsp�̕������ǉ��ł���

----
## hello-liferay-portlet2

### view.jsp

```jsp
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
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />

This is the <b>Hello-liferay2</b> portlet.<br>
Hello, <%= user.getScreenName() %>!<br>
```

- `<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>`
    - `"http://liferay.com/tld/theme"`��liferay�̕W���Z�b�g
- `<liferay-theme:defineObjects />`
    - liferay-theme.tld�Ŏw�肳�ꂽ�N���X�ucom.liferay.taglib.theme.DefineObjectsTag�v��DefineObjects���\�b�h�����s�����
    - DefineObjects���\�b�h�ł́AJSP��pageContext�Ƀ��O�C�����[�U�̃C���X�^���X���uuser�v�Ƃ���setAttribute����
        - `pageContext.setAttribute("user", themeDisplay.getUser());`
- `Hello, <%= user.getScreenName() %>!<br>`
    - `<%= user.getScreenName() %>`��pageContext��setAttribute���ꂽuser�i���O�C�����[�U�̃C���X�^���X�j����A���O�C�����[�U�����擾����

----
## calc-portlet

### WEB-INF/src/Calc.java>Calc.java

```java
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
```

- �W�J��́A�uWEB-INF/classes/com/helloliferay/portlet/Calc.class�v
- doView���\�b�h
    - �|�[�g���b�g�̉�ʕ\���̍ۂɎ����I�Ɏ��s����郁�\�b�h
    - `String result = request.getParameter("result");`
        - RenderRequest����uresult�v�Ƃ����p�����[�^���擾
        - RenderRequest�́A��ʕ\���̂��߂�portlet�ɑ���ꂽ���N�G�X�g��\��
    - `PortletRequestDispatcher rd = context.getRequestDispatcher("/view.jsp");`
        - render�̒���PortletRequestDispatcher���擾���邱�Ƃɂ��APortlet����Servlet��JSP��include���邱�Ƃ��ł���
    - `rd.include(request, response);`
        - view.jsp���Ăяo�����
- processAction���\�b�h
    - portlet.xml�Ŏw�肳�ꂽActionURL�ɂ��Action Request���g���K�ƂȂ��ČĂяo�����B�ʏ�AForm���͂Ȃǂ̃A�N�V�����ɑ΂��āAprocessAction���\�b�h�����������ƁA�����ē���Portlet��render���\�b�h��Portlet Container�ɂ��Ăяo����AprocessAction���\�b�h�̏������ʂɉ������r���[�𐶐�����B
    - processAction���\�b�h��render���\�b�h�ԂŃf�[�^���󂯓n�����߂ɂ́ARender�p�����[�^�𗘗p����BprocessAction���\�b�h�̈����ł���ActionResponse�I�u�W�F�N�g�ɂ́AsetRenderParameter�Ƃ������\�b�h������A���̃��\�b�h��p���āARender Request�փp�����[�^��n�����Ƃ��ł���B�܂��ASession�X�R�[�v�𗘗p���邱�Ƃ��\�B
    - `String XXXX = request.getParameter("xxxx");`
        - ActionRequest����uxxxx�v�Ƃ����p�����[�^���擾
    - `response.setRenderParameter("xxxx",XXXX);`
        - ActionResponse�̉�ʕ\���p�p�����[�^�uxxxx�v�ɁuXXXX�v���Z�b�g
    - `if(check(text1) && check(text2)){�``
        - null�l�A���l�`�F�b�N
        - OK�Ȃ�ActionResponse�̉�ʕ\���p�p�����[�^�uresult�v�Ɍv�Z���ʂ��Z�b�g
- check���\�b�h
    - `if(str == null){�``
        - null�l�`�F�b�N
        - null�Ȃ�false��Ԃ�
    - `try {�``
        - ���l�`�F�b�N
        - Double�^�ɕϊ��ł��Ȃ������l�łȂ��Ȃ�Afalse��Ԃ�
    - ����ȊO�Ȃ�true��Ԃ�

### WEB-INF/portlet.xml

```xml
<?xml version="1.0"?>

<portlet-app xmlns="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd" version="2.0">
	<portlet>
		<portlet-name>calc</portlet-name>
		<display-name>Calc</display-name>
		<portlet-class>com.helloliferay.portlet.Calc</portlet-class>
		<init-param>
			<name>view-template</name>
			<value>/view.jsp</value>
		</init-param>
		<expiration-cache>0</expiration-cache>
		<supports>
			<mime-type>text/html</mime-type>
		</supports>
		<portlet-info>
			<title>Calc</title>
			<short-title>Calc</short-title>
			<keywords>Calc</keywords>
		</portlet-info>
		<security-role-ref>
			<role-name>administrator</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>guest</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>power-user</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>user</role-name>
		</security-role-ref>
	</portlet>
</portlet-app>
```

- `<portlet-class>com.helloliferay.portlet.Calc</portlet-class>`
    - Action�N���X���w��

### view.jsp

```jsp
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
```

- `<jsp:useBean id="result" class="java.lang.String" scope="request" />`
    - JSP���Ŏg�p����javaBeans�̐錾
    - id
        - �C���X�^���X������Bean�̕ϐ����Fresult
    - class
        - �g�p����Bean�̊��S�C���N���X���Fjava.lang.String
    - scope
        - �g�p����Bean�̃X�R�[�v���upage�Arequest�Asession�Aapplication�v����w��Frequest
- `action="<portlet:actionURL />"`
    - portlet.xml��<portlet-class>�^�O�Ŏw�肳�ꂽAction�N���X����������
- `<%= textbox1 %>`
    - request�X�R�[�v����utextbox1�v���L�[�ɒl���擾


----
#### �Q�l�����F
- http://docs.liferay.com/portal/6.0/definitions/liferay-plugin-package_6_0_0.dtd.html#change-log
- http://www.sk-jp.com/java/servlet/webxml.html
- http://software.fujitsu.com/jp/manual/manualfiles/M090098/B1FW5291/05Z200/B5291-f-03-03.html
- http://software.fujitsu.com/jp/manual/manualfiles/M080130/B1FW5291/04Z200/adminaf/admin236.htm
- http://struts.wasureppoi.com/jsp/06_useBean.html
