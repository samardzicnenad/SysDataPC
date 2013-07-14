<%@ page import="datareporter.NETutil" %>



<div class="fieldcontain ${hasErrors(bean: NETutilInstance, field: 'tsTime', 'error')} required">
	<label for="tsTime">
		<g:message code="NETutil.tsTime.label" default="Ts Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="tsTime" precision="day"  value="${NETutilInstance?.tsTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: NETutilInstance, field: 'nInboundTCP', 'error')} required">
	<label for="nInboundTCP">
		<g:message code="NETutil.nInboundTCP.label" default="NI nbound TCP" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nInboundTCP" type="number" value="${NETutilInstance.nInboundTCP}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: NETutilInstance, field: 'nOutboundTCP', 'error')} required">
	<label for="nOutboundTCP">
		<g:message code="NETutil.nOutboundTCP.label" default="NO utbound TCP" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nOutboundTCP" type="number" value="${NETutilInstance.nOutboundTCP}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: NETutilInstance, field: 'nInboundAll', 'error')} required">
	<label for="nInboundAll">
		<g:message code="NETutil.nInboundAll.label" default="NI nbound All" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nInboundAll" type="number" value="${NETutilInstance.nInboundAll}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: NETutilInstance, field: 'nOutboundAll', 'error')} required">
	<label for="nOutboundAll">
		<g:message code="NETutil.nOutboundAll.label" default="NO utbound All" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nOutboundAll" type="number" value="${NETutilInstance.nOutboundAll}" required=""/>
</div>

