package itemsreplenishment.events;

import itemsreplenishment.DummyProxy;
import itemsreplenishment.module.ServiceComponent;

import static org.mockito.Mockito.mock;

public class FakeServiceComponent implements ServiceComponent {
    private final DummyProxy dummyProxy = mock(DummyProxy.class);

    @Override
    public DummyProxy getDummyProxy() {
        return dummyProxy;
    }
}
