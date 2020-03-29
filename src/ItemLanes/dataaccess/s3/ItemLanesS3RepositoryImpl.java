package ItemLanes.dataaccess.s3;

import static com.google.common.collect.ImmutableListMultimap.toImmutableListMultimap;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ItemLanes.model.DataFrame;
import ItemLanes.model.ItemLaneIdentifier;
import ItemLanes.model.Lane;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableListMultimap;
import common.model.S3Pointer;
import lombok.AllArgsConstructor;

import com.amazonaws.services.s3.AmazonS3;

@AllArgsConstructor
public class ItemLanesS3RepositoryImpl implements ItemLanesS3Repository {

    private final AmazonS3 amazonS3;
    private final String bucket;
    private final ObjectMapper objectMapper;

    @Override
    public Map<String, S3Pointer> saveItemLanesByItem(
            List<ItemLaneIdentifier> itemLaneIdentifiers,
            DataFrame dataFrame) throws JsonProcessingException {

        ImmutableListMultimap<String, Lane> itemLaneMap = itemLaneIdentifiers.stream()
                .collect(toImmutableListMultimap(ItemLaneIdentifier::getItem, ItemLaneIdentifier::getLane));

        Map<String, Collection<Lane>> itemLanes = itemLaneMap.asMap();
        Map<String, S3Pointer> itemLanesS3Map = new HashMap<>();
        for (Map.Entry<String, Collection<Lane>> itemLane : itemLanes.entrySet()) {
            String itemLanesStr = objectMapper.writeValueAsString(itemLane);
            S3Pointer s3Pointer = generateS3Pointer(dataFrame, itemLane.getKey());
            amazonS3.putObject(bucket, s3Pointer.getKey(), itemLanesStr);
            itemLanesS3Map.put(itemLane.getKey(), s3Pointer);
        }

        return itemLanesS3Map;
    }

    private S3Pointer generateS3Pointer(
            DataFrame dataFrame,
            String item) {
        final String s3Location = MessageFormat.format(
                "{0}/{1}/{2}/{3}/{4}", dataFrame.getCreationDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy")), dataFrame.getCreationDateTime()
                        .toLocalDate()
                        .getMonthValue(), dataFrame.getCreationDateTime()
                        .toLocalDate()
                        .getDayOfMonth(), dataFrame.getFrameId()
                        .toString(), item);

        return S3Pointer.builder()
                .bucket(bucket)
                .key(s3Location)
                .build();
    }

    @Override
    public S3Pointer getItemLanesS3Pointer(
            String item,
            DataFrame dataFrame) {
        S3Pointer s3Pointer = generateS3Pointer(dataFrame, item);
        boolean doesObjectExist = amazonS3.doesObjectExist(s3Pointer.getBucket(), s3Pointer.getKey());
        return doesObjectExist
               ? s3Pointer
               : null;
    }
}
