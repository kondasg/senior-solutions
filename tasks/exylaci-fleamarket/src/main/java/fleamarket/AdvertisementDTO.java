package fleamarket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDTO {

    private long id;
    private LumberCategory lumberCategory;
    private String text;
    private LocalDateTime timeStamp;
}
