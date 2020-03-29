package ItemLanes.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(onConstructor = @__({ @Deprecated }))
public class ItemLaneIdentifier {

    private String item;
    private Lane lane;
    private DataFrame dataFrame;

    public ItemLaneIdentifier(
            String item,
            Lane lane,
            DataFrame dataFrame) {
        this.item = item;
        this.lane = lane;
        this.dataFrame = dataFrame;
    }
}
