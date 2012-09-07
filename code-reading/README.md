# Liferay Portalのコードリーディングのポイントをまとめました

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
    - uriでどのカスタムタグを使用するかを設定
        - `"http://java.sun.com/portlet_2_0"`はjavaの標準セット
    - prefixで接頭語を設定
        - `<portlet:～>`で利用可能になる
- `<portlet:defineObjects />`
    - portlet_2_0.tldで指定されたクラス「org.apache.pluto.tags.DefineObjectsTag286」のdefineObjectsメソッドが実行される
        - ポートレットに必要な初期設定を行う

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

- Liferayがモジュール（warファイル単位）を管理するためのプロパティを記述したファイル

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
    - ポートレットのカテゴリを設定：「サンプル」
- `<portlet id="hello-liferay" />`
    - ポートレットIDを設定：「hello-liferay」

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
    - cssファイルに「`.hello-liferay-portlet{...}`」と書くことにより、そのスタイルを適用できる
- `<role-mapper>～</role-mapper>`
    - ロールの定義

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
    - JSPで指定できるActionクラスを設定
        - `<form action="<portlet:actionURL />" method="post">...</form>`とやるとここで指定したActionクラスが呼ばれる
- `<init-param>～</init-param>`
    - `<name>view-template</name>`または`<name>view-jsp</name>`で指定したjspがデフォルトで表示される
    - `<init-param>`は、利用したいjspの分だけ追加できる

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
    - `"http://liferay.com/tld/theme"`はliferayの標準セット
- `<liferay-theme:defineObjects />`
    - liferay-theme.tldで指定されたクラス「com.liferay.taglib.theme.DefineObjectsTag」のDefineObjectsメソッドが実行される
    - DefineObjectsメソッドでは、JSPのpageContextにログインユーザのインスタンスを「user」としてsetAttributeする
        - `pageContext.setAttribute("user", themeDisplay.getUser());`
- `Hello, <%= user.getScreenName() %>!<br>`
    - `<%= user.getScreenName() %>`でpageContextにsetAttributeされたuser（ログインユーザのインスタンス）から、ログインユーザ名を取得する

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

- 展開先は、「WEB-INF/classes/com/helloliferay/portlet/Calc.class」
- doViewメソッド
    - ポートレットの画面表示の際に自動的に実行されるメソッド
    - `String result = request.getParameter("result");`
        - RenderRequestから「result」というパラメータを取得
        - RenderRequestは、画面表示のためにportletに送られたリクエストを表す
    - `PortletRequestDispatcher rd = context.getRequestDispatcher("/view.jsp");`
        - renderの中でPortletRequestDispatcherを取得することにより、PortletからServletやJSPをincludeすることができる
    - `rd.include(request, response);`
        - view.jspが呼び出される
- processActionメソッド
    - portlet.xmlで指定されたActionURLによるAction Requestがトリガとなって呼び出される。通常、Form入力などのアクションに対して、processActionメソッドが処理されると、続いて同じPortletのrenderメソッドがPortlet Containerにより呼び出され、processActionメソッドの処理結果に応じたビューを生成する。
    - processActionメソッドとrenderメソッド間でデータを受け渡すためには、Renderパラメータを利用する。processActionメソッドの引数であるActionResponseオブジェクトには、setRenderParameterというメソッドがあり、このメソッドを用いて、Render Requestへパラメータを渡すことができる。また、Sessionスコープを利用することも可能。
    - `String XXXX = request.getParameter("xxxx");`
        - ActionRequestから「xxxx」というパラメータを取得
    - `response.setRenderParameter("xxxx",XXXX);`
        - ActionResponseの画面表示用パラメータ「xxxx」に「XXXX」をセット
    - `if(check(text1) && check(text2)){～`
        - null値、数値チェック
        - OKならActionResponseの画面表示用パラメータ「result」に計算結果をセット
- checkメソッド
    - `if(str == null){～`
        - null値チェック
        - nullならfalseを返す
    - `try {～`
        - 数値チェック
        - Double型に変換できない＝数値でないなら、falseを返す
    - それ以外ならtrueを返す

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
    - Actionクラスを指定

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
    - JSP内で使用するjavaBeansの宣言
    - id
        - インスタンス化するBeanの変数名：result
    - class
        - 使用するBeanの完全修飾クラス名：java.lang.String
    - scope
        - 使用するBeanのスコープを「page、request、session、application」から指定：request
- `action="<portlet:actionURL />"`
    - portlet.xmlの<portlet-class>タグで指定されたActionクラスが代入される
- `<%= textbox1 %>`
    - requestスコープから「textbox1」をキーに値を取得


----
#### 参考資料：
- http://docs.liferay.com/portal/6.0/definitions/liferay-plugin-package_6_0_0.dtd.html#change-log
- http://www.sk-jp.com/java/servlet/webxml.html
- http://software.fujitsu.com/jp/manual/manualfiles/M090098/B1FW5291/05Z200/B5291-f-03-03.html
- http://software.fujitsu.com/jp/manual/manualfiles/M080130/B1FW5291/04Z200/adminaf/admin236.htm
- http://struts.wasureppoi.com/jsp/06_useBean.html
