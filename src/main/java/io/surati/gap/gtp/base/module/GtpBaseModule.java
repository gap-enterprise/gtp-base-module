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
package io.surati.gap.gtp.base.module;

import io.surati.gap.admin.base.api.Module;
import io.surati.gap.web.base.menu.DashboardMenu;
import io.surati.gap.web.base.menu.Menu;
import io.surati.gap.web.base.menu.SimpleMenu;
import io.surati.gap.web.base.menu.SimpleSubmenu;
import org.cactoos.iterable.IterableOf;

/**
 * Gtp base module.
 *
 * @since 0.1
 */
public enum GtpBaseModule implements Module {

	GTP_BASE(
		"General Treasury Payment base module",
		"Ce module sert de base à tous les modules de paiement d'une paierie générale."
	);

	public static void setup() {
		for (final Module mod : GtpBaseModule.values()) {
			Module.VALUES.add(mod);
			Module.BY_CODE.put(mod.code(), mod);
		}
		for (final DashboardMenu dmenu : GtpBaseDashboardMenu.values()) {
			DashboardMenu.VALUES.add(dmenu);
		}
		Menu.VALUES.add(
			new SimpleMenu(
				800,
				"history",
				"lnr-history",
				"Historique",
				"bg-warning",
				"Explorer vos données de production",
				new IterableOf<>(
					new SimpleSubmenu(
						1, "warrant", "lnr-briefcase", "Mandats", "/warrant/history",
						new IterableOf<>(
							GtpBaseAccess.VISUALISER_MANDATS
						),
						false
					),
					new SimpleSubmenu(
						2, "payment", "lnr-diamond", "Paiements", "/payment/list",
						new IterableOf<>(
							GtpBaseAccess.VISUALISER_PAIEMENTS
						),
						false
					)
				)
			)
		);
		Menu.VALUES.add(
			new SimpleMenu(
				900,
				"settings",
				"lnr-cog",
				"Paramétrage",
				"bg-info",
				"Paramétrer vos données de base",
				new IterableOf<>(
					new SimpleSubmenu(
						1, "third-party", "lnr-users", "Tiers", "/third-party",
						new IterableOf<>(
							GtpBaseAccess.VISUALISER_TIERS,
							GtpBaseAccess.CONFIGURER_TIERS
						),
						false
					),
					new SimpleSubmenu(
						4, "title", "lnr-book", "Titres", "/gtp/base/title",
						new IterableOf<>(
							GtpBaseAccess.CONFIGURER_TITRES,
							GtpBaseAccess.VISUALISER_TITRES
						),
						true
					),
					new SimpleSubmenu(
						5, "section", "lnr-book", "Sections", "/gtp/base/section",
						new IterableOf<>(
							GtpBaseAccess.CONFIGURER_SECTIONS,
							GtpBaseAccess.VISUALISER_SECTIONS
						),
						false
					),
					new SimpleSubmenu(
						6, "chapter", "lnr-book", "Chapitres", "/gtp/base/chapter",
						new IterableOf<>(
							GtpBaseAccess.CONFIGURER_CHAPITRES,
							GtpBaseAccess.VISUALISER_CHAPITRES
						),
						false
					),
					new SimpleSubmenu(
						7, "sub-chapter", "lnr-book", "Sous-chapitres", "/gtp/base/sub-chapter",
						new IterableOf<>(
							GtpBaseAccess.CONFIGURER_SOUS_CHAPITRES,
							GtpBaseAccess.VISUALISER_SOUS_CHAPITRES
						),
						false
					),
					new SimpleSubmenu(
						8, "region", "lnr-book", "Régions", "/gtp/base/region",
						new IterableOf<>(
							GtpBaseAccess.CONFIGURER_REGIONS,
							GtpBaseAccess.VISUALISER_REGIONS
						),
						false
					),
					new SimpleSubmenu(
						9, "line", "lnr-book", "Lignes", "/gtp/base/line",
						new IterableOf<>(
							GtpBaseAccess.CONFIGURER_LIGNES,
							GtpBaseAccess.VISUALISER_LIGNES
						),
						false
					),
					new SimpleSubmenu(
						10, "bundle", "lnr-book", "Liasses", "/gtp/base/bundle",
						new IterableOf<>(
							GtpBaseAccess.CONFIGURER_LIASSES,
							GtpBaseAccess.VISUALISER_LIASSES
						),
						false
					),
					new SimpleSubmenu(
						11, "account-pec", "lnr-book", "Comptes PEC", "/gtp/base/account-pec",
						new IterableOf<>(
							GtpBaseAccess.CONFIGURER_COMPTE_PECS,
							GtpBaseAccess.VISUALISER_COMPTE_PECS
						),
						false
					),
					new SimpleSubmenu(
						20, "treasury", "lnr-book", "Postes comptables", "/gtp/base/treasury",
						new IterableOf<>(
							GtpBaseAccess.CONFIGURER_PAIERIES,
							GtpBaseAccess.VISUALISER_PAIERIES
						),
						true
					)
				)
			)
		);
	}

	/**
	 * Title of access.
	 */
	private String title;

	/**
	 * Description.
	 */
	private String description;

	/**
	 * Ctor.
	 * @param title Title
	 * @param description Description
	 */
	GtpBaseModule(final String title, final String description) {
		this.title = title;
		this.description = description;
	}
	
	/**
	 * Get title.
	 * @return Title
	 */
	@Override
	public String title() {
		return this.title;
	}

	/**
	 * Get description.
	 * @return Description
	 */
	@Override
	public String description() {
		return this.description;
	}

	/**
	 * Get Code.
	 * @return Code
	 */
	public String code() {
		return this.name();
	}
	
	@Override
	public String toString() {
		return this.title;
	}
}
