package com.metastring.kew.entity.enumtype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "taxonomyStatus")
@XmlEnum
public enum TaxonomyStatus {

    @XmlEnumValue("ACCEPTED")
    ACCEPTED("ACCEPTED"),
    @XmlEnumValue("SYNONYM")
    SYNONYM("SYNONYM"),

    @XmlEnumValue("INVALID")
    INVALID("INVALID"),
    @XmlEnumValue("ILLEGITIMATE")
    ILLEGITIMATE("ILLEGITIMATE"),

    @XmlEnumValue("MISAPPLIED")
    MISAPPLIED("MISAPPLIED"),
    @XmlEnumValue("ARTIFICIAL HYBRID")
    ARTIFICIAL ("ARTIFICIAL HYBRID"),

    @XmlEnumValue("UNPLACED")
    UNPLACED ("UNPLACED"),
    @XmlEnumValue("ORTHOGRAPHIC")
    ORTHOGRAPHIC ("ORTHOGRAPHIC");

    private String value;

    private TaxonomyStatus(String value) {
        this.value = value;
    }

    public static TaxonomyStatus fromString(String value) {
        for(TaxonomyStatus layerStatus : TaxonomyStatus.values()) {
            if(layerStatus.value.equals(value))
                return layerStatus;
        }
        throw new IllegalArgumentException(value);
    }

}
