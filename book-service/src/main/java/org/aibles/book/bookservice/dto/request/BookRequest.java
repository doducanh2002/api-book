package org.aibles.book.bookservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    //        private int id;

    @NotBlank(message = "Name can not be null")
    private String name;

    @NotBlank(message = "Description can be not null")
    private String description;

    @NotBlank(message = "Release at can be not null")
    private Instant releaseAt;

    private boolean isActive;
}
