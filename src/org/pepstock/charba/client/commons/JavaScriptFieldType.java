package org.pepstock.charba.client.commons;

/**
 * FIXME
 * see https://developer.mozilla.org/it/docs/Web/JavaScript/Reference/Operators/typeof
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum JavaScriptFieldType
{
	Undefined,
	Boolean,
	Number,
	String,
	Symbol,
	Function,
	Object,
	Array;
	
	static final JavaScriptFieldType getType(String value, boolean isArray) {
		if (isArray) {
			return Array;
		}
		if (value != null) {
			for (JavaScriptFieldType type : values()) {
				if (type.name().equalsIgnoreCase(value)) {
					return type;
				}
			}
		}
		return Object;
	}
	
}
