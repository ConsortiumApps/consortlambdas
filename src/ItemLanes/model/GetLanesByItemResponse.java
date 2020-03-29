package ItemLanes.model;

import common.model.S3Pointer;
import lombok.Data;

@Data
public class GetLanesByItemResponse {

    private final S3Pointer s3Pointer;
    private final DataFrame dataFrame;
    private ItemLane[] itemLanes;
    boolean expanded;
}
