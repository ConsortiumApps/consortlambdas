package itemsreplenishment.events;

import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import itemsreplenishment.module.ServiceComponent;

import static org.mockito.Mockito.mock;

public class FakeServiceComponent implements ServiceComponent {
    private final AWSStepFunctions awsStepFunctions = mock(AWSStepFunctions.class);

    @Override
    public AWSStepFunctions getAWSStepFunctions() {
        return awsStepFunctions;
    }
}
