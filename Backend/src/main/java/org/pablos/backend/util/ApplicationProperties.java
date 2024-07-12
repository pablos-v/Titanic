package org.pablos.backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Класс, содержащий переменные приложения, получаемые из application.yaml
 */
@Configuration
public class ApplicationProperties {
    /**
     * URL csv-файла с данными о таблице.
     */
    @Value("${properties.URL}")
    public String tableUrl;
    /**
     * Возраст - критерий совершеннолетия.
     */
    @Value("${properties.adultAgeCriteria}")
    public int adultAgeCriteria;
}

