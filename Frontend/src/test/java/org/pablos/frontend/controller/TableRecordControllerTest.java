package org.pablos.frontend.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pablos.frontend.domain.dto.PageDataDTO;
import org.pablos.frontend.domain.enums.SortingType;
import org.pablos.frontend.service.TableRecordService;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TableRecordControllerTest {

    @Mock
    private TableRecordService service;
    @InjectMocks
    private TableRecordController controller;
    private Model model;
    private PageDataDTO pageData;
    private int sizeOfPage;

    @BeforeEach
    public void setup() {
        model = getModel();
        sizeOfPage = 50;
        pageData = new PageDataDTO(new ArrayList<>(), sizeOfPage, SortingType.NAME_ASC, false, false,
                false, false, 100.1, 2, 2);
    }

    @Test
    @DisplayName("Тест для значений по умолчанию")
    public void testGetRecordsWithDefaultValues() {
        when(service.getPaginatedTableRecords(sizeOfPage, SortingType.NAME_ASC, false, false, false, false, ""))
                .thenReturn(pageData);

        String viewName = controller.getRecords(model, sizeOfPage, "NAME_ASC", false, false, false, false, "");

        assertThat(viewName).isEqualTo("records");
        assertThat(model.getAttribute("data")).isEqualTo(pageData);
    }


    @Test
    @DisplayName("Тест для изменённых значений")
    public void testGetRecordsWithCustomValues() {
        sizeOfPage = 100;
        pageData = new PageDataDTO(new ArrayList<>(), sizeOfPage, SortingType.AGE_DESC, false, false,
                false, false, 100.1, 2, 2);
        when(service.getPaginatedTableRecords(sizeOfPage, SortingType.AGE_DESC, true, true, true, true, "John"))
                .thenReturn(pageData);

        String viewName = controller.getRecords(model, sizeOfPage, "AGE_DESC", true, true, true, true, "John");

        assertThat(viewName).isEqualTo("records");
        assertThat(model.getAttribute("data")).isEqualTo(pageData);
    }

    @Test
    @DisplayName("Тест на IllegalArgumentException при неверном типе сортировки")
    public void testGetRecordsWithBorderValues() {
        String records = controller.getRecords(model, sizeOfPage, "WRONG_WRONG_SORT", true, true, true, true, "");

        assertThat(records).isEqualTo("wrong_sort_type");
    }
    private Model getModel() {
        return new Model() {
            private final Map<String, Object> map = new HashMap<>();

            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                this.map.put(attributeName, attributeValue);
                return this;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return map.get(attributeName);
            }

            @Override
            public Map<String, Object> asMap() {
                return this.map;
            }
        };
    }
}

