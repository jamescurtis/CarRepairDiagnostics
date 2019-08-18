package com.ubiquisoft.evaluation.utility;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.InputStream;

public class RequirementsBuilder {

    private static String filename = "SampleCarPartRequirements.xml";

    public static Requirement build() throws JAXBException {
        // Load classpath resource
        InputStream xml = ClassLoader.getSystemResourceAsStream(filename);

        // Verify resource was loaded properly
        if (xml == null) {
            System.err.println("An error occurred attempting to load" + filename);

            System.exit(1);
        }

        // Build JAXBContext for converting XML into an Object
        JAXBContext context = JAXBContext.newInstance(
                Requirement.class,
                com.ubiquisoft.evaluation.utility.Part.class
        );
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Requirement requirement = (Requirement) unmarshaller.unmarshal(xml);

        return requirement;
    }
}
