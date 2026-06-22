package dev.ikm.beacon.define;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SamRecord(
    String mnemonic,
    SamType samType,
    @JsonProperty("uniqueID") String uniqueId,
    String name,
    String successAlias,
    String failureAlias,
    String description,
    InputType inputType,
    List<Parameter> parameters,
    @JsonInclude(JsonInclude.Include.NON_NULL) PiqiModel piqiModel, // Optional, so can be null
    String prerequisiteSAMMnemonic,
    @JsonProperty("HDQTDimensionMnemonic") String hdqtDimensionMnemonic,
    ExecutionType executionType,
    String executionReference,
    String createdDateTime,
    String modifiedDateTime,
    @JsonProperty("Source") List<Source> source
) {
    public enum SamType { BUILT_IN, CUSTOM }

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
        InputType(String value, String description) { this.value = value; this.description = description; }
        public String getValue() { return value; }
        public String getDescription() { return description; }
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
        ExecutionType(String value, String description) { this.value = value; this.description = description; }
        public String getValue() { return value; }
        public String getDescription() { return description; }
    }

    public record PiqiModel(String modelMnemonic, String modelVersion, String versionMnemonic) {}

    public record Source(@JsonProperty("Name") String name, @JsonProperty("PIQIAllianceMemberUID") String piqiAllianceMemberUid) {}

    public record Parameter(String name, String description, ParameterType type) {}

    public enum ParameterType {
        CODE_SYSTEM_IDENTIFIER("Code_System_Identifier"),
        DEMOGRAPHIC_ATTRIBUTE("Demographic_Attribute"),
        CONTENT_ASSET_MNEMONIC("Content_Asset_Mnemonic"),
        SIMPLE_TEXT_PARAMETER("Simple_Text_Parameter");

        private final String displayName;
        ParameterType(String displayName) { this.displayName = displayName; }
        public String getDisplayName() { return displayName; }
    }
}