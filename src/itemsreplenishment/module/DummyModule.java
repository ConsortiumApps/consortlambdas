package itemsreplenishment.module;

import dagger.Module;
import dagger.Provides;
import itemsreplenishment.DummyProxy;
import itemsreplenishment.DummyProxyImpl;

import javax.inject.Singleton;

@Module
public class DummyModule {

    @Provides
    @Singleton
    static DummyProxy provideDummyProxy(){
        return new DummyProxyImpl();
    }
}
