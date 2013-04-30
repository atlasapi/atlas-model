package org.atlasapi.media.content;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.atlasapi.media.entity.Episode;
import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.base.Supplier;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.testing.EquivalenceTester;
import com.metabroadcast.common.time.DateTimeZones;

public class ContentEquivalenceTest {
    
    
    ClassToInstanceMap<Object> valueMap = ImmutableClassToInstanceMap.builder()
        .put(Integer.class, 1)
        .put(String.class, "string")
        .put(DateTime.class, new DateTime(DateTimeZones.UTC))
        .build();
    
    @Test
    public void testEpisodeEquivalent() throws Exception {
        Supplier<Episode> supplier = contentSupplier();
        EquivalenceTester<Content> tester = EquivalenceTester.of(new ContentEquivalence());
        tester.addEquivalenceGroup(supplier.get(), supplier.get());
        List<Method> methods = Lists.newLinkedList();
        for(Method newMethod : Episode.class.getMethods()) {
            if (newMethod.getName().startsWith("setD")
            || newMethod.getName().startsWith("setY")
            || newMethod.getName().startsWith("setL")) {
                methods.add(newMethod);
                System.out.println(methods);
                Episode left = supplier.get();
                Episode right = supplier.get();
                for(Method method : methods) {
                    Object[] methodValues = valuesFor(method);
                    method.invoke(left, methodValues);
                    method.invoke(right, methodValues);
                }
                tester.addEquivalenceGroup(left, right);
            }
        }
        tester.test();
    }

    private Object[] valuesFor(Method method) {
        Type[] types = method.getGenericParameterTypes();
        Object[] values = new Object[types.length];
        for(int i = 0; i < types.length; i++) {
            Type type = types[i];
            Object valueForType = null;
            if (type instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) type;
                if (Iterable.class.isAssignableFrom((Class<?>)pType.getRawType())) {
                    valueForType = ImmutableSet.of(valueMap.get(pType.getActualTypeArguments()[0]));
                }
            } else {
                valueForType = valueMap.get(type);
            }
            checkNotNull(valueForType, "%s needs %s %s", method.getName(), type);
            values[i] = valueForType;
        }
        return values;
    }
    
    private Supplier<Episode> contentSupplier() {
        return new Supplier<Episode>() {
            int i = 0;
            @Override
            public Episode get() {
                Episode ep = new Episode();
                ep.setId(i++);
                return ep;
            }
        };
    }

    @Test
    public void testHash() {
//        fail("Not yet implemented");
    }

}
