/*
 * Copyright (c) 2022 Surati

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.surati.gap.gtp.base.module.xe;

import org.cactoos.collection.Mapped;
import org.cactoos.iterable.Joined;
import org.takes.rs.xe.XeDirectives;
import org.takes.rs.xe.XeWrap;
import org.xembly.Directives;

import io.surati.gap.gtp.base.api.AccountPec;
import io.surati.gap.gtp.base.api.AccountPecs;

/**
 * Xml of a list of Account PECS.
 *
 * @since 0.1.4
 */
public final class XeAccountPecs extends XeWrap {

	public XeAccountPecs(final AccountPecs accountpecs) {
		this(accountpecs.iterate());
	}

	public XeAccountPecs(final Iterable<AccountPec> items) {
		super(
			new XeDirectives(
				new Directives()
					.add("account-pecs")
					.append(
						new Joined<>(
							new Mapped<>(
								item -> new XeAccountPec("account-pec", item).toXembly(),
								items
							)
						)
					)
			)
		);
	}
}
