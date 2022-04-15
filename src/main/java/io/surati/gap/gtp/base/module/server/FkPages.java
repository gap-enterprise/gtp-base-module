package io.surati.gap.gtp.base.module.server;

import io.surati.gap.gtp.base.module.pages.TkBundleEdit;
import io.surati.gap.gtp.base.module.pages.TkBundleList;
import io.surati.gap.gtp.base.module.pages.TkBundleView;
import io.surati.gap.gtp.base.module.pages.TkChapterEdit;
import io.surati.gap.gtp.base.module.pages.TkChapterList;
import io.surati.gap.gtp.base.module.pages.TkChapterView;
import io.surati.gap.gtp.base.module.pages.TkLineEdit;
import io.surati.gap.gtp.base.module.pages.TkLineList;
import io.surati.gap.gtp.base.module.pages.TkLineView;
import io.surati.gap.gtp.base.module.pages.TkPaymentList;
import io.surati.gap.gtp.base.module.pages.TkPaymentOrderView;
import io.surati.gap.gtp.base.module.pages.TkPaymentView;
import io.surati.gap.gtp.base.module.pages.TkSectionEdit;
import io.surati.gap.gtp.base.module.pages.TkSectionList;
import io.surati.gap.gtp.base.module.pages.TkSectionView;
import io.surati.gap.gtp.base.module.pages.TkSubChapterEdit;
import io.surati.gap.gtp.base.module.pages.TkSubChapterList;
import io.surati.gap.gtp.base.module.pages.TkSubChapterView;
import io.surati.gap.gtp.base.module.pages.TkThirdPartyEdit;
import io.surati.gap.gtp.base.module.pages.TkThirdPartyList;
import io.surati.gap.gtp.base.module.pages.TkThirdPartyView;
import io.surati.gap.gtp.base.module.pages.TkTitleEdit;
import io.surati.gap.gtp.base.module.pages.TkTitleList;
import io.surati.gap.gtp.base.module.pages.TkTitleView;
import io.surati.gap.gtp.base.module.pages.TkTreasuryEdit;
import io.surati.gap.gtp.base.module.pages.TkTreasuryList;
import io.surati.gap.gtp.base.module.pages.TkTreasuryView;
import io.surati.gap.gtp.base.module.pages.TkWarrantView;
import io.surati.gap.web.base.TkSecure;
import javax.sql.DataSource;
import org.takes.facets.fork.FkChain;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.FkWrap;

/**
 * Front for pages.
 *
 * @since 0.1
 */
public final class FkPages extends FkWrap {

	public FkPages(final DataSource src) {
		super(
			new FkChain(
				new FkRegex(
					"/payment/view",
					new TkSecure(
						new TkPaymentView(src),
						src
					)
				),
				new FkRegex(
					"/payment/list",
					new TkSecure(
						new TkPaymentList(src),
						src
					)
				),
				new FkRegex(
					"/payment-order/view",
					new TkSecure(
						new TkPaymentOrderView(src),
						src
					)
				),
				new FkRegex(
					"/third-party",
					new TkSecure(
						new TkThirdPartyList(src),
						src
					)
				),
				new FkRegex(
					"/third-party/edit",
					new TkSecure(
						new TkThirdPartyEdit(src),
						src
					)
				),
				new FkRegex(
					"/third-party/view",
					new TkSecure(
						new TkThirdPartyView(src),
						src
					)
				),
				new FkRegex(
					"/warrant/view",
					new TkSecure(
						new TkWarrantView(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/title/edit",
					new TkSecure(
						new TkTitleEdit(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/title",
					new TkSecure(
						new TkTitleList(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/title/view",
					new TkSecure(
						new TkTitleView(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/bundle/edit",
					new TkSecure(
						new TkBundleEdit(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/bundle",
					new TkSecure(
						new TkBundleList(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/bundle/view",
					new TkSecure(
						new TkBundleView(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/section/edit",
					new TkSecure(
						new TkSectionEdit(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/section",
					new TkSecure(
						new TkSectionList(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/section/view",
					new TkSecure(
						new TkSectionView(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/chapter/edit",
					new TkSecure(
						new TkChapterEdit(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/chapter",
					new TkSecure(
						new TkChapterList(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/chapter/view",
					new TkSecure(
						new TkChapterView(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/sub-chapter/edit",
					new TkSecure(
						new TkSubChapterEdit(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/sub-chapter",
					new TkSecure(
						new TkSubChapterList(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/sub-chapter/view",
					new TkSecure(
						new TkSubChapterView(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/treasury/edit",
					new TkSecure(
						new TkTreasuryEdit(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/treasury",
					new TkSecure(
						new TkTreasuryList(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/treasury/view",
					new TkSecure(
						new TkTreasuryView(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/line/edit",
					new TkSecure(
						new TkLineEdit(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/line",
					new TkSecure(
						new TkLineList(src),
						src
					)
				),
				new FkRegex(
					"/gtp/base/line/view",
					new TkSecure(
						new TkLineView(src),
						src
					)
				)
			)
		);
	}
}
