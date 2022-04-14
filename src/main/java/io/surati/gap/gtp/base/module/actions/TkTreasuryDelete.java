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

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.facets.flash.RsFlash;
import org.takes.facets.forward.RsForward;
import org.takes.rq.RqHref;

import io.surati.gap.admin.base.api.Log;
import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Treasuries;
import io.surati.gap.gtp.base.db.DbTreasuries;
import io.surati.gap.gtp.base.module.secure.SecTreasuries;
import io.surati.gap.web.base.log.RqLog;
import io.surati.gap.web.base.rq.RqUser;

/**
 * Take that deletes a treasury.
 *
 * <p>The class is immutable and thread-safe.</p>
 *
 * @since 0.1.4
 */
public final class TkTreasuryDelete implements Take {

	/**
	 * Database
	 */
	private final DataSource source;

	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkTreasuryDelete(final DataSource source) {
		this.source = source;
	}
	
	@Override
	public Response act(Request req) throws Exception {
		final Log log = new RqLog(this.source, req);
		final Long id = Long.parseLong(new RqHref.Smart(req).single("id"));
		final User user = new RqUser(this.source, req);
		final Treasuries items = new SecTreasuries(
			new DbTreasuries(source),
			user
		);
		items.remove(id);
		log.info("Suppression du poste comptable %s)", id);
		return new RsForward(
			new RsFlash(
				String.format("Le poste comptable %s a été supprimé avec succès !", id),
				Level.INFO
			),
			"/gtp/base/treasury"
		);	
	}
}
