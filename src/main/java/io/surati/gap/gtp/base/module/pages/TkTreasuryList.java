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

import org.cactoos.collection.Sticky;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rs.xe.XeAppend;
import org.takes.rs.xe.XeSource;

import io.surati.gap.gtp.base.db.DbTreasuries;
import io.surati.gap.gtp.base.module.xe.XeTreasuries;
import io.surati.gap.web.base.RsPage;

/**
 * Take that lists treasuries.
 *
 * <p>The class is immutable and thread-safe.</p>
 *
 * @since 0.1.4
 */
public final class TkTreasuryList implements Take {

	private final DataSource source;

	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkTreasuryList(final DataSource source) {
		this.source = source;
	}
	
	@Override
	public Response act(Request req) throws Exception {
		final XeSource src = new XeTreasuries(
			new DbTreasuries(this.source)
		);
		return new RsPage(
			"/io/surati/gap/gtp/base/module/xsl/treasury/list.xsl",
			req,
			this.source,
			() -> new Sticky<>(
				new XeAppend("menu", "gtp-treasury"),
				src
			)
		);
	}
}
