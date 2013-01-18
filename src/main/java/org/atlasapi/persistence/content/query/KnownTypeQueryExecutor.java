/* Copyright 2009 Meta Broadcast Ltd

Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

package org.atlasapi.persistence.content.query;

import java.util.List;
import java.util.Map;

import org.atlasapi.content.criteria.ContentQuery;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Identified;

import com.google.common.base.Optional;

public interface KnownTypeQueryExecutor {

    /**
     * 
     * @param uris
     *            - content identifiers.
     * @param query
     *            - additional parameters.
     * @return map from <b>canonical URI</b> to list of equivalent content for
     *         that URI. N.B. if an alias (a non-canonical identifier) is
     *         requested that alias will not be a key in the returned map.
     */
    Map<Id, List<Identified>> executeUriQuery(Iterable<String> uris, ContentQuery query);

    Map<Id, List<Identified>> executeIdQuery(Iterable<Id> ids, ContentQuery query);

    Map<String, List<Identified>> executeAliasQuery(Optional<String> namespace, Iterable<String> values, ContentQuery query);
}