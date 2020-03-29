package ItemLanes.model.data;

import ItemLanes.dataaccess.converters.LocalDateTimeConverter;
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
@DynamoDBTable(tableName = "DataFrames")
@AllArgsConstructor
public class DataFramesRecord {

  @DynamoDBAttribute
  private String dataFrameId;

  @DynamoDBHashKey
  private String dataSourceName;

  @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
  @DynamoDBRangeKey
  private LocalDateTime captureDateTime;
}
