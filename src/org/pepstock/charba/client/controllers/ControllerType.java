package org.pepstock.charba.client.controllers;

import org.pepstock.charba.client.Type;

public final class ControllerType implements Type {
	
	private final String type;

	public ControllerType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.commons.Key#name()
	 */
	@Override
	public String name() {
		return type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Type) {
			Type objType = (Type)obj;
			if (objType.name() != null && type != null) {
				return type.equals(objType.name());
			}
		}
		return false;
	}
	

}
