package org.pablos.backend.controller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pablos.backend.domain.dto.PageDataDTO;
import org.pablos.backend.domain.dto.RequestDTO;
import org.pablos.backend.domain.enums.SortingType;
import org.pablos.backend.domain.exception.SortingTypeException;
import org.pablos.backend.service.TableRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RESTControllerTest {

    @Mock
    private TableRecordService service;
    @InjectMocks
    private RESTController controller;
    private PageDataDTO pageData;
    private int sizeOfPage;

    @BeforeEach
    public void setup() {
        sizeOfPage = 50;
        pageData = new PageDataDTO(new ArrayList<>(), sizeOfPage, SortingType.NAME_ASC, false, false,
                false, false, 100.1, 2, 2);
    }

    @Test
    @DisplayName("Тест для значений по умолчанию")
    public void testGetPagesWithDefaultValues() {
        when(service.getPageData(any(RequestDTO.class))).thenReturn(pageData);

        ResponseEntity<PageDataDTO> response = controller.getPages(sizeOfPage, "NAME_ASC", false, false, false, false, "");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(pageData);
    }
    @Test
    @DisplayName("Тест для изменённых значений")
    public void testGetPagesWithCustomValues() {
        sizeOfPage = 100;
        when(service.getPageData(any(RequestDTO.class))).thenReturn(pageData);

        ResponseEntity<PageDataDTO> response = controller.getPages(sizeOfPage, "AGE_DESC", false, false, false, false, "");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(pageData);
    }
    @Test
    @DisplayName("Тест на IllegalArgumentException при неверном типе сортировки")
    public void testGetPagesWithBorderValues() {
        sizeOfPage = 100;
        assertThatThrownBy(() -> controller.getPages(sizeOfPage, "WRONG_WRONG_SORT", false,
                false, false, false, "")).isInstanceOf(SortingTypeException.class);
    }

}