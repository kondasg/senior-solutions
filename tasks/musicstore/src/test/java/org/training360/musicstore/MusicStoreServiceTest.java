package org.training360.musicstore;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MusicStoreServiceTest {

    MusicStoreService musicStoreService = new MusicStoreService(new ModelMapper());

    @Test
    void getInstruments() {
        musicStoreService.addInstrument(new CreateInstrumentCommand("a", InstrumentType.ELECTRIC_GUITAR, 2000));
        musicStoreService.addInstrument(new CreateInstrumentCommand("ab", InstrumentType.ELECTRIC_GUITAR, 3000));
        musicStoreService.addInstrument(new CreateInstrumentCommand("v", InstrumentType.ELECTRIC_GUITAR, 2000));
        musicStoreService.addInstrument(new CreateInstrumentCommand("f", InstrumentType.ELECTRIC_GUITAR, 5000));

        assertEquals(4, musicStoreService.getInstruments(Optional.empty(), Optional.empty()).size());
        assertEquals(2, musicStoreService.getInstruments(Optional.empty(), Optional.of(2000d)).size());
        assertEquals(1, musicStoreService.getInstruments(Optional.of("a"), Optional.of(2000d)).size());
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