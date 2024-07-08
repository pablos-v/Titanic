package org.pablos.backend.service;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.pablos.backend.domain.exception.RecordNotFoundException;
import org.pablos.backend.domain.model.TableRecord;
import org.pablos.backend.repository.TableRecordRepository;
import org.pablos.backend.util.TableParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class TableRecordService {

    private final TableRecordRepository repository;
    private final TableParser parser;

    @PostConstruct
    private void init() {
        if (getAll().isEmpty()) {
            saveAll(parser.loadTitanicData());
        }
    }

    public List<TableRecord> getAll() {
        return repository.findAll();
    }

    public TableRecord getById(Long id) {
        return repository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    public void saveAll(List<TableRecord> records) {
        repository.saveAll(records);
    }

}
