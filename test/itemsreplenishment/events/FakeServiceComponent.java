package itemsreplenishment.events;

import static org.mockito.Mockito.mock;

import ItemLanes.dataaccess.DataFrameRepository;
import ItemLanes.dataaccess.ItemLanesRepository;
import ItemLanes.dataaccess.s3.ItemLanesS3Repository;
import itemsreplenishment.module.ServiceComponent;

import com.amazonaws.services.stepfunctions.AWSStepFunctions;

public class FakeServiceComponent implements ServiceComponent {

    private final AWSStepFunctions awsStepFunctions = mock(AWSStepFunctions.class);

    @Override
    public AWSStepFunctions getAWSStepFunctions() {
        return awsStepFunctions;
    }

    @Override
    public DataFrameRepository provideDataFrameRepository() {
        return null;
    }

    @Override
    public ItemLanesRepository provideItemLanesRepository() {
        return null;
    }

    @Override
    public ItemLanesS3Repository provideItemLanesS3Repository() {
        return null;
    }
}
