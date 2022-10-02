package org.aibles.book.bookservice.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeResponse extends BookResponse{

    private long timeRemainingDay;

    private long timeRemainingHouse;

}
