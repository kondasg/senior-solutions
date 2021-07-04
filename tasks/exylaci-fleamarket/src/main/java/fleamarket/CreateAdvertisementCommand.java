package fleamarket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdvertisementCommand {

    @NotNull(message = "Category can't be null")
    private LumberCategory lumberCategory;
    @NotBlank(message = "Text can't be blank")
    private String text;
}
