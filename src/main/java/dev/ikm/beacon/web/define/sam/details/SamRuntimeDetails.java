package dev.ikm.beacon.web.define.sam.details;

public record SamRuntimeDetails(
		Type type,
		ExecutionDetail executionDetail) {

	public enum Type { BUILT_IN, CUSTOM }

	public sealed interface ExecutionDetail permits GroovyScript, SpringBean {
	}

	public record SpringBean(String beanName) implements ExecutionDetail {
	}

	public record GroovyScript(String script) implements ExecutionDetail {
	}

}
