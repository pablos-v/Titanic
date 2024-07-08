package org.pablos.backend.domain.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.pablos.backend.domain.enums.PassengerClass;

@Data
@Entity
@NoArgsConstructor
@Table(schema = "titanic", name = "records")
public class TableRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "survived")
    private boolean survived;
    @Column(name = "pclass", columnDefinition = "varchar")
    private PassengerClass pclass;
    @Column(name = "name")
    private String name;
    @Column(name = "sex")
    private String sex;
    @Column(name = "age")
    private double age;
    @Column(name = "number_of_siblings_or_spouses_aboard")
    private int numberOfSiblingsOrSpousesAboard;
    @Column(name = "number_of_parents_or_children_aboard")
    private int numberOfParentsOrChildrenAboard;
    @Column(name = "fare")
    private double fare;

    public TableRecord(boolean survived, PassengerClass pclass, String name, String sex, Double age,
            int siblingsSpouses, int parentsChildren, Double fare) {
        this.survived = survived;
        this.pclass = pclass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.numberOfSiblingsOrSpousesAboard = siblingsSpouses;
        this.numberOfParentsOrChildrenAboard = parentsChildren;
        this.fare = fare;
    }

}
