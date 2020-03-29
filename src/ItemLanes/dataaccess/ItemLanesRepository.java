package ItemLanes.dataaccess;

import ItemLanes.model.DataFrame;
import ItemLanes.model.ItemLaneIdentifier;
import ItemLanes.model.data.ItemLanesRecord;
import java.util.List;

public interface ItemLanesRepository {

  List<ItemLanesRecord> getItemLanes(
      DataFrame dataFrame,
      String item);

  void AddItemLanesForItems(List<ItemLaneIdentifier> itemLaneIdentifiers);
}
