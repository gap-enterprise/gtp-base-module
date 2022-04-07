package io.surati.gap.gtp.base.module.server;

import io.surati.gap.gtp.base.module.actions.TkBundleDelete;
import io.surati.gap.gtp.base.module.actions.TkBundleSave;
import io.surati.gap.gtp.base.module.actions.TkSectionDelete;
import io.surati.gap.gtp.base.module.actions.TkSectionSave;
import io.surati.gap.gtp.base.module.actions.TkThirdPartyDelete;
import io.surati.gap.gtp.base.module.actions.TkThirdPartySave;
import io.surati.gap.gtp.base.module.actions.TkTitleDelete;
import io.surati.gap.gtp.base.module.actions.TkTitleSave;
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
				),
				new FkRegex(
					"/gtp/base/title/save",
					new TkSecure(
						new TkTitleSave(source),
						source
					)
				),
				new FkRegex(
					"/gtp/base/title/delete",
					new TkSecure(
						new TkTitleDelete(source),
						source
					)
				),
				new FkRegex(
					"/gtp/base/bundle/save",
					new TkSecure(
						new TkBundleSave(source),
						source
					)
				),
				new FkRegex(
					"/gtp/base/bundle/delete",
					new TkSecure(
						new TkBundleDelete(source),
						source
					)
				),
				new FkRegex(
					"/gtp/base/section/save",
					new TkSecure(
						new TkSectionSave(source),
						source
					)
				),
				new FkRegex(
					"/gtp/base/section/delete",
					new TkSecure(
						new TkSectionDelete(source),
						source
					)
				)
			)
		);
	}
}
