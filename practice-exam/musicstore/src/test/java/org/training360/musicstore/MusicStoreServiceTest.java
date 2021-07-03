package org.training360.musicstore;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class MusicStoreServiceTest {

    MusicStoreService musicStoreService = new MusicStoreService(new ModelMapper());

    @Test
    void getInstruments() {
    }

    @Test
    void addInstrument() {
        InstrumentDTO instrumentDTO =
                musicStoreService.addInstrument(new CreateInstrumentCommand("a", InstrumentType.ELECTRIC_GUITAR, 2000));

        assertEquals("a", instrumentDTO.getBrand());
    }

    @Test
    void getInstrumentById() {
        InstrumentDTO instrumentDTO =
                musicStoreService.addInstrument(new CreateInstrumentCommand("a", InstrumentType.ELECTRIC_GUITAR, 2000));

        assertEquals("a", musicStoreService.getInstrumentById(1).getBrand());
    }
}