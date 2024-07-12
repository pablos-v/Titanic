package org.pablos.backend.controller.api;

import lombok.Data;
import org.pablos.backend.domain.dto.PageDataDTO;
import org.pablos.backend.domain.dto.RequestDTO;
import org.pablos.backend.domain.enums.SortingType;
import org.pablos.backend.service.TableRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер REST API для работы с таблицей.
 */
@RestController
@RequestMapping("/api/record")
@Data
public class RESTController {

    private final TableRecordService recordService;

    /**
     * Получает запрос вида
     * /api/record?size=100&sort=NAME_DESC&survived=true&is_adult=true&is_man=true&no_relatives=true&search=text
     * Формирует объект запроса RequestDTO и отсылает запрос сервису TableRecordService.
     * @param size размер страницы
     * @param sort порядок сортировки
     * @param survived признак фильтрации по выжившим
     * @param isAdult признак фильтрации по совершеннолетним
     * @param isMan признак фильтрации по полу
     * @param noRelatives признак фильтрации по родственникам
     * @param search текст поиска
     * @return PageDataDTO
     */
    @GetMapping
    public ResponseEntity<PageDataDTO> getPages(
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "survived") boolean survived,
            @RequestParam(value = "is_adult") boolean isAdult,
            @RequestParam(value = "is_man") boolean isMan,
            @RequestParam(value = "no_relatives") boolean noRelatives,
            @RequestParam(value = "search") String search) {

        RequestDTO request = new RequestDTO(size, SortingType.valueOf(sort), survived,
                isAdult, isMan, noRelatives, search);
        return ResponseEntity.ok(recordService.getPageData(request));
    }
}
