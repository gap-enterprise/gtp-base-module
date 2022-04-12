package io.surati.gap.gtp.base.module.secure;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Section;
import io.surati.gap.gtp.base.module.GtpBaseAccess;
import javax.ws.rs.NotAuthorizedException;

public final class SecSection implements Section {

    private final Section origin;

    private final User user;

    public SecSection(final Section origin, final User user) {
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
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_SECTIONS)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour modifier une section.");
        }
        this.origin.update(name, notes);
    }
}
