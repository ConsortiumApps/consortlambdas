package itemsreplenishment.module;

import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AwsModule {
    @Singleton
    @Provides
    static AWSStepFunctions provideAWSStepFunctionsClient() {
        return AWSStepFunctionsClientBuilder.standard().build();
    }
}
