package ItemLanes.dataaccess.dynamodb;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ItemLanes.dataaccess.DataFrameRepository;
import ItemLanes.model.DataFrame;
import ItemLanes.model.data.DataFramesTable;
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
        DataFramesTable dataFramesTable = DataFramesTable.builder()
                .dataSourceName(dataSourceName)
                .build();
        Condition rangeKeyCondition = new Condition().withComparisonOperator(ComparisonOperator.GE.toString())
                .withAttributeValueList(new AttributeValue().withS(LocalDateTime.now()
                        .minusDays(2)
                        .toString()));
        final DynamoDBQueryExpression<DataFramesTable> queryExpression =
                new DynamoDBQueryExpression<DataFramesTable>().withHashKeyValues(dataFramesTable)
                        .withRangeKeyCondition("captureDateTime", rangeKeyCondition);
        return dynamoDBMapper.query(DataFramesTable.class, queryExpression)
                .stream()
                .map(dft -> DataFrame.builder()
                        .dataSource(dft.getDataSourceName())
                        .frameId(dft.getCaptureDateTime())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void saveDataFrame(DataFrame dataFrame) {
        DataFramesTable dataFramesTable = DataFramesTable.builder()
                .captureDateTime(LocalDateTime.now()
                        .toString())
                .dataSourceName(dataFrame.getDataSource())
                .dataFrameId(dataFrame.getFrameId())
                .build();
        dynamoDBMapper.save(dataFramesTable);
    }
}
