package io.surati.gap.gtp.base.module.secure;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Title;
import io.surati.gap.gtp.base.api.Titles;
import io.surati.gap.gtp.base.module.GtpBaseAccess;
import javax.ws.rs.NotAuthorizedException;

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
        if(!user.profile().accesses().has(GtpBaseAccess.VISUALISER_CHAPITRES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour lister les titres.");
        }
        return this.origin.iterate();
    }

    @Override
    public void add(String code, String name, String notes) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_TITRES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour ajouter un titre.");
        }
        this.origin.add(code, name, notes);
    }

    @Override
    public void remove(final String code) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_TITRES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour supprimer un titre.");
        }
        this.origin.remove(code);
    }

    @Override
    public boolean has(String code) {
        return this.origin.has(code);
    }
}
