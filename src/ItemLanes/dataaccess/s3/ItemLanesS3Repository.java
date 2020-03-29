package ItemLanes.dataaccess.s3;

import java.util.List;
import java.util.Map;

import ItemLanes.model.DataFrame;
import ItemLanes.model.ItemLaneIdentifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import common.model.S3Pointer;

public interface ItemLanesS3Repository {

    Map<String, S3Pointer> saveItemLanesByItem(
            List<ItemLaneIdentifier> itemLaneIdentifiers,
            DataFrame dataFrame) throws JsonProcessingException;

    S3Pointer getItemLanesS3Pointer(String item, DataFrame dataFrame);
}
