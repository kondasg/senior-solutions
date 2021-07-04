package fleamarket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {


    private long id;
    private LumberCategory lumberCategory;
    private String text;
    private LocalDateTime timeStamp = LocalDateTime.now();

    public Advertisement(long id, CreateAdvertisementCommand command) {
        this.id = id;
        this.lumberCategory = command.getLumberCategory();
        this.text = command.getText();
    }

    public void setText(String text) {
        this.text = text;
        timeStamp = LocalDateTime.now();
    }
}
