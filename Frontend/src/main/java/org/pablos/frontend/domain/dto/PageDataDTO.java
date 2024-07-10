package org.pablos.frontend.domain.dto;

import org.pablos.frontend.domain.enums.SortingType;

import java.util.List;

/**
 * ДТО для формирования страницы
 * @param pages список записей
 * @param sizeOfPage записей на странице
 * @param sortingType тип и порядок сортировки
 * @param survivedFilterOn индикатор фильтрации по выжившим
 * @param isManFilterOn индикатор фильтрации по мужскому полу
 * @param isAdultFilterOn индикатор фильтрации по совершеннолетию
 * @param noRelativesFilterOn индикатор фильтрации по отсутствию родственников на борту
 * @param totalFares Статистика - общая сумма оплаты проезда
 * @param totalWithRelatives Статистика - количество людей имеющих родственников на борту
 * @param totalSurvived Статистика - количество выживших на борту
 */
public record PageDataDTO(List<PageDTO> pages, int sizeOfPage, SortingType sortingType, boolean survivedFilterOn,
                          boolean isManFilterOn, boolean isAdultFilterOn, boolean noRelativesFilterOn, double totalFares,
                          int totalWithRelatives, int totalSurvived) {
}
