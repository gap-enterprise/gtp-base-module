package io.surati.gap.gtp.base.module.secure;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Title;
import io.surati.gap.gtp.base.module.GtpBaseAccess;

public final class SecTitle implements Title {

    private final Title origin;

    private final User user;

    public SecTitle(final Title origin , final User user) {
        this.origin = origin;
        this.user = user;
    }

    @Override
    public String code() {
        return this.origin.code();
    }

    @Override
    public String name() {
        return this.origin.name();
    }

    @Override
    public String notes() {
        return this.origin.notes();
    }

    @Override
    public String fullName() {
        return this.origin.fullName();
    }

    @Override
    public void update(final String name, final String notes) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_TITRES)) {
            throw new IllegalArgumentException("Vos droits d’accès sont insuffisants pour mener cette action.");
        }
        this.origin.update(name, notes);
    }
}
