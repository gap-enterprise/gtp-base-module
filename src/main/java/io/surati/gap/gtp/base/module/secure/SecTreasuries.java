/*
 * Copyright (c) 2022 Surati

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.surati.gap.gtp.base.module.secure;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Treasuries;
import io.surati.gap.gtp.base.api.Treasury;
import io.surati.gap.gtp.base.module.GtpBaseAccess;
import javax.ws.rs.NotAuthorizedException;

public final class SecTreasuries implements Treasuries {

    private final Treasuries origin;

    private final User user;

    public SecTreasuries(final Treasuries origin, final User user) {
        this.origin = origin;
        this.user = user;
    }

	@Override
	public Long count() {
		return this.origin.count();
	}

	@Override
	public Treasury get(Long id) {
		return this.origin.get(id);
	}

	@Override
	public void remove(Long id) {
		if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_PAIERIES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour supprimer un poste comptable.");
        }
        this.origin.remove(id);
	}

	@Override
	public Iterable<Treasury> iterate() {
		if(!user.profile().accesses().has(GtpBaseAccess.VISUALISER_PAIERIES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour lister les postes comptables.");
        }
        return this.origin.iterate();
	}

	@Override
	public Treasury add(String code, String name, String abbreviated) {
		if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_PAIERIES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour ajouter un poste comptable.");
        }
        return this.origin.add(code, name, abbreviated);
	}
}
