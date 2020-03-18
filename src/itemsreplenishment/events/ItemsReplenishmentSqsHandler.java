package itemsreplenishment.events;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import itemsreplenishment.DummyProxy;
import itemsreplenishment.module.DaggerServiceComponent;
import itemsreplenishment.module.ServiceComponent;

public class ItemsReplenishmentSqsHandler implements RequestHandler<SQSEvent, Void> {

    private final DummyProxy dummyProxy;

    public ItemsReplenishmentSqsHandler() {
        this(DaggerServiceComponent.create());
    }

    public ItemsReplenishmentSqsHandler(ServiceComponent serviceComponent) {
        this.dummyProxy = serviceComponent.getDummyProxy();
    }

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        for (SQSMessage msg : event.getRecords()) {
            boolean worked = this.dummyProxy.saveProxyValue();
            System.out.println(msg.getBody() + " Worked:" + worked);
        }
        return null;
    }
}
