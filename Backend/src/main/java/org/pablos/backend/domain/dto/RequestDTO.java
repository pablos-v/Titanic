package org.pablos.backend.domain.dto;

import org.pablos.backend.domain.enums.SortingType;

import java.io.Serializable;

/**
 * Контейнер для запроса. Сокращает количество кода для настройки кэширования Redis.
 * @param size записей на странице
 * @param sortingType порядок сортировки
 * @param survived индикатор фильтрации по выжившим
 * @param isAdult индикатор фильтрации по совершеннолетию
 * @param isMan индикатор фильтрации по мужскому полу
 * @param noRelatives индикатор фильтрации по отсутствию родственников на борту
 * @param search текст поиска по имени
 */
public record RequestDTO(int size, SortingType sortingType, boolean survived, boolean isAdult, boolean isMan,
                         boolean noRelatives, String search)  implements Serializable {

}
