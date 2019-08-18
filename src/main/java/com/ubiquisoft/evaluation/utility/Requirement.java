package com.ubiquisoft.evaluation.utility;

import com.ubiquisoft.evaluation.domain.PartType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Requirement {

    private List<com.ubiquisoft.evaluation.utility.Part> parts;

    public Map<PartType, Integer> getRequirementsMap() {
        Map<PartType, Integer> requirements = new HashMap<>();
        parts.forEach(
            (part) -> requirements.put(part.getType(), Integer.parseInt(part.getQuantity()))
        );
        return requirements;
    }

    public List<Part> getPart() {
        return parts;
    }

    public void setPart(List<Part> parts) {
        this.parts = parts;
    }

}
