package org.aibles.book.bookservice.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    @Id
    private int id;

    @NotBlank(message = "Name can not be null")
    private String name;

    @NotBlank(message = "Description can be not null")
    private String description;

    @NotBlank(message = "Release at can be not null")
    private LocalDate releaseAt;

    private Boolean isActive;
}
