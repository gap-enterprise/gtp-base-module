package io.surati.gap.gtp.base.module.secure;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Bundle;
import io.surati.gap.gtp.base.module.GtpBaseAccess;
import javax.ws.rs.NotAuthorizedException;

public final class SecBundle implements Bundle {

    private final Bundle origin;

    private final User user;

    public SecBundle(final Bundle origin, final User user) {
        this.origin = origin;
        this.user = user;
    }

    @Override
    public String code() {
        return this.origin.code();
    }

    @Override
    public String notes() {
        return this.origin.notes();
    }

    @Override
    public void update(String notes) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_LIASSES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour modifier une liasse.");
        }
        this.origin.update(notes);
    }
}
