package io.surati.gap.gtp.base.module.xe;

import io.surati.gap.payment.base.api.ReferenceDocumentStatus;
import org.takes.rs.xe.XeDirectives;
import org.takes.rs.xe.XeWrap;
import org.xembly.Directives;

public final class XeWarrantStatus extends XeWrap {

	public XeWarrantStatus(final ReferenceDocumentStatus status) {
		this("warrant_status", status);
	}

	public XeWarrantStatus(final String name, final ReferenceDocumentStatus status) {
		super(
			new XeDirectives(
				new Directives()
				.add(name)
					.add("id").set(status.name()).up()
					.add("name").set(status.toString()).up()
				.up()
			)
		);
	}
}