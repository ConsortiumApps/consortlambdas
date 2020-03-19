package ItemLanes.model;

import lombok.Data;

@Data
public class GetLanesByItemResponse {

    private final S3Pointer s3Pointer;
    private final DataFrame dataFrame;
}
