package io.surati.gap.gtp.base.module.xe;

import io.surati.gap.payment.base.api.ReferenceDocumentStatus;
import java.util.Collection;
import java.util.LinkedList;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.takes.rs.xe.XeDirectives;
import org.takes.rs.xe.XeWrap;
import org.xembly.Directives;

public final class XeWarrantStatuss extends XeWrap {

	public XeWarrantStatuss() {
		this(withoutNone());
	}
	public XeWarrantStatuss(final Iterable<ReferenceDocumentStatus> items) {
		super(
			new XeDirectives(
				new Directives()
					.add("warrant_statuss")
					.append(
						new Joined<>(
							new Mapped<>(
								item -> new XeWarrantStatus("warrant_status", item).toXembly(),
								items
							)
						)
					)
			)
		);
	}
	
	private final static Iterable<ReferenceDocumentStatus> withoutNone() {
		final Collection<ReferenceDocumentStatus> status = new LinkedList<>();
		for (ReferenceDocumentStatus st : ReferenceDocumentStatus.values()) {
			if(st != ReferenceDocumentStatus.NONE) {
				status.add(st);
			}
		}
		return status;
	}
}
