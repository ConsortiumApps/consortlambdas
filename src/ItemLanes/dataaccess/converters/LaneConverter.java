package ItemLanes.dataaccess.converters;

import java.util.Arrays;

import ItemLanes.model.Lane;
import ItemLanes.model.Node;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class LaneConverter implements DynamoDBTypeConverter<String, Lane> {

  public static final String LANE_SEPARATOR = "\\|";
  public static final int SOURCE_INDEX = 0;
  public static final int DEST_INDEX = 1;
  private static final NodeConverter nodeConverter = new NodeConverter();

  @Override
  public String convert(Lane lane) {

    String sLane = nodeConverter.convert(lane.getSrcNode()) + "|" + nodeConverter.convert(lane.getDestNode());
    System.out.println("sLane = " + sLane);
    return sLane;
  }

  @Override
  public Lane unconvert(String lane) {
    System.out.println("lane = " + lane);
    String[] laneParts = lane.split(LANE_SEPARATOR);
    System.out.println("laneParts = " + Arrays.toString(laneParts));
    Node srcNode = nodeConverter.unconvert(laneParts[SOURCE_INDEX]);
    Node destNode = nodeConverter.unconvert(laneParts[DEST_INDEX]);

    Lane cnvLane = new Lane();
    cnvLane.setSrcNode(srcNode);
    cnvLane.setDestNode(destNode);
    return cnvLane;
  }
}
