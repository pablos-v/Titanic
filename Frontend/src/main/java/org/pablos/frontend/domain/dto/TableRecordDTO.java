package org.pablos.frontend.domain.dto;

import org.pablos.frontend.domain.enums.PassengerClass;

public record TableRecordDTO(Long id, boolean survived, PassengerClass pclass, String name, String sex, double age,
                             int numberOfSiblingsOrSpousesAboard, int numberOfParentsOrChildrenAboard, double fare) {
}