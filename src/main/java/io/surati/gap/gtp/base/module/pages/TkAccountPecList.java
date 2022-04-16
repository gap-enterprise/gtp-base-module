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

import io.surati.gap.gtp.base.db.DbAccountPecs;
import io.surati.gap.gtp.base.module.xe.XeAccountPecs;
import io.surati.gap.web.base.RsPage;

/**
 * Take that lists account PECS.
 *
 * <p>The class is immutable and thread-safe.</p>
 *
 * @since 0.1.4
 */
public final class TkAccountPecList implements Take {

	private final DataSource source;

	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkAccountPecList(final DataSource source) {
		this.source = source;
	}
	
	@Override
	public Response act(Request req) throws Exception {
		final XeSource src = new XeAccountPecs(
			new DbAccountPecs(this.source)
		);
		return new RsPage(
			"/io/surati/gap/gtp/base/module/xsl/account-pec/list.xsl",
			req,
			this.source,
			() -> new Sticky<>(
				new XeAppend("menu", "ma-account-pec"),
				src
			)
		);
	}

}
