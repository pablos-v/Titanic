package org.pablos.backend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {
    @Value("${properties.URL}")
    public String tableUrl;
    @Value("${properties.adultAgeCriteria}")
    public int adultAgeCriteria;

}
