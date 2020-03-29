package ItemLanes.model;

import java.util.List;
import lombok.Data;

@Data
public class AddMockItemLanesRequest {

  private ItemLane[] itemLanes;

  private String dataFrameId;
  private String dataSourceName = "ItemLanes";

}
