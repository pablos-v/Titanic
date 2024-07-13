package org.pablos.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pablos.backend.domain.dto.PageDataDTO;
import org.pablos.backend.domain.dto.RequestDTO;
import org.pablos.backend.domain.enums.PassengerClass;
import org.pablos.backend.domain.enums.SortingType;
import org.pablos.backend.domain.exception.SearchLengthException;
import org.pablos.backend.domain.exception.SizeException;
import org.pablos.backend.domain.model.TableRecord;
import org.pablos.backend.repository.TableRecordRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TableRecordServiceTest {

    @Mock
    private TableRecordRepository repository;

    @InjectMocks
    private TableRecordService service;

    List<TableRecord> listRecords = List.of(
            new TableRecord(1L, true, PassengerClass.FIRST, "Alla", "female", 31.5, 2,2,4.6),
            new TableRecord(2L, false, PassengerClass.THIRD, "Boris", "male", 41.5, 0,0,2.4)
    );

    RequestDTO request = new RequestDTO(50, SortingType.NAME_ASC, false, false, false, false, "");

    @Test
    @DisplayName("Тест для значений по умолчанию и проверка корректного подсчёта статистики")
    public void testGetPageDataWithDefaultValues() {
        when(repository.findAll()).thenReturn(listRecords);

        PageDataDTO result = service.getPageData(request);

        assertThat(result).isNotNull();
        assertThat(result.records().size()).isEqualTo(2);
        assertThat(result.records().get(0).name()).isEqualTo("Alla");
    }
    @Test
    @DisplayName("Тест проверка корректного подсчёта статистики")
    public void testGetPageDataTotalCounts() {
        when(repository.findAll()).thenReturn(listRecords);

        PageDataDTO result = service.getPageData(request);

        assertThat(result).isNotNull();
        assertThat(result.totalSurvived()).isEqualTo(1);
        assertThat(result.totalFares()).isEqualTo(7);
        assertThat(result.totalWithRelatives()).isEqualTo(1);
    }

    @Test
    @DisplayName("Тест сортировки по возрастанию цены билета")
    public void testGetPageDataCorrectSortingFARE_ASC() {
        when(repository.findAll()).thenReturn(listRecords);
        request = new RequestDTO(50, SortingType.FARE_ASC, false, false, false, false, "");

        PageDataDTO result = service.getPageData(request);

        assertThat(result).isNotNull();
        assertThat(result.records().get(0).fare()).isEqualTo(2.4);
    }
    @Test
    @DisplayName("Тест фильтрации по полу")
    public void testGetPageDataIsManFilterOn() {
        when(repository.findAll()).thenReturn(listRecords);
        request = new RequestDTO(50, SortingType.NAME_ASC, false, false, true, false, "");

        PageDataDTO result = service.getPageData(request);

        assertThat(result).isNotNull();
        assertThat(result.records().size()).isEqualTo(1);
        assertThat(result.records().get(0).sex()).isEqualTo("male");
    }
    @Test
    @DisplayName("Тест превышения длины поискового запроса")
    public void testGetPageDataSearchLengthExceeded() {
        request = new RequestDTO(50, SortingType.NAME_ASC, false, false, false,
                false, "SearchLengthExceptionSearchLengthExceptionSearchLengthException");

        assertThatThrownBy(() -> service.getPageData(request)).isInstanceOf(SearchLengthException.class);
    }
    @Test
    @DisplayName("Тест некорректного размера страницы")
    public void testGetPageDataSizeException() {
        request = new RequestDTO(555, SortingType.NAME_ASC, false, false, false,false, "");

        assertThatThrownBy(() -> service.getPageData(request)).isInstanceOf(SizeException.class);
    }


}