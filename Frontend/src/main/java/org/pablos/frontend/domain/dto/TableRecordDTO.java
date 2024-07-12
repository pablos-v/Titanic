package org.pablos.frontend.domain.dto;

import org.pablos.frontend.domain.enums.PassengerClass;

import java.io.Serializable;

/**
 * Класс, представляющий DTO для записи из таблицы.
 *
 * @param id                              Идентификатор записи.
 * @param survived                        Указывает, выжил ли пассажир.
 * @param pclass                          Класс пассажира.
 * @param name                            Имя пассажира.
 * @param sex                             Пол пассажира.
 * @param age                             Возраст пассажира.
 * @param numberOfSiblingsOrSpousesAboard Количество братьев, сестер или супругов на борту.
 * @param numberOfParentsOrChildrenAboard Количество родителей или детей на борту.
 * @param fare                            Стоимость билета.
 */
public record TableRecordDTO(Long id, boolean survived, PassengerClass pclass, String name, String sex, double age,
                             int numberOfSiblingsOrSpousesAboard, int numberOfParentsOrChildrenAboard,
                             double fare) implements Serializable {
}