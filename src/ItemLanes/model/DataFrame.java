package ItemLanes.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataFrame {

    private final String frameId;
    private final String dataSource;
}
