package com.ubiquisoft.evaluation;

import com.ubiquisoft.evaluation.domain.Car;
import com.ubiquisoft.evaluation.domain.ConditionType;
import com.ubiquisoft.evaluation.domain.Part;
import com.ubiquisoft.evaluation.domain.PartType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class CarDiagnosticEngine {
	/*I WANTED TO KEEP THIS STUPID SIMPLE, SURE THERE ARE OTHER WAYS TO DO IT.
	  I.E. LAMDAS, STREAMS, FILTERS, ETC ...
	  SO I KEPT THAT TO THE MINIMUM AND
	*/
	public void executeDiagnostics(Car car) {
		List<String> missingFields = car.getMissingCarFields();
		if (!missingFields.isEmpty()) {
			car.getMissingCarFields().forEach(this::printMissingCarField);
			System.exit(-1);
		}

		Map<PartType, Integer> missingParts = car.getMissingPartsMap();
		if (!missingParts.isEmpty()) {
			car.getMissingPartsMap().forEach(this::printMissingPart);
			System.exit(-1);
		}

		boolean exit = false;
		for (Part part : car.getParts()) {
			if (!part.isInWorkingCondition()) {
				exit = true;
				printDamagedPart(part.getType(), part.getCondition());
			}
		}

		if (exit) System.exit(-1);

		System.out.println("Car Diagnostics Check: OKAY");
	}

	private void printMissingPart(PartType partType, Integer count) {
		if (partType == null) throw new IllegalArgumentException("PartType must not be null");
		if (count == null || count <= 0) throw new IllegalArgumentException("Count must be greater than 0");

		System.out.println(String.format("Missing Part(s) Detected: %s - Count: %s", partType, count));
	}

	private void printDamagedPart(PartType partType, ConditionType condition) {
		if (partType == null) throw new IllegalArgumentException("PartType must not be null");
		if (condition == null) throw new IllegalArgumentException("ConditionType must not be null");

		System.out.println(String.format("Damaged Part Detected: %s - Condition: %s", partType, condition));
	}

	private void printMissingCarField(String field) {
		System.out.println(String.format("Missing Car Field Detected: " + field));
	}

	public static void main(String[] args) throws JAXBException {
		// Load classpath resource
		InputStream xml = ClassLoader.getSystemResourceAsStream("SampleCar.xml");

		// Verify resource was loaded properly
		if (xml == null) {
			System.err.println("An error occurred attempting to load SampleCar.xml");

			System.exit(1);
		}

		// Build JAXBContext for converting XML into an Object
		JAXBContext context = JAXBContext.newInstance(Car.class, Part.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		Car car = (Car) unmarshaller.unmarshal(xml);

		// Build new Diagnostics Engine and execute on deserialized car object.

		CarDiagnosticEngine diagnosticEngine = new CarDiagnosticEngine();

		diagnosticEngine.executeDiagnostics(car);

	}

}
