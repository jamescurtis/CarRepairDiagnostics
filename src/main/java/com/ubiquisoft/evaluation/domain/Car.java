package com.ubiquisoft.evaluation.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

	private String year;
	private String make;
	private String model;

	private List<Part> parts;

	public Map<PartType, Integer> getMissingPartsMap() {
		int tires = 0;
		int engine = 0;
		int electrical = 0;
		int fuelFilter = 0;
		int oilFilter = 0;
		Map<PartType, Integer> missingParts = new HashMap<>();
		for (Part part : parts) {
			switch (part.getType()) {
				case ELECTRICAL:
					electrical++;
					break;
				case TIRE:
					tires++;
					break;
				case ENGINE:
					engine++;
					break;
				case FUEL_FILTER:
					fuelFilter++;
					break;
				case OIL_FILTER:
					oilFilter++;
					break;
			}
		}

		if (tires < 4) {
			missingParts.put(PartType.TIRE, 4 - tires);
		} else if (tires > 4) throw new IllegalArgumentException("To many tires");

		if (engine < 1) {
			missingParts.put(PartType.ENGINE, 1);
		} else if (engine > 1) throw new IllegalArgumentException("Too many engines.");

		if (electrical < 1) {
			missingParts.put(PartType.ELECTRICAL, 1);
		} else if (electrical > 1) throw new IllegalArgumentException("Too many electrical parts.");

		if (fuelFilter < 1) {
			missingParts.put(PartType.FUEL_FILTER, 1);
		} else if (fuelFilter > 1) throw new IllegalArgumentException("Too many fuel filters.");

		if (oilFilter < 1) {
			missingParts.put(PartType.OIL_FILTER, 1);
		} else if (oilFilter > 1) throw new IllegalArgumentException("Too many oil filters.");


		return missingParts;
	}

	public List<String> getMissingCarFields() {
		List<String> missingFields = new ArrayList<>();
		if (year == null || year.isEmpty()) missingFields.add("year");
		if (make == null || make.isEmpty()) missingFields.add("make");
		if (model == null || model.isEmpty()) missingFields.add("model");
		return missingFields;
	}

	@Override
	public String toString() {
		return "Car{" +
				       "year='" + year + '\'' +
				       ", make='" + make + '\'' +
				       ", model='" + model + '\'' +
				       ", parts=" + parts +
				       '}';
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters *///region
	/* --------------------------------------------------------------------------------------------------------------- */

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	/* --------------------------------------------------------------------------------------------------------------- */
	/*  Getters and Setters End *///endregion
	/* --------------------------------------------------------------------------------------------------------------- */

}
