package org.pablos.backend.domain.mapper;

import org.pablos.backend.domain.dto.TableRecordDTO;
import org.pablos.backend.domain.model.TableRecord;

import java.util.List;

/**
 * Не стал тащить Mapstruct ради простого маппинга.
 */
public class TableRecordMapper {
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

    public static List<TableRecordDTO> toListDto(List<TableRecord> records) {
        return records.stream()
                .map(TableRecordMapper::toDto)
                .toList();
    }

}
