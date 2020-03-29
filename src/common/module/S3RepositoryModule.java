package common.module;

import ItemLanes.dataaccess.s3.ItemLanesS3Repository;
import ItemLanes.dataaccess.s3.ItemLanesS3RepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import com.amazonaws.services.s3.AmazonS3;

@Module
public class S3RepositoryModule {

    public static final String ITEM_LANES_BUCKET_NAME = "ITEM_LANES_BUCKET_NAME";

    @Provides
    @Singleton
    public ItemLanesS3Repository provideItemLanesRepository(
            final AmazonS3 amazonS3) {
        ObjectMapper objectMapper = new ObjectMapper();
        return new ItemLanesS3RepositoryImpl(amazonS3, System.getenv(ITEM_LANES_BUCKET_NAME), objectMapper);
    }
}
