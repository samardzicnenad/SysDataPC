<%--
/**********************************************************************
 * Created by : mostly automatically with some modifications by Nenad Samardzic
 * Date       : 07/15/2013
 * Description: The class represents view for network utilization data
 *				The original list.gsp was altered in sense that:
 *					- all of non-read-only actions were disabled
 *					- chart view was added
 * 					- select tag was added
 *					- parameters were passed to the controller class
 **********************************************************************/
 --%>
<%@ page import="datareporter.NETutil" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'NETutil.label', default: 'NETutil')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-NETutil" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<%--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
			</ul>
		</div>

		<g:form name="selectInterval" controller="NETutil" action="list">
			<label>&nbsp;Choose the interval</label>
			<g:select name='interval' from="${['Minute', 'Hour', 'Day']}" onchange="submit()" value="${params.interval}"/>
		</g:form>
		<div id="netData">
            <img src="${createLink(controller:'NETutil', action:'returnNetData')}?interval=${params.interval}"
              alt="network utilization chart" /> 
        </div>

		<div id="list-NETutil" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="tsTime" title="${message(code: 'NETutil.tsTime.label', default: 'Time')}" />
						<g:sortableColumn property="nInboundTCP" title="${message(code: 'NETutil.nInboundTCP.label', default: 'InboundTCP')}" />
						<g:sortableColumn property="nOutboundTCP" title="${message(code: 'NETutil.nOutboundTCP.label', default: 'OutboundTCP')}" />
						<g:sortableColumn property="nInboundAll" title="${message(code: 'NETutil.nInboundAll.label', default: 'InboundAll')}" />
						<g:sortableColumn property="nOutboundAll" title="${message(code: 'NETutil.nOutboundAll.label', default: 'OutboundAll')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${NETutilInstanceList}" status="i" var="NETutilInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${NETutilInstance.id}">${fieldValue(bean: NETutilInstance, field: "tsTime")}</g:link></td>
						<td>${fieldValue(bean: NETutilInstance, field: "nInboundTCP")}</td>
						<td>${fieldValue(bean: NETutilInstance, field: "nOutboundTCP")}</td>
						<td>${fieldValue(bean: NETutilInstance, field: "nInboundAll")}</td>
						<td>${fieldValue(bean: NETutilInstance, field: "nOutboundAll")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${NETutilInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
