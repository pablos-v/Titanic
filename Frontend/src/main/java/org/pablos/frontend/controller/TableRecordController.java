package org.pablos.frontend.controller;

import lombok.Data;
import org.pablos.frontend.domain.dto.PageDataDTO;
import org.pablos.frontend.domain.enums.SortingType;
import org.pablos.frontend.service.TableRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * Контроллер для работы с записями в таблице.
 */
@Data
@Controller
public class TableRecordController {

    private final TableRecordService service;

    /**
     * Получает запрос вида:
     * /?size=50&sort=NAME_ASC&survived=true%isAdult=true&isMan=true&noRelatives=true&search=Sara
     * Запрашивает у сервиса PageDataDTO с данными для формирования страницы и передаёт его в Модель.
     * После чего вызывает шаблон представления Thymeleaf.
     * При неверном SortingType ловит исключение IllegalArgumentException и отдаёт шаблон представления ошибки.
     * @param model Модель для передачи данных в представление.
     * @param size Размер страницы.
     * @param sort Тип сортировки.
     * @param survived Признак выживания.
     * @param isAdult Признак взрослости.
     * @param isMan Признак пола.
     * @param noRelatives Признак наличия родственников.
     * @param search Поисковый запрос.
     * @return Название представления.
     */
    @GetMapping("/")
    public String getRecords(Model model,
            @RequestParam(value = "size", defaultValue = "50") int size,
            @RequestParam(value = "sort", defaultValue = "NAME_ASC") String sort,
            @RequestParam(value = "survived", defaultValue = "false") boolean survived,
            @RequestParam(value = "is_adult", defaultValue = "false") boolean isAdult,
            @RequestParam(value = "is_man", defaultValue = "false") boolean isMan,
            @RequestParam(value = "no_relatives", defaultValue = "false") boolean noRelatives,
            @RequestParam(value = "search", defaultValue = "") String search)  {

        try {
            PageDataDTO data = service.getPaginatedTableRecords(size, SortingType.valueOf(sort), survived,
                    isAdult, isMan, noRelatives, search);
            model.addAttribute("data", data);
            return "records";
        } catch (IllegalArgumentException e) {
            return "wrong_sort_type";
        }
    }

}