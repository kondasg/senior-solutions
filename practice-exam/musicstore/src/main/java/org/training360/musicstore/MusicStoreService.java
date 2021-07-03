package org.training360.musicstore;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.lang.reflect.Type;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {

    private List<Instrument> instruments = new ArrayList<>();
    private AtomicLong atomicLong = new AtomicLong();
    private ModelMapper modelMapper;

    public MusicStoreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<InstrumentDTO> getInstruments(Optional<String> brand, Optional<Double> price) {
        Type targetListType = new TypeToken<List<InstrumentDTO>>() {}.getType();

        return modelMapper.map(instruments, targetListType);
    }

    public void deleteAllInstruments() {
        instruments.clear();
        atomicLong = new AtomicLong();
    }

    public InstrumentDTO addInstrument(CreateInstrumentCommand command) {
        Instrument instrument = new Instrument(
                atomicLong.incrementAndGet(),
                command.getBrand(),
                command.getType(),
                command.getPrice(),
                LocalDate.now());
        instruments.add(instrument);
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public InstrumentDTO getInstrumentById(long id) {
        return modelMapper.map(getInstrument(id), InstrumentDTO.class);
    }

    public InstrumentDTO updateInstrumentById(long id, UpdatePriceCommand command) {
        Instrument instrument = getInstrument(id);
        if (instrument.getPrice() != command.getPrice()) {
            instrument.setPrice(command.getPrice());
        }
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteInstrumentById(long id) {
        instruments.remove(getInstrument(id));
    }

    private Instrument getInstrument(long id) {
        return instruments.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
    }
}

