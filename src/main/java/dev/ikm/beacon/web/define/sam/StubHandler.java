package dev.ikm.beacon.web.define.sam;

import dev.ikm.beacon.web.define.sam.details.SamAdvanceDetails;
import dev.ikm.beacon.web.define.sam.details.SamBasicDetails;
import dev.ikm.beacon.web.define.sam.details.SamRuntimeDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StubHandler {

    public static List<SamRecord> createStubSamRecords() {
        return List.of(
                createBuiltInStubSam(),
                createCustomStubSam()
        );
    }

    private static SamRecord createBuiltInStubSam() {
        SamBasicDetails basic = new SamBasicDetails(
                "Attr_IsPopulated",
                UUID.fromString("5ea00021-24ce-40c2-a1f7-02875b7732f0"),
                "Attribute is Populated",
                "Populated",
                "Not Populated",
                "Determines if a simple attribute has a value.",
                "2023-01-01T12:00:00Z",
                "2023-01-01T12:00:00Z",
                List.of(new SamBasicDetails.Source(
                        "PIQI Alliance",
                        "f0f3de6d-c3d8-483c-a8ff-004a66dbc551"))
        );

        SamAdvanceDetails advanced = new SamAdvanceDetails(
                SamAdvanceDetails.InputType.SIMPLE_ATTRIBUTE,
                new ArrayList<>(),
                null,
                "Accuracy.Completeness",
                "Accuracy.Completeness",
                SamAdvanceDetails.ExecutionType.PRIMITIVE_LOGIC,
                "exists()"
        );

        SamRuntimeDetails runtime = new SamRuntimeDetails(
                SamRuntimeDetails.Type.BUILT_IN,
                new SamRuntimeDetails.SpringBean("attrIsPopulatedSam")
        );

        return new SamRecord(basic, advanced, runtime);
    }

    private static SamRecord createCustomStubSam() {
        SamBasicDetails basic = new SamBasicDetails(
                "Age_IsInRange",
                UUID.randomUUID(),
                "Patient Age is Within Range",
                "In Range",
                "Out of Range",
                "Checks if the patient's age is between a min and max value.",
                "2024-05-10T09:00:00Z",
                "2024-05-10T09:00:00Z",
                List.of(new SamBasicDetails.Source(
                        "Local Hospital System",
                        UUID.randomUUID().toString()))
        );

        SamAdvanceDetails advanced = new SamAdvanceDetails(
                SamAdvanceDetails.InputType.DATA_CLASS,
                new ArrayList<>(List.of(
                        new SamAdvanceDetails.Parameter(
                                "minAge",
                                "MIN_AGE",
                                "Integer",
                                false,
                                "The minimum age (inclusive).",
                                SamAdvanceDetails.ParameterType.SIMPLE_TEXT_PARAMETER
                        ),
                        new SamAdvanceDetails.Parameter(
                                "maxAge",
                                "MAX_AGE",
                                "Integer",
                                false,
                                "The maximum age (inclusive).",
                                SamAdvanceDetails.ParameterType.SIMPLE_TEXT_PARAMETER
                        )
                )),
                new SamAdvanceDetails.PiqiModel("Patient", "1.0", "Patient_v1"),
                "Attr_IsPopulated",
                "Accuracy.Value",
                SamAdvanceDetails.ExecutionType.RESTFUL_SERVICE,
                "age >= minAge && age <= maxAge"
        );

        SamRuntimeDetails runtime = new SamRuntimeDetails(
                SamRuntimeDetails.Type.CUSTOM,
                new SamRuntimeDetails.GroovyScript("return age >= minAge && age <= maxAge")
        );

        return new SamRecord(basic, advanced, runtime);
    }
}