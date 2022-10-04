package org.aibles.book.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Name can not be null")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description can not be null")
    @Column(name = "description")
    private String description;

    @Column(name = "releaseAt")
    private LocalDate releaseAt;

    @Column(name = "isActive")
    private Boolean isActive;

    private long timeRemainingDay;

    private long timeRemainingHouse;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book obj = (Book) o;
        return Objects.equals(getId(), obj.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
