package dev.ikm.beacon.web.define.sam;

import dev.ikm.beacon.web.define.sam.details.SamAdvanceDetails;
import dev.ikm.beacon.web.define.sam.details.SamBasicDetails;
import dev.ikm.beacon.web.define.sam.details.SamRuntimeDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;



@Service
public class SamService {

	private final List<SamRecord> samRecords;

	public SamService() {
		this.samRecords = new ArrayList<>();
		samRecords.addAll(StubHandler.createStubSamRecords());
	}

	public List<SamRecord> findAll() {
		return List.copyOf(samRecords);
	}

	public Optional<SamRecord> findById(UUID id) {
		return samRecords.stream().filter(s -> s.samBasicDetails().uniqueId().equals(id)).findFirst();
	}

	public SamRecord create(SamRecord samRecord) {
		Objects.requireNonNull(samRecord, "samRecord must not be null");
		UUID id = Objects.requireNonNull(
				samRecord.samBasicDetails().uniqueId(),
				"samRecord.samBasicDetails.uniqueId must not be null"
		);

		// Upsert: replace existing by id, else append
		for (int i = 0; i < samRecords.size(); i++) {
			SamRecord existing = samRecords.get(i);
			if (existing.samBasicDetails().uniqueId().equals(id)) {
				samRecords.set(i, samRecord);
				return samRecord;
			}
		}

		// If we reach here, it's a completely new SAM
		samRecords.add(samRecord);
		return samRecord;
	}

	public SamRecord createEmptySamRecord() {
		SamBasicDetails basic = new SamBasicDetails(
				null,
				UUID.randomUUID(),
				null, null, null, null, null, null,
				new ArrayList<>()
		);

		SamAdvanceDetails advance = new SamAdvanceDetails(
				null,
				new ArrayList<>(),
				null, null, null, null, null
		);

		SamRuntimeDetails runtime = new SamRuntimeDetails(
				null,
				null
		);

		return new SamRecord(basic, advance, runtime);
	}

	public SamRecord update(SamRecord samRecord) {
		Objects.requireNonNull(samRecord, "samRecord must not be null");
		UUID id = Objects.requireNonNull(
				samRecord.samBasicDetails().uniqueId(),
				"samRecord.samBasicDetails.uniqueId must not be null"
		);

		samRecords.removeIf(s -> s.samBasicDetails().uniqueId().equals(id));
		samRecords.add(samRecord);

		return samRecord;
	}

	public boolean existsById(UUID id) {
		return samRecords.stream().anyMatch(s -> s.samBasicDetails().uniqueId().equals(id));
	}

	public boolean deleteById(UUID id) {
		return samRecords.removeIf(s -> s.samBasicDetails().uniqueId().equals(id));
	}

	public List<SamAdvanceDetails.Parameter> findParametersById(UUID id) {
		Optional<SamRecord> samRecord = findById(id);
		if (samRecord.isEmpty()) {
			return List.of();
		}
		return samRecord.get().samAdvanceDetails().parameters();
	}

	public void updateParameters(UUID id, List<SamAdvanceDetails.Parameter> parameters) {
		Optional<SamRecord> samRecord = findById(id);
		samRecord.ifPresent(record -> {
			record.samAdvanceDetails().parameters().clear();
			record.samAdvanceDetails().parameters().addAll(parameters);
		});
	}

	public void updateSources(UUID id, List<SamBasicDetails.Source> sources) {
		Optional<SamRecord> samRecord = findById(id);
		samRecord.ifPresent(record -> {
			record.samBasicDetails().source().clear();
			record.samBasicDetails().source().addAll(sources);
		});
	}

}
