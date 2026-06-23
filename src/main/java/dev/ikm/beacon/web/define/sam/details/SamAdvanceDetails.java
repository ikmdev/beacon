package dev.ikm.beacon.web.define.sam.details;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SamAdvanceDetails(
		@JsonProperty("inputType") InputType inputType,
		@JsonProperty("parameters") List<Parameter> parameters,
		@JsonProperty("PIQIModel") @JsonInclude(JsonInclude.Include.NON_NULL) PiqiModel piqiModel,
		@JsonProperty("prerequisiteSAMMnemonic") String prerequisiteSamMnemonic,
		@JsonProperty("HDQTDimensionMnemonic") String hdqtDimensionMnemonic,
		@JsonProperty("executionTypeMnemonic") ExecutionType executionType,
		@JsonProperty("executionReference") String executionReference) {

	public enum InputType {
		SIMPLE_ATTRIBUTE("Simple_Attribute", "A simple string attribute JSON string"),
		CODEABLE_CONCEPT_ATTRIBUTE("Codeable_Concept_Attribute", "A codeable concept attribute JSON string"),
		OBSERVATION_VALUE("Observation_Value", "An observation value JSON string"),
		RANGED_VALUE("Ranged_Value", "A ranged value JSON string"),
		ELEMENT("Element", "A domain element JSON collection"),
		DATA_CLASS("Data_Class", "A domain data class, element JSON collection"),
		PATIENT("Patient", "The entire patient JSON collection");

		private final String value;
		private final String description;

		InputType(String value, String description) {
			this.value = value;
			this.description = description;
		}

		public String getValue() {
			return value;
		}

		public String getDescription() {
			return description;
		}
	}

	public record Parameter(@JsonProperty("name") String name,
							@JsonProperty("Mnemonic") String mnemonic,
							@JsonProperty("parameterValueTypeName") String parameterValueTypeName,
							@JsonProperty("isOptional") boolean isOptional,
							@JsonInclude(JsonInclude.Include.NON_NULL) @JsonProperty("description") String description,
							@JsonInclude(JsonInclude.Include.NON_NULL) @JsonProperty("parameterType") ParameterType parameterType) {
	}

	public enum ParameterType {
		CODE_SYSTEM_IDENTIFIER("Code_System_Identifier"),
		DEMOGRAPHIC_ATTRIBUTE("Demographic_Attribute"),
		CONTENT_ASSET_MNEMONIC("Content_Asset_Mnemonic"),
		SIMPLE_TEXT_PARAMETER("Simple_Text_Parameter");

		private final String displayName;

		ParameterType(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	public record PiqiModel(
			@JsonProperty("mnemonic") String mnemonic,
			@JsonProperty("version") String version,
			@JsonProperty("versionMnemonic") String versionMnemonic) {
	}

	public enum ExecutionType {
		PRIMITIVE_LOGIC("Primitive_Logic", "Hard-wired primitive logic"),
		REGEX_PATTERN("Regex_Pattern", "Regex pattern matching"),
		STORED_PROCEDURE("Stored_Procedure", "SQL stored procedure"),
		RESTFUL_SERVICE("RESTful_Service", "RESTful service call"),
		VALUE_SET_ENUM("Value_Set_Enum", "Value Set on enumerated strings"),
		VALUE_SET_CODE("Value_Set_Code", "Value set of codings");

		private final String value;
		private final String description;

		ExecutionType(String value, String description) {
			this.value = value;
			this.description = description;
		}

		public String getValue() {
			return value;
		}

		public String getDescription() {
			return description;
		}
	}

}
