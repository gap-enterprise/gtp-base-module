package io.surati.gap.gtp.base.module.secure;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Title;
import io.surati.gap.gtp.base.api.Titles;
import io.surati.gap.gtp.base.module.GtpBaseAccess;

public final class SecTitles implements Titles {

    private final Titles origin;

    private final User user;

    public SecTitles(final Titles origin, final User user) {
        this.origin = origin;
        this.user = user;
    }

    @Override
    public Title get(final String code) {
        return this.origin.get(code);
    }

    @Override
    public Iterable<Title> iterate() {
        return this.origin.iterate();
    }

    @Override
    public void add(String code, String name, String notes) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_TITRES)) {
            throw new IllegalArgumentException("Vos droits d’accès sont insuffisants pour mener cette action.");
        }
        this.origin.add(code, name, notes);
    }

    @Override
    public void remove(final String code) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_TITRES)) {
            throw new IllegalArgumentException("Vos droits d’accès sont insuffisants pour mener cette action.");
        }
        this.origin.remove(code);
    }

    @Override
    public boolean has(String code) {
        return this.origin.has(code);
    }
}
