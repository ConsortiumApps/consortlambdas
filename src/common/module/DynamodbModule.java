package common.module;

import ItemLanes.dataaccess.DataFrameRepository;
import ItemLanes.dataaccess.dynamodb.DataFrameRepositoryImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

@Module
public class DynamodbModule {

    public static final String DATA_FRAME_TABLE_NAME = "DATA_FRAME_TABLE_NAME";

    @Provides
    @Singleton
    public AmazonDynamoDB providesDynamoDb() {
        return AmazonDynamoDBClientBuilder.standard()
                .build();
    }

    @Provides
    @Singleton
    public DataFrameRepository provideDataFrameRepository(
            final AmazonDynamoDB amazonDynamoDBClient) {
        final DynamoDBMapper dynamoDBMapper = buildDynamoDBWrapper(System.getenv(DATA_FRAME_TABLE_NAME), amazonDynamoDBClient);
        return new DataFrameRepositoryImpl(dynamoDBMapper);
    }

    private DynamoDBMapper buildDynamoDBWrapper(
            final String tableName,
            final AmazonDynamoDB amazonDynamoDB) {
        final DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement(tableName))
                .build();
        return new DynamoDBMapper(amazonDynamoDB, mapperConfig);
    }
}
