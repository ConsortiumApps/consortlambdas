package ItemLanes.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemLanes {

    private DataFrame dataFrame;
    private final String item;
    private List<Lane> lanes;
}
