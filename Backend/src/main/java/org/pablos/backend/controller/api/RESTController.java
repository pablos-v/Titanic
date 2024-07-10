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

@RestController
@RequestMapping("/api/record")
@Data
public class RESTController {

    private final TableRecordService recordService;

    @GetMapping
    public ResponseEntity<PageDataDTO> getPages(
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "survived") boolean survived,
            @RequestParam(value = "is_adult") boolean isAdult,
            @RequestParam(value = "is_man") boolean isMan,
            @RequestParam(value = "no_relatives") boolean noRelatives,
            @RequestParam(value = "search") String search) {

        PageDataDTO data = null;
        // TODO обработку ошибок при валидации
        try {
            RequestDTO request = new RequestDTO(size, SortingType.valueOf(sort),
                    survived, isAdult, isMan, noRelatives, search);
            data = recordService.getPageData(request);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }





}
