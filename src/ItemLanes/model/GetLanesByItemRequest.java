package ItemLanes.model;

import lombok.Data;

@Data
public class GetLanesByItemRequest {

    private String item;
    private String dataFrameId;
    private String dataSourceName = "ItemLanes";
    private boolean expanded;
}
