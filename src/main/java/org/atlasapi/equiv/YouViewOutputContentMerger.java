package org.atlasapi.equiv;

import com.google.common.base.Strings;
import com.metabroadcast.applications.client.model.internal.Application;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Quality;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class YouViewOutputContentMerger extends OutputContentMerger {

    /**
     * Before merging the versions to the chosen item, check which was chosen and make sure it was not the UHD
     * one as otherwise the chosen item will have the UDH description.
     *
     * Set again if needed and then merge as normal
     */
    @Override
    protected <T extends Item> void mergeIn(Application application, T chosen, Iterable<T> notChosen) {
        if (Publisher.AMAZON_UNBOX.equals(chosen.getPublisher())) {
            if (isNotUhd(chosen)) {
                // Should be fine
                return;
            }

            Optional<T> nonUhdItem = StreamSupport.stream(notChosen.spliterator(), false)
                    .filter(this::isNotUhd)
                    .filter(item -> !Strings.isNullOrEmpty(item.getDescription()))
                    .findFirst();

            nonUhdItem.ifPresent(item -> chosen.setDescription(item.getDescription()));
        }

        super.mergeIn(application, chosen, notChosen);
    }

    private boolean isNotUhd(Item item) {
        return item.getVersions().stream()
                .flatMap(version -> version.getManifestedAs().stream())
                .noneMatch(enc -> enc.getQuality().equals(Quality.FOUR_K));
    }
}
