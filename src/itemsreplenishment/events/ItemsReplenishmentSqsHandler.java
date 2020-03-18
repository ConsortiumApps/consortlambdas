package itemsreplenishment.events;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.amazonaws.services.stepfunctions.model.StartExecutionResult;
import itemsreplenishment.module.DaggerServiceComponent;
import itemsreplenishment.module.ServiceComponent;

public class ItemsReplenishmentSqsHandler implements RequestHandler<SQSEvent, Void> {
    private final AWSStepFunctions awsStepFunctions;

    public ItemsReplenishmentSqsHandler() {
        this(DaggerServiceComponent.create());
    }

    public ItemsReplenishmentSqsHandler(ServiceComponent serviceComponent) {
        this.awsStepFunctions = serviceComponent.getAWSStepFunctions();
    }

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        for (SQSMessage msg : event.getRecords()) {
            if(msg.getBody().contains("exec")) {
                StartExecutionRequest startExecutionRequest = new StartExecutionRequest();
                startExecutionRequest.setStateMachineArn(System.getenv("STATE_MACHINE_ARN"));
                startExecutionRequest.setInput("{\n" +
                        "  \"test\": \"Workednew\",\n" +
                        "  \"hello\": \"World\"\n" +
                        "}");
                StartExecutionResult executionResult = awsStepFunctions.startExecution(startExecutionRequest);
                System.out.println("executionResult.getExecutionArn1() = " + executionResult.getExecutionArn());
            }
            else {
                System.out.println(" Not creating step function 1 " + msg.getBody());
            }
        }
        return null;
    }
}
