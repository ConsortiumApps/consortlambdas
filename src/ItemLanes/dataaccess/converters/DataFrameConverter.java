package ItemLanes.dataaccess.converters;

import ItemLanes.model.DataFrame;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class DataFrameConverter implements DynamoDBTypeConverter<String, DataFrame> {

    public static final String DATA_FRAME_SEPARATOR = "_";
    public static final int DATA_SOURCE_INDEX = 1;
    public static final int FRAME_ID_INDEX = 0;

    @Override
    public String convert(DataFrame dataFrame) {
        return dataFrame.getFrameId() + DATA_FRAME_SEPARATOR + dataFrame.getDataSource();
    }

    @Override
    public DataFrame unconvert(String dataFrame) {
        String[] dataFrameParts = dataFrame.split(DATA_FRAME_SEPARATOR);
        return DataFrame.builder()
                .dataSource(dataFrameParts[DATA_SOURCE_INDEX])
                .frameId(dataFrameParts[FRAME_ID_INDEX])
                .build();
    }
}
