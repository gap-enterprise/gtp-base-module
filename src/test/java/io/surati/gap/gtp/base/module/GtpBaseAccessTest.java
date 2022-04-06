package io.surati.gap.gtp.base.module;

import io.surati.gap.admin.base.api.Access;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link GtpBaseAccess}.
 *
 * @since 0.1
 */
final class GtpBaseAccessTest {

    @Test
    void containsRight() {
        MatcherAssert.assertThat(
            Access.VALUES,
            Matchers.hasItem(GtpBaseAccess.VISUALISER_PAIEMENTS)
        );
    }
}
