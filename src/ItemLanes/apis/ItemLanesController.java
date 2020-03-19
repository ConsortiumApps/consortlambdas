package ItemLanes.apis;

import ItemLanes.dataaccess.DataFrameRepository;
import ItemLanes.model.DataFrame;
import ItemLanes.model.GetLanesByItemRequest;
import ItemLanes.model.GetLanesByItemResponse;
import ItemLanes.model.S3Pointer;
import itemsreplenishment.module.DaggerServiceComponent;
import itemsreplenishment.module.ServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ItemLanesController implements RequestHandler<GetLanesByItemRequest, GetLanesByItemResponse> {

    private final DataFrameRepository dataFrameRepository;

    public ItemLanesController() {
        this(DaggerServiceComponent.create());
    }

    public ItemLanesController(
            final ServiceComponent serviceComponent) {
        dataFrameRepository = serviceComponent.provideDataFrameRepository();
    }

    @Override
    public GetLanesByItemResponse handleRequest(
            GetLanesByItemRequest input,
            Context context) {
        DataFrame dataFrame = dataFrameRepository.getMostRecentDataFrame("ItemLanes");

        return new GetLanesByItemResponse(S3Pointer.builder()
                .bucket("bucket")
                .key("key")
                .build(), dataFrame);
    }
}
