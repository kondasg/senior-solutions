package fleamarket;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class FleamarketService {

    private ModelMapper modelMapper;
    private AtomicLong idGenerator = new AtomicLong();
    private List<Advertisement> advertisements = new ArrayList<>();

    public FleamarketService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void deleteAllAdvertisements() {
        advertisements.clear();
        idGenerator = new AtomicLong();
    }

    public AdvertisementDTO addAdvertisement(CreateAdvertisementCommand command) {
        Advertisement advertisement =
                new Advertisement(idGenerator.incrementAndGet(), command);
        advertisements.add(advertisement);
        return modelMapper.map(advertisement, AdvertisementDTO.class);
    }

    public List<AdvertisementDTO> listAdvertisements(Optional<String> category, Optional<String> word) {
        Type targetListType = new TypeToken<List<AdvertisementDTO>>() {
        }.getType();
        List<Advertisement> filtered = advertisements.stream()
                .filter(a -> category.isEmpty() || a.getLumberCategory().name().equals(category.get().toUpperCase()))
                .filter(a -> word.isEmpty() || a.getText().toLowerCase().contains(word.get().toLowerCase()))
                .sorted(Comparator.comparing(Advertisement::getTimeStamp).reversed())
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    }

    public AdvertisementDTO modifyAdvertisementTextById(long id, UpdateAdvertisementCommand command) {
        Advertisement advertisement = getAdvertisement(id);
        if (!command.getText().isEmpty()) {
            advertisement.setText(command.getText());
        }
        return modelMapper.map(advertisement, AdvertisementDTO.class);
    }

    public AdvertisementDTO getAdvertisementById(long id) {
        return modelMapper.map(getAdvertisement(id), AdvertisementDTO.class);
    }

    public void deleteAdvertisementById(long id) {
        advertisements.remove(getAdvertisement(id));
    }

    public void deleteOldAdvertisementAllCategory(Optional<String> category) {
        if (category.isPresent()) {
            advertisements.remove(
                    advertisements.stream()
                            .filter(a -> a.getLumberCategory().name().equals(category.get().toUpperCase()))
                            .min(Comparator.comparing(Advertisement::getTimeStamp))
                            .orElseThrow(() -> new IllegalArgumentException("Not found id"))
            );
        } else {
            Map<LumberCategory, List<Advertisement>> groupByCategory = advertisements.stream()
                    .sorted(Comparator.comparing(Advertisement::getTimeStamp))
                    .collect(Collectors.groupingBy(Advertisement::getLumberCategory, Collectors.toList()));
            List<Advertisement> del = new ArrayList<>();
            for (Map.Entry<LumberCategory, List<Advertisement>> entry: groupByCategory.entrySet()) {
                del.add(entry.getValue().get(0));
            }
            advertisements.removeAll(del);
        }
    }

    private Advertisement getAdvertisement(long id) {
        return advertisements.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not found id"));
    }
}
