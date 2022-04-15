package io.surati.gap.gtp.base.module.secure;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Region;
import io.surati.gap.gtp.base.api.Regions;
import io.surati.gap.gtp.base.module.GtpBaseAccess;
import javax.ws.rs.NotAuthorizedException;

public final class SecRegions implements Regions {

    private final Regions origin;

    private final User user;

    public SecRegions(final Regions origin, final User user) {
        this.origin = origin;
        this.user = user;
    }

    @Override
    public Region get(final String code) {
        return this.origin.get(code);
    }

    @Override
    public Iterable<Region> iterate() {
        if(!user.profile().accesses().has(GtpBaseAccess.VISUALISER_REGIONS)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour lister les régions.");
        }
        return this.origin.iterate();
    }

    @Override
    public void add(String code, String name, String notes) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_REGIONS)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour ajouter une région.");
        }
        this.origin.add(code, name, notes);
    }

    @Override
    public void remove(final String code) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_REGIONS)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour supprimer une région.");
        }
        this.origin.remove(code);
    }

    @Override
    public boolean has(String code) {
        return this.origin.has(code);
    }
}
