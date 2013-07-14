
<%@ page import="datareporter.NETutil" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'NETutil.label', default: 'NETutil')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-NETutil" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<%--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
			</ul>
		</div>
		<div id="show-NETutil" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list NETutil">
			
				<g:if test="${NETutilInstance?.tsTime}">
				<li class="fieldcontain">
					<span id="tsTime-label" class="property-label"><g:message code="NETutil.tsTime.label" default="Ts Time" /></span>
					
						<span class="property-value" aria-labelledby="tsTime-label"><g:formatDate date="${NETutilInstance?.tsTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${NETutilInstance?.nInboundTCP}">
				<li class="fieldcontain">
					<span id="nInboundTCP-label" class="property-label"><g:message code="NETutil.nInboundTCP.label" default="NI nbound TCP" /></span>
					
						<span class="property-value" aria-labelledby="nInboundTCP-label"><g:fieldValue bean="${NETutilInstance}" field="nInboundTCP"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${NETutilInstance?.nOutboundTCP}">
				<li class="fieldcontain">
					<span id="nOutboundTCP-label" class="property-label"><g:message code="NETutil.nOutboundTCP.label" default="NO utbound TCP" /></span>
					
						<span class="property-value" aria-labelledby="nOutboundTCP-label"><g:fieldValue bean="${NETutilInstance}" field="nOutboundTCP"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${NETutilInstance?.nInboundAll}">
				<li class="fieldcontain">
					<span id="nInboundAll-label" class="property-label"><g:message code="NETutil.nInboundAll.label" default="NI nbound All" /></span>
					
						<span class="property-value" aria-labelledby="nInboundAll-label"><g:fieldValue bean="${NETutilInstance}" field="nInboundAll"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${NETutilInstance?.nOutboundAll}">
				<li class="fieldcontain">
					<span id="nOutboundAll-label" class="property-label"><g:message code="NETutil.nOutboundAll.label" default="NO utbound All" /></span>
					
						<span class="property-value" aria-labelledby="nOutboundAll-label"><g:fieldValue bean="${NETutilInstance}" field="nOutboundAll"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${NETutilInstance?.id}" />
					<%--<g:link class="edit" action="edit" id="${NETutilInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />--%>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
