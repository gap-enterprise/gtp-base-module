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
package io.surati.gap.gtp.base.module.secure;

import io.surati.gap.admin.base.api.User;
import io.surati.gap.gtp.base.api.Treasury;
import io.surati.gap.gtp.base.module.GtpBaseAccess;
import io.surati.gap.payment.base.api.PaymentCondition;
import io.surati.gap.payment.base.api.ThirdPartyFamily;

import javax.ws.rs.NotAuthorizedException;

public final class SecTreasury implements Treasury {

    private final Treasury origin;

    private final User user;

    public SecTreasury(final Treasury origin , final User user) {
        this.origin = origin;
        this.user = user;
    }

    @Override
    public String code() {
        return this.origin.code();
    }

    @Override
    public String name() {
        return this.origin.name();
    }

	@Override
	public String abbreviated() {
		return this.origin.abbreviated();
	}

	@Override
	public void update(String code, String name, String abbreviated) {
		if(!user.profile().accesses().has(GtpBaseAccess.CONFIGURER_PAIERIES)) {
            throw new NotAuthorizedException("Vos droits d’accès sont insuffisants pour modifier un poste comptable.");
        }
        this.origin.update(code, name, abbreviated);
	}

	@Override
	public ThirdPartyFamily family() {
		return this.origin.family();
	}

	@Override
	public void assign(ThirdPartyFamily family) {
		this.origin.assign(family);
	}

	@Override
	public PaymentCondition paymentCondition() {
		return this.origin.paymentCondition();
	}

	@Override
	public Long id() {
		return this.origin.id();
	}

	@Override
	public void update(String name) {
		this.origin.update(name);
	}

	@Override
	public String representative() {
		return this.origin.representative();
	}

	@Override
	public void representative(String arg0, String arg1) {
		this.origin.representative(arg0, arg1);
	}

	@Override
	public String representativePosition() {
		return this.origin.representativePosition();
	}
}
