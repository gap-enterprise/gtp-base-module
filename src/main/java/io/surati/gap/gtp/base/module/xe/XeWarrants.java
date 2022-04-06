package io.surati.gap.gtp.base.module.xe;

import io.surati.gap.gtp.base.api.Warrant;
import io.surati.gap.gtp.base.api.Warrants;
import org.cactoos.collection.Mapped;
import org.cactoos.iterable.Joined;
import org.takes.rs.xe.XeDirectives;
import org.takes.rs.xe.XeWrap;
import org.xembly.Directives;

public final class XeWarrants extends XeWrap {
	
	public XeWarrants(final Warrants warrants) {
		this(warrants.iterate());
	}
	
	public XeWarrants(final Iterable<Warrant> items) {
		super(
			new XeDirectives(
				new Directives()
					.add("warrants")
					.append(
						new Joined<>(
							new Mapped<>(
								item -> new XeWarrant("warrant", item).toXembly(),
								items
							)
						)
					)
			)
		);
	}
}
