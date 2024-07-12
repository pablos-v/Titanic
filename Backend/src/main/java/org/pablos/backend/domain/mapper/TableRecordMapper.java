package org.pablos.backend.domain.mapper;

import org.pablos.backend.domain.dto.TableRecordDTO;
import org.pablos.backend.domain.model.TableRecord;

import java.util.List;

/**
 * Маппер для преобразования сущности {@link org.pablos.backend.domain.model.TableRecord} в DTO {@link org.pablos.backend.domain.dto.TableRecordDTO}.
 * Не стал тащить Mapstruct ради простого маппинга.
 */
public class TableRecordMapper {
    /**
     * Преобразует сущность в DTO.
     *
     * @param record сущность
     * @return DTO
     */
    public static TableRecordDTO toDto(TableRecord record) {
        return new TableRecordDTO(
                record.getId(),
                record.isSurvived(),
                record.getPclass(),
                record.getName(),
                record.getSex(),
                record.getAge(),
                record.getNumberOfSiblingsOrSpousesAboard(),
                record.getNumberOfParentsOrChildrenAboard(),
                record.getFare());
    }

    /**
     * Преобразует список сущностей в список DTO.
     *
     * @param records список сущностей
     * @return список DTO
     */
    public static List<TableRecordDTO> toListDto(List<TableRecord> records) {
        return records.stream()
                .map(TableRecordMapper::toDto)
                .toList();
    }

}


