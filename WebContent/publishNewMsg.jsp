<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@page import="com.sanqing.dao.EmployeeDAO"%>
<%@page import="com.sanqing.factory.EmployeeDAOFactory"%>
<%@page import="com.sanqing.bean.Employee"%>	
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>��ҵ�ճ��������ϵͳ-��������Ϣ</title>
<link href="css.css" type="text/css" rel="stylesheet" media="all" />
<link href="css/channel.css" type="text/css" rel="stylesheet" media="all" />
<script src="menu.js" type="text/javascript"></script>
</head>

<body>
<div id="topexplain">��ҵ�ճ��������ϵͳ��Ϊ��ҵ�ڲ�ͨ���ṩ����ķ���</div>
<div id="topmenu"><img src="images/banner.jpg" width="970" height="147" /></div>
<div id="bookmunu">
<div class="jsmenu" id="jsmenu">
<ul>
  <li class="normal"><a href="index.jsp" urn="jsmenu1"  rel="conmenu">��ҳ</a></li>
  <li class="normal"><a urn="jsmenu2" rel="conmenu" href="GetMessageList">��Ϣ�б�</a></li>
  <li class="active"><a urn="#default_Info" rel="conmenu" href="publishNewMsg.jsp">��������Ϣ</a></li>
  <li class="normal"><a urn="jsmenu3" rel="conmenu" href="statusRecognise.jsp">���ʶ��</a></li>
  </ul>
</div>
<div id="conmenu"></div>
<div id="place">��ǰλ�ã�[<a href="index.jsp">��ҳ</a>] - [��������Ϣ]</div>
<div id="channelcont">
<div id="channelleft">
<div class="channelinleft">
	<div id="messageBox">
		<p>
			<font color="red">${requestScope.error}</font>
		</p>
		<form action="MsgPublish" method="post">
	  <p>��Ϣ���⣺
	    <input type="text" name="title" size="50"/>
	  </p>
	  <p>��Ϣ���ݣ�</p>
	  <p>
	    <FCK:editor instanceName="content" basePath="/fckeditor" toolbarSet="myToolbar" height="400" width="750"></FCK:editor>
	  </p>
	  <p align="center">
	    <input type="submit" value="�ύ" />
	    <input type="reset" value="����" />
	  </p>
	  <ul id="permission_select" style="margin:0 auto;width:80px;height:100px;overflow:scroll;">
	  	<%
		EmployeeDAO employeeDAO = EmployeeDAOFactory.getEmployeeDAOInstance();
	  	List<Employee> e_list = employeeDAO.findAllEmployee();
		for(Employee employee : e_list) {
	%>
		<li><span><%=employee.getEmployeeName()%><%=employee.getEmployeeID()%></span><input value=<%=employee.getEmployeeID()%> name="user" type="checkbox"/></li>
	<%	
		}
	 %>
	  </ul>
	  </form>
	</div>
</div>
</div>
</div>
<div class="copyright"><ul><li></li>
<li>��ҵ�ճ��������ϵͳ &nbsp;&copy;2009-2010</li>
</ul>
</div>
<div class="end"></div>
<script type=text/javascript>
startajaxtabs("jsmenu");

var iTab=GetCookie("nets_jsmenu");
	iTab = iTab ? parseInt(iTab):parseInt(Math.random()*5);
	if(iTab!=0) getElement("jsmenu").getElementsByTagName("h1")[iTab].LoadTab();
	iTab++;
	if(iTab>4) iTab=0;
	SetCookie("nets_jsmenu",iTab,365);
function getElement(aID)
{
  return (document.getElementById) ? document.getElementById(aID)
                                   : document.all[aID];
}

/* //ajax����
function getXmlhttp()
{
	var http_request;
	
	if(window.XMLHttpRequest) { 
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {
			http_request.overrideMimeType("text/xml");
		}
	}
	else if (window.ActiveXObject) { 
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	if (!http_request) { 
		window.alert("can't create XMLHttpRequest object.");
		return null;
	}	
	return http_request;
}

function loadAJAXTab(url,method){
	var content=null;
	if(url==null) {
		var xhttp=getXmlhttp();		
			xhttp.onreadystatechange=function(){
				if(xhttp.readyState == 4 && (xhttp.status==200 || window.location.href.indexOf("http")==-1))
				{					
					content=xhttp.responseText;
				}
			}
		xhttp.open(method,url,true);
		xhttp.send(null);
	}
	return content;
}
 */
//��ȡ������Ա��Ϣ
function getEmployee(){
	var content = loadAJAXTab("","POST");
	setPermissionCheck(content);
}

//����Ȩ�޸�ѡ��
function setPermissionCheck(data){
	var ul = document.getElementById("permission_select");
	var i,li;
	for(i=1;i<data.length;i++){
		li+="<li><span>"+data.employeeName+"</span><input "+
		" value="+data.employeeID+" name='user' type='checkbox'/></li>"
		ul.append("<li><span></span><input value="1" name="user" type="checkbox"/></li>");
	}
}

//����DOM
getEmployee();

</script>
</body>
</html>



