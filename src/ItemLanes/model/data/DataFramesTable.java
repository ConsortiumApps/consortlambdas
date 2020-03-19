package ItemLanes.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@Builder
@Data
@NoArgsConstructor(onConstructor = @__({ @Deprecated }))
@DynamoDBTable(tableName = "DataFrames")
@AllArgsConstructor
public class DataFramesTable {

    @DynamoDBAttribute private String dataFrameId;

    @DynamoDBHashKey private String dataSourceName;

    @DynamoDBRangeKey private String captureDateTime;
}
