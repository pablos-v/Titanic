package org.pablos.backend.domain.mapper;

import org.junit.jupiter.api.Test;
import org.pablos.backend.domain.dto.TableRecordDTO;
import org.pablos.backend.domain.enums.PassengerClass;
import org.pablos.backend.domain.model.TableRecord;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TableRecordMapperTest {


    @Test
    public void testToDto() {
        TableRecord record = new TableRecord(1L, false, PassengerClass.FIRST, "John Doe", "male", 30, 1, 2, 100.0);
        TableRecordDTO result = TableRecordMapper.toDto(record);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1);
        assertThat(result.survived()).isFalse();
        assertThat(result.pclass()).isEqualTo(PassengerClass.FIRST);
        assertThat(result.name()).isEqualTo("John Doe");
        assertThat(result.sex()).isEqualTo("male");
        assertThat(result.age()).isEqualTo(30);
        assertThat(result.numberOfSiblingsOrSpousesAboard()).isEqualTo(1);
        assertThat(result.numberOfParentsOrChildrenAboard()).isEqualTo(2);
        assertThat(result.fare()).isEqualTo(100.0);
    }

    @Test
    public void testToListDto() {
        List<TableRecord> records = List.of(new TableRecord(1L, false, PassengerClass.FIRST, "John Doe", "male", 30, 1, 2, 100.0));
        List<TableRecordDTO> result = TableRecordMapper.toListDto(records);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).id()).isEqualTo(1);
        assertThat(result.get(0).survived()).isFalse();
        assertThat(result.get(0).pclass()).isEqualTo(PassengerClass.FIRST);
        assertThat(result.get(0).name()).isEqualTo("John Doe");
        assertThat(result.get(0).sex()).isEqualTo("male");
        assertThat(result.get(0).age()).isEqualTo(30);
        assertThat(result.get(0).numberOfSiblingsOrSpousesAboard()).isEqualTo(1);
        assertThat(result.get(0).numberOfParentsOrChildrenAboard()).isEqualTo(2);
        assertThat(result.get(0).fare()).isEqualTo(100.0);
    }

}