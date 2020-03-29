package ItemLanes.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataFrame {

  public static final String DATA_FRAME_SEPARATOR = "_";
  private final String frameId;
  private final String dataSource;
  private final LocalDateTime creationDateTime;

}
