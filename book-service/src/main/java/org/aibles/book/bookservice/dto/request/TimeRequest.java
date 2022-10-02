package org.aibles.book.bookservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeRequest extends BookRequest {

    private Boolean isActive;

    private long timeRemainingDay;

    private long timeRemainingHouse;

}
