package common.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;

@Module
public class AwsModule {

    @Singleton
    @Provides
    static AWSStepFunctions provideAWSStepFunctionsClient() {
        return AWSStepFunctionsClientBuilder.standard()
                .build();
    }

    @Singleton
    @Provides
    static AmazonS3 provideAmazonS3() {
        return AmazonS3ClientBuilder.standard()
                .build();
    }
}
