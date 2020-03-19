package ItemLanes.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemLane {

    private final String item;
    private final Node sourceNode;
    private final Node destNode;
}
