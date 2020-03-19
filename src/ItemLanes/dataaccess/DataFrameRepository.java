package ItemLanes.dataaccess;

import java.util.List;

import ItemLanes.model.DataFrame;
import ItemLanes.model.data.DataFramesTable;

public interface DataFrameRepository {

    DataFrame getMostRecentDataFrame(String dataSourceName);

    List<DataFrame> getMostRecentDataFrames(String dataSourceName);

    void saveDataFrame(DataFrame dataFrame);
}
