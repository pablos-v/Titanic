package org.pablos.backend.domain.dto;

import org.pablos.backend.domain.enums.PassengerClass;

import java.io.Serializable;

/**
 * Класс, представляющий DTO для записи из таблицы {@link org.pablos.backend.domain.model.TableRecord}.
 *
 * @param id                                 Идентификатор записи.
 * @param survived                           Указывает, выжил ли пассажир.
 * @param pclass                             Класс пассажира (например, {@link org.pablos.backend.domain.enums.PassengerClass#FIRST}).
 * @param name                               Имя пассажира.
 * @param sex                                Пол пассажира.
 * @param age                                Возраст пассажира.
 * @param numberOfSiblingsOrSpousesAboard    Количество братьев, сестер или супругов на борту.
 * @param numberOfParentsOrChildrenAboard    Количество родителей или детей на борту.
 * @param fare                               Стоимость билета.
 */
public record TableRecordDTO(Long id, boolean survived, PassengerClass pclass, String name, String sex, double age,
                             int numberOfSiblingsOrSpousesAboard, int numberOfParentsOrChildrenAboard,
                             double fare) implements Serializable {
}