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
        - "http://java.sun.com/portlet_2_0"��java�̕W���Z�b�g
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
- `<category name="category.sample">`
    - �|�[�g���b�g�̃J�e�S����ݒ�F�u�T���v���v
- `<portlet id="hello-liferay" />`
    - �|�[�g���b�gID��ݒ�F�uhello-liferay�v

### WEB-INF/liferay-portlet.xml
- `<css-class-wrapper>hello-liferay-portlet</css-class-wrapper>`
    - css�t�@�C���Ɂu`.hello-liferay-portlet{...}`�v�Ə������Ƃɂ��A���̃X�^�C����K�p�ł���
- `<role-mapper>�`</role-mapper>`
    - ���[���̒�`

### WEB-INF/portlet.xml
- `<portlet-class>com.liferay.util.bridges.mvc.MVCPortlet</portlet-class>`
    - JSP�Ŏw��ł���Action�N���X��ݒ�
        - `<form action="<portlet:actionURL />" method="post">...</form>`�Ƃ��Ƃ����Ŏw�肵��Action�N���X���Ă΂��
- `<init-param>�`</init-param>`
    - `<name>view-template</name>`�܂���`<name>view-jsp</name>`�Ŏw�肵��jsp���f�t�H���g�ŕ\�������
    - `<init-param>`�́A���p������jsp�̕������ǉ��ł���

----
## hello-liferay-portlet2

### view.jsp
- `<%@ taglib uri="[[http://liferay.com/tld/theme>liferay-theme.tld]]" prefix="liferay-theme" %>`
    - "http://liferay.com/tld/theme"��liferay�̕W���Z�b�g
- `<liferay-theme:defineObjects />`
    - liferay-theme.tld�Ŏw�肳�ꂽ�N���X�ucom.liferay.taglib.theme.DefineObjectsTag�v��DefineObjects���\�b�h�����s�����
    - DefineObjects���\�b�h�ł́AJSP��pageContext�Ƀ��O�C�����[�U�̃C���X�^���X���uuser�v�Ƃ���setAttribute����
        - `pageContext.setAttribute("user", themeDisplay.getUser());`
- `Hello, <%= user.getScreenName() %>!<br>`
    - `<%= user.getScreenName() %>`��pageContext��setAttribute���ꂽuser�i���O�C�����[�U�̃C���X�^���X�j����A���O�C�����[�U�����擾����

----
## calc-portlet

### WEB-INF/src/Calc.java>Calc.java
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
- `<portlet-class>com.helloliferay.portlet.Calc</portlet-class>`
    - Action�N���X���w��

### view.jsp
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
