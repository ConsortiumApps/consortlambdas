package itemsreplenishment.module;

import ItemLanes.dataaccess.DataFrameRepository;
import ItemLanes.dataaccess.ItemLanesRepository;
import ItemLanes.dataaccess.s3.ItemLanesS3Repository;
import ItemLanes.module.DynamoDbRepositoryModule;
import common.module.AwsModule;
import common.module.DynamodbModule;
import common.module.S3RepositoryModule;
import dagger.Component;
import javax.inject.Singleton;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;

@Singleton
@Component(modules = { AwsModule.class, DynamodbModule.class, DynamoDbRepositoryModule.class, S3RepositoryModule.class })
public interface ServiceComponent {

    AWSStepFunctions getAWSStepFunctions();

    DataFrameRepository provideDataFrameRepository();

    ItemLanesRepository provideItemLanesRepository();

    ItemLanesS3Repository provideItemLanesS3Repository();
}
