package dev.ikm.beacon.web.define.sam;

import dev.ikm.beacon.web.define.sam.details.SamAdvanceDetails;
import dev.ikm.beacon.web.define.sam.details.SamBasicDetails;
import dev.ikm.beacon.web.define.sam.details.SamRuntimeDetails;

public record SamRecord(
		SamBasicDetails samBasicDetails,
		SamAdvanceDetails samAdvanceDetails,
		SamRuntimeDetails samRuntimeDetails) {

}