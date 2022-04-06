package io.surati.gap.gtp.base.module.server;

import io.surati.gap.gtp.base.module.actions.TkThirdPartyDelete;
import io.surati.gap.gtp.base.module.actions.TkThirdPartySave;
import io.surati.gap.gtp.base.module.pages.TkWarrantHistory;
import io.surati.gap.web.base.TkSecure;
import javax.sql.DataSource;
import org.takes.facets.fork.FkChain;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.FkWrap;

/**
 * Front for actions.
 *
 * @since 0.1
 */
public final class FkActions extends FkWrap {

	public FkActions(final DataSource source) {
		super(
			new FkChain(
				new FkRegex(
					"/third-party/save",
					new TkSecure(
						new TkThirdPartySave(source),
						source
					)
				),
				new FkRegex(
					"/third-party/delete",
					new TkSecure(
						new TkThirdPartyDelete(source),
						source
					)
				),
				new FkRegex(
					"/warrant/history",
					new TkSecure(
						new TkWarrantHistory(source),
						source
					)
				)
			)
		);
	}
}
