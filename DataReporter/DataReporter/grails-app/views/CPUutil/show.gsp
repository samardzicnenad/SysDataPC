
<%@ page import="datareporter.CPUutil" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'CPUutil.label', default: 'CPUutil')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-CPUutil" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<%--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
			</ul>
		</div>
		<div id="show-CPUutil" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list CPUutil">
			
				<g:if test="${CPUutilInstance?.tsTime}">
				<li class="fieldcontain">
					<span id="tsTime-label" class="property-label"><g:message code="CPUutil.tsTime.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="tsTime-label"><g:formatDate date="${CPUutilInstance?.tsTime}" /></span>
				</li>
				</g:if>
			
				<g:if test="${CPUutilInstance?.dUser}">
				<li class="fieldcontain">
					<span id="dUser-label" class="property-label"><g:message code="CPUutil.dUser.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="dUser-label"><g:fieldValue bean="${CPUutilInstance}" field="dUser"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${CPUutilInstance?.dSystem}">
				<li class="fieldcontain">
					<span id="dSystem-label" class="property-label"><g:message code="CPUutil.dSystem.label" default="System" /></span>
					
						<span class="property-value" aria-labelledby="dSystem-label"><g:fieldValue bean="${CPUutilInstance}" field="dSystem"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${CPUutilInstance?.dNice}">
				<li class="fieldcontain">
					<span id="dNice-label" class="property-label"><g:message code="CPUutil.dNice.label" default="Nice" /></span>
					
						<span class="property-value" aria-labelledby="dNice-label"><g:fieldValue bean="${CPUutilInstance}" field="dNice"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${CPUutilInstance?.dIdle}">
				<li class="fieldcontain">
					<span id="dIdle-label" class="property-label"><g:message code="CPUutil.dIdle.label" default="Idle" /></span>
					
						<span class="property-value" aria-labelledby="dIdle-label"><g:fieldValue bean="${CPUutilInstance}" field="dIdle"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${CPUutilInstance?.dWait}">
				<li class="fieldcontain">
					<span id="dWait-label" class="property-label"><g:message code="CPUutil.dWait.label" default="Wait" /></span>
					
						<span class="property-value" aria-labelledby="dWait-label"><g:fieldValue bean="${CPUutilInstance}" field="dWait"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${CPUutilInstance?.dIrq}">
				<li class="fieldcontain">
					<span id="dIrq-label" class="property-label"><g:message code="CPUutil.dIrq.label" default="Irq" /></span>
					
						<span class="property-value" aria-labelledby="dIrq-label"><g:fieldValue bean="${CPUutilInstance}" field="dIrq"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${CPUutilInstance?.dSoftIrq}">
				<li class="fieldcontain">
					<span id="dSoftIrq-label" class="property-label"><g:message code="CPUutil.dSoftIrq.label" default="Soft Irq" /></span>
					
						<span class="property-value" aria-labelledby="dSoftIrq-label"><g:fieldValue bean="${CPUutilInstance}" field="dSoftIrq"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${CPUutilInstance?.dStolen}">
				<li class="fieldcontain">
					<span id="dStolen-label" class="property-label"><g:message code="CPUutil.dStolen.label" default="Stolen" /></span>
					
						<span class="property-value" aria-labelledby="dStolen-label"><g:fieldValue bean="${CPUutilInstance}" field="dStolen"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${CPUutilInstance?.dCombined}">
				<li class="fieldcontain">
					<span id="dCombined-label" class="property-label"><g:message code="CPUutil.dCombined.label" default="Combined" /></span>
					
						<span class="property-value" aria-labelledby="dCombined-label"><g:fieldValue bean="${CPUutilInstance}" field="dCombined"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${CPUutilInstance?.id}" />
					<%--<g:link class="edit" action="edit" id="${CPUutilInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />--%>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
