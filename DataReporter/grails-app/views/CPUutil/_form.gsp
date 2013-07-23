<%@ page import="datareporter.CPUutil" %>



<div class="fieldcontain ${hasErrors(bean: CPUutilInstance, field: 'tsTime', 'error')} required">
	<label for="tsTime">
		<g:message code="CPUutil.tsTime.label" default="Ts Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="tsTime" precision="day"  value="${CPUutilInstance?.tsTime}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: CPUutilInstance, field: 'dUser', 'error')} required">
	<label for="dUser">
		<g:message code="CPUutil.dUser.label" default="DU ser" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="dUser" value="${fieldValue(bean: CPUutilInstance, field: 'dUser')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: CPUutilInstance, field: 'dSystem', 'error')} required">
	<label for="dSystem">
		<g:message code="CPUutil.dSystem.label" default="DS ystem" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="dSystem" value="${fieldValue(bean: CPUutilInstance, field: 'dSystem')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: CPUutilInstance, field: 'dNice', 'error')} required">
	<label for="dNice">
		<g:message code="CPUutil.dNice.label" default="DN ice" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="dNice" value="${fieldValue(bean: CPUutilInstance, field: 'dNice')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: CPUutilInstance, field: 'dIdle', 'error')} required">
	<label for="dIdle">
		<g:message code="CPUutil.dIdle.label" default="DI dle" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="dIdle" value="${fieldValue(bean: CPUutilInstance, field: 'dIdle')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: CPUutilInstance, field: 'dWait', 'error')} required">
	<label for="dWait">
		<g:message code="CPUutil.dWait.label" default="DW ait" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="dWait" value="${fieldValue(bean: CPUutilInstance, field: 'dWait')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: CPUutilInstance, field: 'dIrq', 'error')} required">
	<label for="dIrq">
		<g:message code="CPUutil.dIrq.label" default="DI rq" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="dIrq" value="${fieldValue(bean: CPUutilInstance, field: 'dIrq')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: CPUutilInstance, field: 'dSoftIrq', 'error')} required">
	<label for="dSoftIrq">
		<g:message code="CPUutil.dSoftIrq.label" default="DS oft Irq" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="dSoftIrq" value="${fieldValue(bean: CPUutilInstance, field: 'dSoftIrq')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: CPUutilInstance, field: 'dStolen', 'error')} required">
	<label for="dStolen">
		<g:message code="CPUutil.dStolen.label" default="DS tolen" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="dStolen" value="${fieldValue(bean: CPUutilInstance, field: 'dStolen')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: CPUutilInstance, field: 'dCombined', 'error')} required">
	<label for="dCombined">
		<g:message code="CPUutil.dCombined.label" default="DC ombined" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="dCombined" value="${fieldValue(bean: CPUutilInstance, field: 'dCombined')}" required=""/>
</div>

