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
package io.surati.gap.gtp.base.module.pages;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.cactoos.collection.Sticky;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rq.RqHref;
import org.takes.rs.xe.XeAppend;
import org.takes.rs.xe.XeSource;

import io.surati.gap.gtp.base.db.DbSubChapters;
import io.surati.gap.gtp.base.module.xe.XeSubChapter;
import io.surati.gap.web.base.RsPage;

/**
 * Take that edits a sub-chapter.
 *
 * <p>The class is immutable and thread-safe.</p>
 *
 * @since 0.1.1
 */
public final class TkSubChapterEdit implements Take {

	/**
	 * Data source.
	 */
	private final DataSource source;

	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkSubChapterEdit(final DataSource source) {
		this.source = source;
	}

	@Override
	public Response act(Request req) throws Exception {
		final String code = new RqHref.Smart(req).single("code", "");
		final XeSource src;
		if(StringUtils.isBlank(code)) {
			src = XeSource.EMPTY;
		} else {
			src = new XeSubChapter(
				new DbSubChapters(this.source).get(code)
			);
		}
		return new RsPage(
			"/io/surati/gap/gtp/base/module/xsl/sub-chapter/edit.xsl",
			req,
			this.source,
			() -> new Sticky<>(
				new XeAppend("menu", "ma-sub-chapter"),
				src
			)
		);
	}
}
