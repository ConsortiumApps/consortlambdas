package ItemLanes.model.data;

import ItemLanes.dataaccess.converters.ItemLaneIdentifierConverter;
import ItemLanes.dataaccess.converters.LocalDateTimeConverter;
import ItemLanes.model.ItemLaneIdentifier;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@AllArgsConstructor
@DynamoDBTable(tableName = "ItemLanes")
public class ItemLanesRecord {

  //todo: Add validations since not wise to insert corrupted data in dynamodb

  @DynamoDBHashKey(attributeName = "item")
  private String item;

  @DynamoDBRangeKey(attributeName = "itemLaneId")
  @DynamoDBTypeConverted(converter = ItemLaneIdentifierConverter.class)
  private ItemLaneIdentifier itemLaneId;

  @DynamoDBAttribute
  @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
  private LocalDateTime creationDate;

  @DynamoDBAttribute
  @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
  private LocalDateTime lastUpdatedDate;



}
