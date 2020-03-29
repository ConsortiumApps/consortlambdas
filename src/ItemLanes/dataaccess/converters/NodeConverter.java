package ItemLanes.dataaccess.converters;

import ItemLanes.model.Node;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class NodeConverter implements DynamoDBTypeConverter<String, Node> {

  public static final String NODE_SEPARATOR = "-";
  public static final int OWNER_INDEX = 0;
  public static final int LOCATION_INDEX = 1;

  @Override
  public String convert(Node node) {
    return node.getOwner() + NODE_SEPARATOR + node.getLocation();
  }

  @Override
  public Node unconvert(String node) {
    System.out.println("node = " + node);
    String[] nodeParts = node.split(NODE_SEPARATOR);
    Node cnvNode = new Node();
    cnvNode.setOwner(Integer.parseInt(nodeParts[OWNER_INDEX]));
    cnvNode.setLocation(nodeParts[LOCATION_INDEX]);
    return cnvNode;
  }
}
