package dev.ikm.beacon.web.define.sam.details;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record SamBasicDetails(
		@JsonProperty("mnemonic") String mnemonic,
		@JsonProperty("uniqueID") UUID uniqueId,
		@JsonProperty("name") String name,
		@JsonProperty("successAlias") String successAlias,
		@JsonProperty("failureAlias") String failureAlias,
		@JsonProperty("description") String description,
		@JsonProperty("createdDateTime") String createdDateTime,
		@JsonProperty("modifiedDateTime") String modifiedDateTime,
		@JsonProperty("Source") List<Source> source) {

	public record Source(@JsonProperty("Name") String name, @JsonProperty("PIQIAllianceMemberUID") String piqiAllianceMemberUid) {}

}
