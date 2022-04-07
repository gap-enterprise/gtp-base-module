package io.surati.gap.gtp.base.module.secure;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Section;
import io.surati.gap.gtp.base.api.Sections;
import io.surati.gap.gtp.base.module.GtpBaseAccess;

public final class SecSections implements Sections {

    private final Sections origin;

    private final User user;

    public SecSections(final Sections origin, final User user) {
        this.origin = origin;
        this.user = user;
    }

    @Override
    public Section get(final String code) {
        return this.origin.get(code);
    }

    @Override
    public Iterable<Section> iterate() {
        return this.origin.iterate();
    }

    @Override
    public void add(String code, String name, String notes) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_SECTIONS)) {
            throw new IllegalArgumentException("Vos droits d’accès sont insuffisants pour mener cette action.");
        }
        this.origin.add(code, name, notes);
    }

    @Override
    public void remove(final String code) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_SECTIONS)) {
            throw new IllegalArgumentException("Vos droits d’accès sont insuffisants pour mener cette action.");
        }
        this.origin.remove(code);
    }

    @Override
    public boolean has(String code) {
        return this.origin.has(code);
    }
}
