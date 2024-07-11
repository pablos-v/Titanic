package org.pablos.frontend.controller;

import lombok.Data;
import org.pablos.frontend.domain.dto.PageDataDTO;
import org.pablos.frontend.domain.enums.SortingType;
import org.pablos.frontend.service.TableRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Controller
public class TableRecordController {

    private final TableRecordService service;

    /**
     * Получает запрос вида:
     * /records?size=50&sort=NAME_ASC&survived=true%isAdult=true&isMan=true&noRelatives=true&search=Sara
     * @param model
     * @param size
     * @param sort
     * @param survived
     * @param isAdult
     * @param isMan
     * @param noRelatives
     * @param search
     * @return
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
        PageDataDTO data = service.getPaginatedTableRecords(size, SortingType.valueOf(sort), survived,
                isAdult, isMan, noRelatives, search);

        model.addAttribute("data", data);

        return "records";
    }

}