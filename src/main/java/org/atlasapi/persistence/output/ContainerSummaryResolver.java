package org.atlasapi.persistence.output;

import org.atlasapi.media.entity.ParentRef;
import org.atlasapi.media.entity.simple.BrandSummary;
import org.atlasapi.media.entity.simple.SeriesSummary;

import com.google.common.base.Optional;

public interface ContainerSummaryResolver {

    Optional<BrandSummary> summarizeTopLevelContainer(ParentRef container);

    Optional<SeriesSummary> summarizeSeries(ParentRef series);

}