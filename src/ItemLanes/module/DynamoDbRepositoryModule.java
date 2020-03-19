package ItemLanes.module;

import ItemLanes.dataaccess.DataFrameRepository;
import ItemLanes.dataaccess.dynamodb.DataFrameRepositoryImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

@Module
public class DynamoDbRepositoryModule {


}
