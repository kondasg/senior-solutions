package org.training360.musicstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentDTO {

    private long id;
    private String brand;
    private InstrumentType type;
    private double price;
    private LocalDate postDate;
}

