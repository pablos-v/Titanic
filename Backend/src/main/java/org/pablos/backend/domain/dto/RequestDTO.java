package org.pablos.backend.domain.dto;

import org.pablos.backend.domain.enums.SortingType;

import java.io.Serializable;

/**
 * Контейнер для запроса. Сокращает количество кода для настройки кэширования Redis.
 * @param size
 * @param sortingType
 * @param survived
 * @param isAdult
 * @param isMan
 * @param noRelatives
 * @param search
 */
public record RequestDTO(int size, SortingType sortingType, boolean survived, boolean isAdult, boolean isMan,
                         boolean noRelatives, String search)  implements Serializable {

}
