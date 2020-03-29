package ItemLanes.dataaccess;

import java.util.List;

import ItemLanes.model.DataFrame;

public interface DataFrameRepository {

    DataFrame getMostRecentDataFrame(String dataSourceName);

    List<DataFrame> getMostRecentDataFrames(String dataSourceName);

    void saveDataFrame(DataFrame dataFrame);
}
