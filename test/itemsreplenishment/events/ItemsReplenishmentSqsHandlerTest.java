package itemsreplenishment.events;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.google.common.collect.Lists;
import itemsreplenishment.DummyProxy;
import itemsreplenishment.module.ServiceComponent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Strict.class)
public class ItemsReplenishmentSqsHandlerTest {
    private final String VALID_JSON = "{\n" +
            "  \"work\": \"newjob\"\n" +
            "}";
    DummyProxy dummyProxy;
    private ServiceComponent serviceComponent;
    private ItemsReplenishmentSqsHandler itemsReplenishmentSqsHandler;

    @Before
    public void Setup() {
        this.serviceComponent = new FakeServiceComponent();
        this.dummyProxy = this.serviceComponent.getDummyProxy();
        this.itemsReplenishmentSqsHandler = new ItemsReplenishmentSqsHandler(this.serviceComponent);
    }

    @Test
    public void handleRequest() {
        SQSEvent sqsEvent = new SQSEvent();
        SQSEvent.SQSMessage sqsMessage = new SQSEvent.SQSMessage();
        sqsMessage.setBody(VALID_JSON);

        sqsEvent.setRecords(Lists.newArrayList(sqsMessage));
        when(this.dummyProxy.saveProxyValue()).thenReturn(false);
        itemsReplenishmentSqsHandler.handleRequest(sqsEvent, null);
    }


}