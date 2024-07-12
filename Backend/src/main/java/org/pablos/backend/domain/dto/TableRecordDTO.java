package org.pablos.backend.domain.dto;

import org.pablos.backend.domain.enums.PassengerClass;

import java.io.Serializable;

public record TableRecordDTO(Long id, boolean survived, PassengerClass pclass, String name, String sex, double age,
                             int numberOfSiblingsOrSpousesAboard, int numberOfParentsOrChildrenAboard,
                             double fare) implements Serializable {
}