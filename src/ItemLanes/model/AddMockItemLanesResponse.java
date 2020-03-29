package ItemLanes.model;

import java.util.Map;

import common.model.S3Pointer;
import lombok.Data;

@Data
public class AddMockItemLanesResponse {

  private final Map<String, S3Pointer> s3PointerMap;
  private final DataFrame dataFrame;

}
