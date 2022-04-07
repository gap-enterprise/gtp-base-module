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
import io.surati.gap.gtp.base.api.Title;
import io.surati.gap.gtp.base.api.Titles;
import io.surati.gap.gtp.base.db.DbTitles;
import io.surati.gap.gtp.base.module.secure.SecTitles;
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
 * Take that creates or modifies a title.
 *
 * <p>The class is immutable and thread-safe.</p>
 *
 * @since 3.0
 */


public final class TkTitleSave implements Take {

	/**
	 * Database
	 */
	private final DataSource source;

	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkTitleSave(final DataSource source) {
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
		final String name = form.single("name");
		final String notes = form.single("notes", "");
		final Titles items = new SecTitles(
			new DbTitles(this.source),
			new RqUser(this.source, req)
		);
		final String msg;
		if(tocreate) {
			items.add(code, name, notes);
			msg = String.format("Le titre %s a été créé avec succès !", code);
			log.info("Ajout du titre %s - %s", code, name);
		} else {
			final Title item = items.get(code);
			final String oldname = item.name();
			final String oldnote = item.notes();
			item.update(name, notes);
			msg = String.format("Le titre %s a été modifié avec succès !", code);
			log.info(
				"Modification du titre %s: (Libellé=%s,Notes=%s) en (Libellé=%s,Notes=%s)",
				code, oldname, oldnote, name, notes
			);
		}
		return new RsForward(
			new RsFlash(
				msg,
				Level.INFO
			),
			String.format("/gtp/base/title/view?code=%s", code)
		);	
	}
}
