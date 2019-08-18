package com.ubiquisoft.evaluation.domain;

import com.ubiquisoft.evaluation.utility.RequirementsBuilder;

import javax.xml.bind.JAXBException;
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

	public Map<PartType, Integer> getMissingPartsMap() throws JAXBException {

		Map<PartType, Integer> missingParts = RequirementsBuilder.build().getRequirementsMap();
		for (Part part : this.getParts()) {
			if (missingParts.containsKey(part.getType())) {
				if (missingParts.get(part.getType()) == 1) {
					missingParts.remove(part.getType());
				} else if (missingParts.get(part.getType()) > 1) {
					missingParts.put(part.getType(), missingParts.get(part.getType()) - 1);
				}
			} else {
				System.err.println("Unknown part found: "
						+ part.toString()
						+ ". Car has to many parts of the same type or part type is unknown"
				);
			}

		}

		return missingParts;
	}

	public List<String> getMissingCarFields() {
		List<String> missingFields = new ArrayList<>();
		if (year == null || year.isEmpty()) missingFields.add("year");
		if (make == null || make.isEmpty()) missingFields.add("make");
		if (model == null || model.isEmpty()) missingFields.add("model");
		return missingFields;
	}


	public Map<PartType, ConditionType> getNonWorkingParts() {
		Map<PartType, ConditionType> nonWorkingParts = new HashMap<>();
		for (Part part : this.getParts()) {
			if (!part.isInWorkingCondition()) {
				nonWorkingParts.put(part.getType(), part.getCondition());
			}
		}
		return nonWorkingParts;
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
