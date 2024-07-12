package org.pablos.backend.service;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.pablos.backend.domain.dto.PageDataDTO;
import org.pablos.backend.domain.dto.RequestDTO;
import org.pablos.backend.domain.dto.TableRecordDTO;
import org.pablos.backend.domain.enums.SortingType;
import org.pablos.backend.domain.mapper.TableRecordMapper;
import org.pablos.backend.domain.model.TableRecord;
import org.pablos.backend.repository.TableRecordRepository;
import org.pablos.backend.util.ApplicationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
/**
 * Сервис для работы с записями.
 */
@Service
@Data
public class TableRecordService {

    private final ApplicationProperties properties;
    private final TableRecordRepository repository;
    private final TableParser parser;

    /**
     * Наполняет БД. Если в таблице нет записей, загружает данные.
     */
    @PostConstruct
    private void init() {
        if (getAll().isEmpty()) {
            saveAll(parser.loadTitanicData());
        }
    }

    /**
     * Возвращает все записи из таблицы.
     *
     * @return список всех записей.
     */
    public List<TableRecord> getAll() {
        return repository.findAll();
    }

    /**
     * Сохраняет все записи в таблицу.
     *
     * @param records список записей для сохранения.
     */
    public void saveAll(List<TableRecord> records) {
        repository.saveAll(records);
    }

    /**
     * Формирут и возвращает данные для отображения на странице.
     * Кэширует получаемые запросы и ответы.
     *
     * @param request объект запроса с параметрами фильтрации и сортировки.
     * @return объект PageDataDTO с данными для отображения на странице.
     */
    @Cacheable(value = "TableRecordService::getPageData", key = "#request")
    public PageDataDTO getPageData(RequestDTO request) {

        List<TableRecordDTO> records = tableMaker(request.search(), request.survived(), request.isAdult(),
                request.isMan(), request.noRelatives(), request.sortingType());

        // считать стату
        double totalFares = records.stream()
                .map(TableRecordDTO::fare)
                .reduce((double) 0, Double::sum);
        int totalSurvived = (int) records.stream().filter(TableRecordDTO::survived).count();
        int totalWithRelatives = (int) records.stream().filter(person ->
                person.numberOfSiblingsOrSpousesAboard() + person.numberOfParentsOrChildrenAboard() >0).count();

        return new PageDataDTO(records, request.size(), request.sortingType(), request.survived(), request.isMan(),
                request.isAdult(), request.noRelatives(), totalFares, totalWithRelatives, totalSurvived);
    }

    /**
     * Формирует список записей в зависимости от параметров поиска и фильтров.
     * Перед отдачей сортирует и маппит в DTO.
     *
     * @param search             строка поиска.
     * @param survivedFilterOn   указатель на включение фильтра по выживанию.
     * @param isAdultFilterOn    указатель на включение фильтра по возрасту.
     * @param isManFilterOn      указатель на включение фильтра по полу.
     * @param noRelativesFilterOn указатель на включение фильтра по наличию родственников.
     * @param sortingType        тип сортировки.
     * @return список записей, удовлетворяющих условиям поиска и фильтров.
     *
     * @see org.pablos.backend.domain.enums.SortingType
     */
    private List<TableRecordDTO> tableMaker(String search, boolean survivedFilterOn, boolean isAdultFilterOn,
            boolean isManFilterOn, boolean noRelativesFilterOn, SortingType sortingType) {

        ArrayList<TableRecord> records = new ArrayList<>(
                getAll().stream()
                .filter(person -> Objects.equals(search, "") || person.getName().contains(search))
                .filter(person -> !survivedFilterOn || person.isSurvived())
                .filter(person -> !isAdultFilterOn || person.getAge() >= properties.adultAgeCriteria)
                .filter(person -> !isManFilterOn || person.getSex().equals("male"))
                .filter(person -> !noRelativesFilterOn || (person.getNumberOfSiblingsOrSpousesAboard()
                        + person.getNumberOfParentsOrChildrenAboard()) == 0)
                .toList()
        );

        sortRecordsWith(records, sortingType);

        return TableRecordMapper.toListDto(records);
    }

    /**
     * Сортирует записи в зависимости от типа сортировки.
     *
     * @param records      список записей для сортировки.
     * @param sortingType  тип сортировки.
     */
    private void sortRecordsWith(List<TableRecord> records, SortingType sortingType) {
        Comparator<TableRecord> comparator;
        switch (sortingType) {
            case NAME_DESC -> comparator = Comparator.comparing(TableRecord::getName).reversed();
            case AGE_ASC -> comparator = Comparator.comparing(TableRecord::getAge);
            case AGE_DESC -> comparator = Comparator.comparing(TableRecord::getAge).reversed();
            case FARE_ASC -> comparator = Comparator.comparing(TableRecord::getFare);
            case FARE_DESC -> comparator = Comparator.comparing(TableRecord::getFare).reversed();
            // NAME_ASC by default
            default -> comparator = Comparator.comparing(TableRecord::getName);
        }
        records.sort(comparator);
    }

}
