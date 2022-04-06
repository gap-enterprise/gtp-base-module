package io.surati.gap.gtp.base.module.rest;

import io.surati.gap.gtp.base.api.Warrants;
import io.surati.gap.gtp.base.db.DbPaginatedWarrants;
import io.surati.gap.gtp.base.module.xe.XeWarrantsJson;
import javax.sql.DataSource;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rq.RqHref.Smart;
import org.takes.rs.RsJson;

public final class TkWarrantHistorySearch implements Take {

	/**
	 * Database
	 */
	private final DataSource source;
	
	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkWarrantHistorySearch(final DataSource source) {
		this.source = source;
	}
	
	@Override
	public Response act(Request req) throws Exception {	
		final Smart href = new Smart(req);
		final String filter = href.single("filter", "");					
		final Long page = Long.parseLong(href.single("page"));
		final Long nbperpage = Long.parseLong(href.single("nbperpage"));
		final Warrants items = new DbPaginatedWarrants(
			this.source,
			nbperpage,
			page,
			filter
		);
		return new RsJson(
			new XeWarrantsJson(
				items.iterate(),
				items.count(),
				items.totalAmount(),
				items.amountLeft(),
				0.0
			)
		);
	}

}
