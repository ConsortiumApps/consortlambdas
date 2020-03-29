package ItemLanes.apis;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ItemLanes.dataaccess.DataFrameRepository;
import ItemLanes.dataaccess.ItemLanesRepository;
import ItemLanes.dataaccess.s3.ItemLanesS3Repository;
import ItemLanes.model.AddMockItemLanesRequest;
import ItemLanes.model.AddMockItemLanesResponse;
import ItemLanes.model.DataFrame;
import ItemLanes.model.ItemLane;
import ItemLanes.model.ItemLaneIdentifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import common.model.S3Pointer;
import itemsreplenishment.module.DaggerServiceComponent;
import itemsreplenishment.module.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddMockItemLanesController implements RequestHandler<AddMockItemLanesRequest, AddMockItemLanesResponse> {

    private final DataFrameRepository dataFrameRepository;
    private final ItemLanesRepository itemLanesRepository;
    private final ItemLanesS3Repository itemLanesS3Repository;

    public AddMockItemLanesController() {
        this(DaggerServiceComponent.create());
    }

    public AddMockItemLanesController(
            final ServiceComponent serviceComponent) {
        itemLanesRepository = serviceComponent.provideItemLanesRepository();
        itemLanesS3Repository = serviceComponent.provideItemLanesS3Repository();
        dataFrameRepository = serviceComponent.provideDataFrameRepository();
    }

    @Override
    public AddMockItemLanesResponse handleRequest(
            AddMockItemLanesRequest input,
            Context context) {

        DataFrame dataFrame = DataFrame.builder()
                .dataSource(input.getDataSourceName())
                .frameId(input.getDataFrameId())
                .creationDateTime(LocalDateTime.now())
                .build();
        List<ItemLaneIdentifier> itemLaneIdentifiers = Arrays.asList(input.getItemLanes())
                .stream()
                .map((ItemLane i) -> {
                    return new ItemLaneIdentifier(i.getItem(), i.getLane(), dataFrame);
                })
                .collect(Collectors.toList());
        itemLanesRepository.AddItemLanesForItems(itemLaneIdentifiers);
        Map<String, S3Pointer> s3PointerMap = new HashMap<>();
        try {
            s3PointerMap = itemLanesS3Repository.saveItemLanesByItem(itemLaneIdentifiers, dataFrame);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        dataFrameRepository.saveDataFrame(dataFrame);
        AddMockItemLanesResponse mockItemLanesResponse = new AddMockItemLanesResponse(s3PointerMap, dataFrame);
        return mockItemLanesResponse;
    }
}
