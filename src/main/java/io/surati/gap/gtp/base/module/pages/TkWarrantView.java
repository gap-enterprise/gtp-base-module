package io.surati.gap.gtp.base.module.pages;

import io.surati.gap.gtp.base.api.Warrant;
import io.surati.gap.gtp.base.db.DbPaginatedWarrants;
import io.surati.gap.gtp.base.module.xe.XePayments;
import io.surati.gap.gtp.base.module.xe.XeWarrant;
import io.surati.gap.web.base.RsPage;
import io.surati.gap.web.base.xe.XeRootPage;
import javax.sql.DataSource;
import org.cactoos.collection.Sticky;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rq.RqHref;
import org.takes.rs.xe.XeAppend;
import org.takes.rs.xe.XeChain;
import org.takes.rs.xe.XeSource;

public final class TkWarrantView implements Take {

	/**
	 * DataSource
	 */
	private final DataSource source;
	
	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkWarrantView(final DataSource source) {
		this.source = source;
	}

	@Override
	public Response act(Request req) throws Exception {
		final Long id = Long.parseLong(new RqHref.Smart(req).single("id"));
		final Warrant item = new DbPaginatedWarrants(this.source).get(id);
		final XeSource src = new XeChain(
			new XeWarrant(item),
			new XePayments(item.payments()),
			new XeRootPage(
				"current_page",
				"Historique",
				String.format("Mandat N°%s", item.reference()),
				req
			)
		);
		return new RsPage(
			"/io/surati/gap/gtp/base/module/xsl/warrant/view.xsl",
			req,
			this.source,
			() -> new Sticky<>(
				new XeAppend("menu", "warrant"),
				src,
				new XeRootPage(req)
			)
		);
	}

}
