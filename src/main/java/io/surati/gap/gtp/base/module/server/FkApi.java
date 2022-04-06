package io.surati.gap.gtp.base.module.server;

import io.surati.gap.gtp.base.module.rest.TkPaymentSearch;
import io.surati.gap.gtp.base.module.rest.TkThirdPartySearch;
import io.surati.gap.gtp.base.module.rest.TkWarrantHistorySearch;
import io.surati.gap.web.base.TkSecure;
import javax.sql.DataSource;
import org.takes.facets.fork.FkChain;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.FkWrap;

/**
 * Front for API.
 *
 * @since 0.1
 */
public final class FkApi extends FkWrap {

	public FkApi(final DataSource source) {
		super(
			new FkChain(
				new FkRegex(
					"/third-party/search",
					new TkSecure(
						new TkThirdPartySearch(source),
						source
					)
				),
				new FkRegex(
					"/warrant/history/search",
					new TkSecure(
						new TkWarrantHistorySearch(source),
						source
					)
				),
				new FkRegex(
					"/payment/search",
					new TkSecure(
						new TkPaymentSearch(source),
						source
					)
				)
			)
		);
	}
}
