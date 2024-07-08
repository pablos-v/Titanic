package org.pablos.backend.repository;

import org.pablos.backend.domain.model.TableRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRecordRepository extends JpaRepository<TableRecord, Long> {

}
