package io.surati.gap.gtp.base.module.secure;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Bundle;
import io.surati.gap.gtp.base.api.Bundles;
import io.surati.gap.gtp.base.module.GtpBaseAccess;

public final class SecBundles implements Bundles {

    private final Bundles origin;

    private final User user;

    public SecBundles(final Bundles origin, final User user) {
        this.origin = origin;
        this.user = user;
    }

    @Override
    public Bundle get(final String code) {
        return this.origin.get(code);
    }

    @Override
    public Iterable<Bundle> iterate() {
        return this.origin.iterate();
    }

    @Override
    public void add(final String code, final String notes) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_LIASSES)) {
            throw new IllegalArgumentException("Vos droits d’accès sont insuffisants pour mener cette action.");
        }
        this.origin.add(code, notes);
    }

    @Override
    public void remove(final String code) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_LIASSES)) {
            throw new IllegalArgumentException("Vos droits d’accès sont insuffisants pour mener cette action.");
        }
        this.origin.remove(code);
    }

    @Override
    public boolean has(String code) {
        return this.origin.has(code);
    }
}
