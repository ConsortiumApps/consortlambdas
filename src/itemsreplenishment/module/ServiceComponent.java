package itemsreplenishment.module;

import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AwsModule.class})
public interface ServiceComponent {

    AWSStepFunctions getAWSStepFunctions();
}
