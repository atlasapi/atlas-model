package org.atlasapi.application.v3;

import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ApplicationConfigurationTest {

    private ApplicationConfiguration.Builder configurationBuilder;

    @Before
    public void setUp() throws Exception {
        configurationBuilder = ApplicationConfiguration.builder()
                .withSourceStatuses(ImmutableMap.of(
                        Publisher.METABROADCAST,
                        SourceStatus.AVAILABLE_ENABLED
                ))
                .withPrecedence(ImmutableList.of(Publisher.METABROADCAST))
                .withWritableSources(ImmutableSet.of())
                .withImagePrecedenceEnabled(false)
                .withContentHierarchyPrecedence(Optional.absent());
    }

    @Test
    public void configurationHasGivenRole() throws Exception {
        ApplicationAccessRole expectedRole = ApplicationAccessRole.OWL_ACCESS;

        ApplicationConfiguration configuration = configurationBuilder
                .withAccessRoles(ImmutableSet.of(expectedRole))
                .build();

        assertThat(
                configuration.hasAccessRole(expectedRole),
                is(true)
        );
    }

    @Test
    public void configurationHasDefaultRoleInAdditionToGiven() throws Exception {
        ApplicationConfiguration configuration = configurationBuilder
                .withAccessRoles(ImmutableSet.of(ApplicationAccessRole.OWL_ACCESS))
                .build();

        for (ApplicationAccessRole defaultRole : ApplicationAccessRole.getDefaultRoles()) {
            assertThat(
                    configuration.hasAccessRole(defaultRole),
                    is(true)
            );
        }
    }

    @Test
    public void configurationWithNoGivenRolesStillHasDefaultRoles() throws Exception {
        ApplicationConfiguration configuration = configurationBuilder
                .build();

        for (ApplicationAccessRole defaultRole : ApplicationAccessRole.getDefaultRoles()) {
            assertThat(
                    configuration.hasAccessRole(defaultRole),
                    is(true)
            );
        }
    }

    @Test
    public void configurationCanHaveDefaultRoleExplicitlyGiven() throws Exception {
        ApplicationAccessRole defaultRole = ApplicationAccessRole.DEER_ACCESS;

        ApplicationConfiguration configuration = configurationBuilder
                .withAccessRoles(ImmutableSet.of(defaultRole))
                .build();

        assertThat(
                configuration.hasAccessRole(defaultRole),
                is(true)
        );
    }

    @Test
    public void configurationCanBeBuiltWithoutNullableFieldsSetToNull() throws Exception {
        configurationBuilder
                .withPrecedence(null)
                .withImagePrecedenceEnabled(null)
                .build();
    }
}
