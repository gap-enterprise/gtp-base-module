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
package io.surati.gap.gtp.base.module.actions;

import java.util.logging.Level;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.facets.flash.RsFlash;
import org.takes.facets.forward.RsForward;
import org.takes.rq.RqGreedy;
import org.takes.rq.RqHref;
import org.takes.rq.form.RqFormSmart;

import io.surati.gap.admin.base.api.Log;
import io.surati.gap.gtp.base.api.Treasuries;
import io.surati.gap.gtp.base.api.Treasury;
import io.surati.gap.gtp.base.db.DbTreasuries;
import io.surati.gap.gtp.base.module.secure.SecTreasuries;
import io.surati.gap.web.base.log.RqLog;
import io.surati.gap.web.base.rq.RqUser;

/**
 * Take that creates or modifies a treasury.
 *
 * <p>The class is immutable and thread-safe.</p>
 *
 * @since 0.1.4
 */

public final class TkTreasurySave implements Take {

	/**
	 * Database
	 */
	private final DataSource source;

	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkTreasurySave(final DataSource source) {
		this.source = source;
	}
		
	@Override
	public Response act(final Request req) throws Exception {
		final RqGreedy rqg = new RqGreedy(req);
		final Log log = new RqLog(this.source, rqg);
		final boolean tocreate = StringUtils.isBlank(
			new RqHref.Smart(rqg).single("id", "0")
		);
		final RqFormSmart form = new RqFormSmart(rqg);
		final Long id = Long.parseLong(form.single("id"));
		final String code = form.single("code");
		final String name = form.single("name");
		final String representative = form.single("representative", "");
		final String representativeposition = form.single("representative_position", "");
		final String abbreviated = form.single("abbreviated", "");
		final Treasuries items = new SecTreasuries(
			new DbTreasuries(this.source),
			new RqUser(this.source, req)
		);
		final String msg;
		if(tocreate) {
			//items.add(code, name);
			msg = String.format("Le poste comptable %s a été créé avec succès !", abbreviated);
			log.info("Ajout du poste comptable %s - %s", code, abbreviated);
		} else {
			final Treasury item = items.get(id);
			final String oldname = item.name();
			final String oldabbreviated = item.abbreviated();
			final String oldcode = item.code();
			item.update(code, name, abbreviated);;
			msg = String.format("Le poste comptable %s a été modifié avec succès !", abbreviated);
			log.info(
				"Modification du poste comptable %s: (Abrégé=%s,Libellé=%s) en %s (Abrégé=%s,Libellé=%s)",
				oldcode, oldabbreviated, oldname, code, abbreviated, name
			);
		}
		return new RsForward(
			new RsFlash(
				msg,
				Level.INFO
			),
			String.format("/gtp/base/treasury/view?id=%s", id)
		);	
	}
}
