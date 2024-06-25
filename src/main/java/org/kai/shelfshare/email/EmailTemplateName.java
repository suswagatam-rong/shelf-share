package org.kai.shelfshare.email;
/*
 * @created 25/06/2024 - 12:28
 * @project Shelf Share
 * @author  Suswagatam Rong
 */

import lombok.Getter;

@Getter
public enum EmailTemplateName {
	ACTIVATE_ACCOUNT("activate_account");
	
	private final String name;

	EmailTemplateName(String name) {
		this.name = name;
	}
}