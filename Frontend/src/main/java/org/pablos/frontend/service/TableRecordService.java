package org.pablos.frontend.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.Data;
import org.pablos.frontend.domain.dto.PageDataDTO;
import org.pablos.frontend.domain.enums.SortingType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/**
 * Сервис для работы с записями из таблицы.
 */
@Data
@Service
public class TableRecordService {

    private final EurekaClient eurekaClient;
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Формирует запроск бэкенду, отправляет его через RestTemplate.
     * Получает данные для отображения на странице и возвращает их.
     *
     * @param size             размер страницы.
     * @param sort              тип сортировки.
     * @param survived         указатель на включение фильтра по выживанию.
     * @param is_adult         указатель на включение фильтра по возрасту.
     * @param is_man           указатель на включение фильтра по полу.
     * @param no_relatives     указатель на включение фильтра по наличию родственников.
     * @param search           строка поиска.
     * @return объект {@link org.pablos.frontend.domain.dto.PageDataDTO} с данными для отображения на странице.
     *
     * @see org.pablos.frontend.domain.dto.PageDataDTO
     * @see org.pablos.frontend.domain.enums.SortingType
     */
    public PageDataDTO getPaginatedTableRecords(int size, SortingType sort,
            boolean survived, boolean is_adult, boolean is_man, boolean no_relatives, String search) {
        String url = getBackendIp() + "/api/record?size=" + size
                + "&sort=" + sort // NAME_ASC; NAME_DESC; AGE_ASC; AGE_DESC; FARE_ASC; FARE_DESC
                + "&survived=" + survived
                + "&is_adult=" + is_adult
                + "&is_man=" + is_man
                + "&no_relatives=" + no_relatives
                + "&search=" + search;
        ResponseEntity<PageDataDTO> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
        });
        return response.getBody();
    }

    /**
     * Вытаскивает из эврики адрес бэкенда
     *
     * @return IP адрес бэкенда
     */
    private String getBackendIp() {
        InstanceInfo info = eurekaClient.getApplication("BACKEND").getInstances().get(0);
        return "http://" + info.getIPAddr() + ":" + info.getPort();
    }

}
