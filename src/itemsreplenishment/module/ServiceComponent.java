package itemsreplenishment.module;

import dagger.Component;
import itemsreplenishment.DummyProxy;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DummyModule.class})
public interface ServiceComponent {

    DummyProxy getDummyProxy();
}
