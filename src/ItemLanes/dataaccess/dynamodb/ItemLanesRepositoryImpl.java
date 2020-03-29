package ItemLanes.dataaccess.dynamodb;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ItemLanes.dataaccess.ItemLanesRepository;
import ItemLanes.model.DataFrame;
import ItemLanes.model.ItemLaneIdentifier;
import ItemLanes.model.data.ItemLanesRecord;
import lombok.AllArgsConstructor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

@AllArgsConstructor
public class ItemLanesRepositoryImpl implements ItemLanesRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Override
    public List<ItemLanesRecord> getItemLanes(
            DataFrame dataFrame,
            String item) {
        ItemLanesRecord itemLanesRecord = ItemLanesRecord.builder()
                .item(item)
                .build();
        Condition rangeKeyCondition = new Condition().withComparisonOperator(ComparisonOperator.BEGINS_WITH.toString())
                .withAttributeValueList(
                        new AttributeValue().withS(dataFrame.getFrameId() + DataFrame.DATA_FRAME_SEPARATOR + dataFrame.getDataSource()));

        final DynamoDBQueryExpression<ItemLanesRecord> queryExpression =
                new DynamoDBQueryExpression<ItemLanesRecord>().withHashKeyValues(itemLanesRecord)
                        .withLimit(2)
                        .withRangeKeyCondition("itemLaneId", rangeKeyCondition);

        return dynamoDBMapper.query(ItemLanesRecord.class, queryExpression)
                .stream()
                .map(itemLane -> ItemLanesRecord.builder()
                        .creationDate(itemLane.getCreationDate())
                        .lastUpdatedDate(itemLane.getLastUpdatedDate())
                        .item(itemLane.getItem())
                        .itemLaneId(itemLane.getItemLaneId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void AddItemLanesForItems(List<ItemLaneIdentifier> itemLaneIdentifiers) {

        for (ItemLaneIdentifier itemLaneId : itemLaneIdentifiers) {
            System.out.println("itemLaneId.item = " + itemLaneId.getItem());
            System.out.println("itemLaneId = " + itemLaneId);
            ItemLanesRecord itemLanesRecord = ItemLanesRecord.builder()
                    .item(itemLaneId.getItem())
                    .itemLaneId(itemLaneId)
                    .creationDate(LocalDateTime.now())
                    .lastUpdatedDate(LocalDateTime.now())
                    .build();
            System.out.println("itemLanesRecord = " + itemLanesRecord);
            dynamoDBMapper.save(itemLanesRecord);
        }
    }
}
