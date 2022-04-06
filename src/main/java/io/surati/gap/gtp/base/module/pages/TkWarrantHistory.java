package io.surati.gap.gtp.base.module.pages;

import io.surati.gap.gtp.base.module.xe.XeWarrantStatuss;
import io.surati.gap.web.base.RsPage;
import io.surati.gap.web.base.xe.XeRootPage;
import javax.sql.DataSource;
import org.cactoos.collection.Sticky;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rs.xe.XeAppend;
import org.takes.rs.xe.XeSource;

public final class TkWarrantHistory implements Take {
	
	private final DataSource source;

	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkWarrantHistory(final DataSource source) {
		this.source = source;
	}
	
	@Override
	public Response act(Request req) throws Exception {
		final XeSource src = new XeWarrantStatuss();
		return new RsPage(
			"/io/surati/gap/gtp/base/module/xsl/warrant/history.xsl",
			req,
			this.source,
			() -> new Sticky<>(
				new XeAppend("menu", "reference-document"),
				new XeRootPage(
					"Historique",
					"Mandats",
					req
				),
				src
			)
		);
	}

}
