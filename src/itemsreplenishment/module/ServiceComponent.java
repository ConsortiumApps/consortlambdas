package itemsreplenishment.module;

import ItemLanes.dataaccess.DataFrameRepository;
import ItemLanes.module.DynamoDbRepositoryModule;
import common.module.AwsModule;
import common.module.DynamodbModule;
import dagger.Component;
import javax.inject.Singleton;

import com.amazonaws.services.stepfunctions.AWSStepFunctions;

@Singleton
@Component(modules = { AwsModule.class, DynamodbModule.class, DynamoDbRepositoryModule.class })
public interface ServiceComponent {

    AWSStepFunctions getAWSStepFunctions();

    DataFrameRepository provideDataFrameRepository();
}
