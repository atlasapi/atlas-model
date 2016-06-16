package org.atlasapi.application.v3;

import com.google.common.collect.ImmutableSet;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class ApplicationAccessRoleTest {

    @Rule public ExpectedException exception = ExpectedException.none();

    @Test
    public void fromValidRole() throws Exception {
        ApplicationAccessRole expected = ApplicationAccessRole.OWL_ACCESS;
        ApplicationAccessRole actual = ApplicationAccessRole.from(expected.getRole());

        assertThat(actual, sameInstance(expected));
    }

    @Test
    public void fromInvalidRole() throws Exception {
        exception.expect(IllegalArgumentException.class);
        ApplicationAccessRole.from("invalid-role");
    }

    @Test
    public void defaultRolesAreTheExpected() throws Exception {
        ImmutableSet<ApplicationAccessRole> expected = ImmutableSet.of(ApplicationAccessRole.DEER_ACCESS);
        ImmutableSet<ApplicationAccessRole> actual = ApplicationAccessRole.getDefaultRoles();

        assertThat(actual.size(), is(expected.size()));
        assertThat(
                actual.containsAll(expected),
                is(true)
        );
    }
}
