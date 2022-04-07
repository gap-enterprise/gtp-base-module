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
package io.surati.gap.gtp.base.module.pages;

import io.surati.gap.gtp.base.db.DbSections;
import io.surati.gap.gtp.base.module.xe.XeSection;
import io.surati.gap.web.base.RsPage;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.cactoos.collection.Sticky;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rq.RqHref;
import org.takes.rs.xe.XeAppend;
import org.takes.rs.xe.XeSource;

/**
 * Take that edits a section.
 *
 * <p>The class is immutable and thread-safe.</p>
 *
 * @since 3.0
 */
public final class TkSectionEdit implements Take {

	/**
	 * Data source.
	 */
	private final DataSource source;

	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkSectionEdit(final DataSource source) {
		this.source = source;
	}

	@Override
	public Response act(Request req) throws Exception {
		final String code = new RqHref.Smart(req).single("code", "");
		final XeSource src;
		if(StringUtils.isBlank(code)) {
			src = XeSource.EMPTY;
		} else {
			src = new XeSection(
				new DbSections(this.source).get(code)
			);
		}
		return new RsPage(
			"/io/surati/gap/gtp/base/module/xsl/section/edit.xsl",
			req,
			this.source,
			() -> new Sticky<>(
				new XeAppend("menu", "ma-section"),
				src
			)
		);
	}
}
