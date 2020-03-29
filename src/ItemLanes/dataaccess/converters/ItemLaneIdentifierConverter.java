package ItemLanes.dataaccess.converters;

import java.util.Arrays;

import ItemLanes.model.DataFrame;
import ItemLanes.model.ItemLaneIdentifier;
import ItemLanes.model.Lane;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class ItemLaneIdentifierConverter implements
    DynamoDBTypeConverter<String, ItemLaneIdentifier> {

  public static final String ITEM_LANE_ID_SEPARATOR = "#";
  public static final int DATA_FRAME_INDEX = 0;
  public static final int ITEM_INDEX = 1;
  public static final int LANE_INDEX = 2;
  private static final LaneConverter laneConverter = new LaneConverter();
  private static final DataFrameConverter dataFrameConverter = new DataFrameConverter();

  @Override
  public String convert(ItemLaneIdentifier itemLaneIdentifier) {

    String s = dataFrameConverter.convert(itemLaneIdentifier.getDataFrame()) + ITEM_LANE_ID_SEPARATOR + itemLaneIdentifier.getItem()
            + ITEM_LANE_ID_SEPARATOR + laneConverter.convert(itemLaneIdentifier.getLane());
    System.out.println("s = " + s);
    return s;
  }

  @Override
  public ItemLaneIdentifier unconvert(String itemLaneIdentifierValue) {
    System.out.println("itemLaneIdentifierValue = " + itemLaneIdentifierValue);
    String[] itemLaneIdParts = itemLaneIdentifierValue.split(ITEM_LANE_ID_SEPARATOR);
    System.out.println("itemLaneIdParts = " + Arrays.toString(itemLaneIdParts));
    DataFrame dataFrame = dataFrameConverter.unconvert(itemLaneIdParts[DATA_FRAME_INDEX]);
    String item = itemLaneIdParts[ITEM_INDEX];
    Lane lane = laneConverter.unconvert(itemLaneIdParts[LANE_INDEX]);

    return new ItemLaneIdentifier(item, lane, dataFrame);
  }
}
