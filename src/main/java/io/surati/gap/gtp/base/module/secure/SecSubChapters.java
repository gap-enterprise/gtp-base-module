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

import javax.ws.rs.NotAuthorizedException;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Chapter;
import io.surati.gap.gtp.base.api.Chapters;
import io.surati.gap.gtp.base.module.GtpBaseAccess;

public final class SecSubChapters implements Chapters {

    private final Chapters origin;

    private final User user;

    public SecSubChapters(final Chapters origin, final User user) {
        this.origin = origin;
        this.user = user;
    }

    @Override
    public Chapter get(final String code) {
        return this.origin.get(code);
    }

    @Override
    public Iterable<Chapter> iterate() {
        if(!user.profile().accesses().has(GtpBaseAccess.VISUALISER_SOUS_CHAPITRES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour lister les sous-chapitres.");
        }
        return this.origin.iterate();
    }

    @Override
    public void add(String code, String name, String notes) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_SOUS_CHAPITRES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour ajouter un sous-chapitre.");
        }
        this.origin.add(code, name, notes);
    }

    @Override
    public void remove(final String code) {
        if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_SOUS_CHAPITRES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour supprimer un sous-chapitre.");
        }
        this.origin.remove(code);
    }

    @Override
    public boolean has(String code) {
        return this.origin.has(code);
    }
}
