package ItemLanes.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class S3Pointer {

    private String bucket;
    private String key;
}
