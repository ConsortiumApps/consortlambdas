package ItemLanes.apis;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ItemLanes.dataaccess.DataFrameRepository;
import ItemLanes.dataaccess.ItemLanesRepository;
import ItemLanes.dataaccess.s3.ItemLanesS3Repository;
import ItemLanes.model.DataFrame;
import ItemLanes.model.GetLanesByItemRequest;
import ItemLanes.model.GetLanesByItemResponse;
import ItemLanes.model.ItemLane;
import ItemLanes.model.Lane;
import ItemLanes.model.data.ItemLanesRecord;
import com.google.common.base.Preconditions;
import common.model.S3Pointer;
import itemsreplenishment.module.DaggerServiceComponent;
import itemsreplenishment.module.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ItemLanesController implements RequestHandler<GetLanesByItemRequest, GetLanesByItemResponse> {

    public static final String ITEM_LANES = "ItemLanes";

    private final DataFrameRepository dataFrameRepository;
    private final ItemLanesRepository itemLanesRepository;
    private final ItemLanesS3Repository itemLanesS3Repository;

    public ItemLanesController() {
        this(DaggerServiceComponent.create());
    }

    public ItemLanesController(
            final ServiceComponent serviceComponent) {
        itemLanesRepository = serviceComponent.provideItemLanesRepository();
        dataFrameRepository = serviceComponent.provideDataFrameRepository();
        itemLanesS3Repository = serviceComponent.provideItemLanesS3Repository();
    }

    @Override
    public GetLanesByItemResponse handleRequest(
            GetLanesByItemRequest input,
            Context context) {
        Preconditions.checkArgument(ITEM_LANES.toLowerCase()
                .equals(input.getDataSourceName()
                        .toLowerCase()));
        DataFrame dataFrame;
        if (Optional.ofNullable(input.getDataFrameId())
                .isPresent()) {
            dataFrame = DataFrame.builder()
                    .dataSource(input.getDataSourceName())
                    .frameId(input.getDataFrameId())
                    .build();
        } else {
            dataFrame = dataFrameRepository.getMostRecentDataFrame(ITEM_LANES);
        }
        System.out.println("Most recent dataFrame = " + dataFrame);

        List<ItemLanesRecord> itemLanesRecord = itemLanesRepository.getItemLanes(DataFrame.builder()
                .frameId(dataFrame.getFrameId())
                .dataSource(dataFrame.getDataSource())
                .build(), input.getItem());
        System.out.println("itemLanesRecord = " + itemLanesRecord);
        S3Pointer s3ItemLanesPointer = itemLanesS3Repository.getItemLanesS3Pointer(input.getItem(), dataFrame);
        List<ItemLane> itemLaneList = newArrayList();
        if (input.isExpanded()) {
            itemLaneList = itemLanesRecord.stream()
                    .map((ItemLanesRecord i) -> {
                        Lane lane = i.getItemLaneId()
                                .getLane();
                        return ItemLane.builder()
                                .lane(lane)
                                .item(i.getItem())
                                .build();
                    })
                    .collect(Collectors.toList());
        }
        GetLanesByItemResponse getLanesByItemResponse = new GetLanesByItemResponse(s3ItemLanesPointer, dataFrame);
        getLanesByItemResponse.setExpanded(input.isExpanded());
        getLanesByItemResponse.setItemLanes(itemLaneList.toArray(new ItemLane[0]));
        return getLanesByItemResponse;
    }
}
