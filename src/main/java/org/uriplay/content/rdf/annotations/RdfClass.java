/* Copyright 2009 British Broadcasting Corporation
 
Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

package org.uriplay.content.rdf.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * FIXME: RdfClasses are currently treated as, potentially, a 
 * FIXME  combination of RDF types; with a Java class representing
 * FIXME  a particular combination.  This should be altered such
 * FIXME  that a Java class represents the storage structure of
 * FIXME  of the entity, but the actual inheritance is independent;
 * FIXME  to allow for multiple inheritance, mutable typing, etc.
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface RdfClass {
    String namespace() default "";
    String uri() default "";
}
