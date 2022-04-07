/**
MIT License

Copyright (c) 2021 Surati

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */  
package io.surati.gap.gtp.base.module.actions;

import io.surati.gap.admin.base.api.Log;
import io.surati.gap.gtp.base.api.Bundle;
import io.surati.gap.gtp.base.api.Bundles;
import io.surati.gap.gtp.base.db.DbBundles;
import io.surati.gap.gtp.base.module.secure.SecBundles;
import io.surati.gap.web.base.log.RqLog;
import io.surati.gap.web.base.rq.RqUser;
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

/**
 * Take that creates or modifies a bundle.
 *
 * <p>The class is immutable and thread-safe.</p>
 *
 * @since 3.0
 */


public final class TkBundleSave implements Take {

	/**
	 * Database
	 */
	private final DataSource source;

	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkBundleSave(final DataSource source) {
		this.source = source;
	}
	
	@Override
	public Response act(final Request req) throws Exception {
		final RqGreedy rqg = new RqGreedy(req);
		final Log log = new RqLog(this.source, rqg);
		final boolean tocreate = StringUtils.isBlank(
			new RqHref.Smart(rqg).single("code", "")
		);
		final RqFormSmart form = new RqFormSmart(rqg);
		final String code = form.single("code");
		final String notes = form.single("notes", "");
		final Bundles items = new SecBundles(
			new DbBundles(this.source),
			new RqUser(this.source, req)
		);
		final String msg;
		if(tocreate) {
			items.add(code, notes);
			msg = String.format("La liasse %s a été créée avec succès !", code);
			log.info("Ajout de la liasse %s", code);
		} else {
			final Bundle item = items.get(code);
			final String oldnotes = item.notes();
			item.update(notes);
			msg = String.format("La liasse %s a été modifiée avec succès !", code);
			log.info(
				"Modification de la liasse %s: Notes=%s en Notes=%s",
				code, notes, oldnotes
			);
		}
		return new RsForward(
			new RsFlash(
				msg,
				Level.INFO
			),
			String.format("/gtp/base/bundle/view?code=%s", code)
		);	
	}
}
