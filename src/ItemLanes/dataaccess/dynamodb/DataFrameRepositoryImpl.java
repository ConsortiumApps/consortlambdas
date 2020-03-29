package ItemLanes.dataaccess.dynamodb;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ItemLanes.dataaccess.DataFrameRepository;
import ItemLanes.model.DataFrame;
import ItemLanes.model.data.DataFramesRecord;
import lombok.AllArgsConstructor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

@AllArgsConstructor
public class DataFrameRepositoryImpl implements DataFrameRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public DataFrame getMostRecentDataFrame(String dataSourceName) {
        return this.getMostRecentDataFrames(dataSourceName)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<DataFrame> getMostRecentDataFrames(String dataSourceName) {
        DataFramesRecord dataFramesRecord = DataFramesRecord.builder()
                .dataSourceName(dataSourceName)
                .build();
        Condition rangeKeyCondition = new Condition().withComparisonOperator(ComparisonOperator.GE.toString())
                .withAttributeValueList(new AttributeValue().withS(LocalDateTime.now()
                        .minusDays(2)
                        .toString()));
        final DynamoDBQueryExpression<DataFramesRecord> queryExpression =
                new DynamoDBQueryExpression<DataFramesRecord>().withHashKeyValues(dataFramesRecord)
                        .withScanIndexForward(false)
                        .withRangeKeyCondition("captureDateTime", rangeKeyCondition);
        return dynamoDBMapper.query(DataFramesRecord.class, queryExpression)
                .stream()
                .map(dft -> DataFrame.builder()
                        .creationDateTime(dft.getCaptureDateTime())
                        .dataSource(dft.getDataSourceName())
                        .frameId(dft.getDataFrameId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void saveDataFrame(DataFrame dataFrame) {
        DataFramesRecord dataFramesRecord = DataFramesRecord.builder()
                .captureDateTime(LocalDateTime.now())
                .dataSourceName(dataFrame.getDataSource())
                .dataFrameId(dataFrame.getFrameId())
                .build();
        dynamoDBMapper.save(dataFramesRecord);
    }
}
