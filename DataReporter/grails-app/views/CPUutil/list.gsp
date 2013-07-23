<%--
/**********************************************************************
 * Created by : mostly automatically with some modifications by Nenad Samardzic
 * Date       : 07/15/2013
 * Description: The class represents view for CPU utilization data
 *				The original list.gsp was altered in sense that:
 *					- all of non-read-only actions were disabled
 *					- chart view was added
 * 					- select tag was added
 *					- parameters were passed to the controller class
 **********************************************************************/
 --%>
<%@ page import="datareporter.CPUutil" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'CPUutil.label', default: 'CPUutil')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-CPUutil" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<%--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
			</ul>
		</div>
		
		<g:form name="selectInterval" controller="CPUutil" action="list">
			<label>&nbsp;Choose the interval</label>
			<g:select name='interval' from="${['Minute', 'Hour', 'Day']}" onchange="submit()" value="${params.interval}"/>
		</g:form>
		<div id="cpuData">
            <img src="${createLink(controller:'CPUutil', action:'returnCPUData')}?interval=${params.interval}"
              alt="CPU utilization chart" />
        </div>
        
		<div id="list-CPUutil" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="tsTime" title="${message(code: 'CPUutil.tsTime.label', default: 'Time')}" />
						<g:sortableColumn property="dUser" title="${message(code: 'CPUutil.dUser.label', default: 'User')}" />
						<g:sortableColumn property="dSystem" title="${message(code: 'CPUutil.dSystem.label', default: 'System')}" />
						<g:sortableColumn property="dNice" title="${message(code: 'CPUutil.dNice.label', default: 'Nice')}" />
						<g:sortableColumn property="dIdle" title="${message(code: 'CPUutil.dIdle.label', default: 'Idle')}" />
						<g:sortableColumn property="dWait" title="${message(code: 'CPUutil.dWait.label', default: 'Wait')}" />
						<g:sortableColumn property="dIrq" title="${message(code: 'CPUutil.dIrq.label', default: 'Irq')}" />
						<g:sortableColumn property="dSoftIrq" title="${message(code: 'CPUutil.dSoftIrq.label', default: 'SoftIrq')}" />
						<g:sortableColumn property="dStolen" title="${message(code: 'CPUutil.dStolen.label', default: 'Stolen')}" />
						<g:sortableColumn property="dCombined" title="${message(code: 'CPUutil.dCombined.label', default: 'Combined')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${CPUutilInstanceList}" status="i" var="CPUutilInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${CPUutilInstance.id}">${fieldValue(bean: CPUutilInstance, field: "tsTime")}</g:link></td>
						<td>${fieldValue(bean: CPUutilInstance, field: "dUser")}</td>
						<td>${fieldValue(bean: CPUutilInstance, field: "dSystem")}</td>
						<td>${fieldValue(bean: CPUutilInstance, field: "dNice")}</td>
						<td>${fieldValue(bean: CPUutilInstance, field: "dIdle")}</td>
						<td>${fieldValue(bean: CPUutilInstance, field: "dWait")}</td>
						<td>${fieldValue(bean: CPUutilInstance, field: "dIrq")}</td>
						<td>${fieldValue(bean: CPUutilInstance, field: "dSoftIrq")}</td>
						<td>${fieldValue(bean: CPUutilInstance, field: "dStolen")}</td>
						<td>${fieldValue(bean: CPUutilInstance, field: "dCombined")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${CPUutilInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
