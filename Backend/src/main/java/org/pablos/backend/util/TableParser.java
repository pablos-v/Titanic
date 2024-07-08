package org.pablos.backend.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.pablos.backend.domain.enums.PassengerClass;
import org.pablos.backend.domain.model.TableRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Data
@Component
public class TableParser {

    private final ApplicationConfiguration configuration;

    /**
     * Скачивает csv-файл с данными о таблице и преобразует его в список объектов.
     * @return список объектов
     * @throws IOException если произошла ошибка при скачивании или чтении файла
     */
    public List<TableRecord> loadTitanicData() {
        List<TableRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new URL(configuration.tableUrl).openStream()))) {
            // Пропускаем заголовок
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                // Разбиваем строку на значения по запятой
                TableRecord record = getTableRecord(line);

                // Добавляем объект Record в список
                records.add(record);
            }
        } catch (MalformedURLException e) {
            log.error("URL is incorrect or resource is unavailable", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("Unavailable to read file", e);
            throw new RuntimeException(e);
        }
        return records;
    }

    private static TableRecord getTableRecord(String line) {
        String[] values = line.split(",");

        return new TableRecord(
                Integer.parseInt(values[0]) > 0,
                getPassengerClass(Integer.parseInt(values[1])),
                values[2],
                values[3],
                Double.parseDouble(values[4]),
                Integer.parseInt(values[5]),
                Integer.parseInt(values[6]),
                Double.parseDouble(values[7])
        );
    }

    private static PassengerClass getPassengerClass(int classNumber) {
        return switch (classNumber) {
            case 1 -> PassengerClass.FIRST;
            case 2 -> PassengerClass.SECOND;
            case 3 -> PassengerClass.THIRD;
            default -> throw new IllegalArgumentException("Invalid passenger class number");
        };
    }

}
