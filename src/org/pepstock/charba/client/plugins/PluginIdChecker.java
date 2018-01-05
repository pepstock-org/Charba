package org.pepstock.charba.client.plugins;

import java.util.Locale;

import org.pepstock.charba.client.options.InvalidPluginIdException;

import com.google.gwt.safehtml.shared.UriUtils;

final class PluginIdChecker {
	
	private static final String INVALID_PLUGIN__ID_NULL = "Plugin id can not be null ";
	
	private static final String INVALID_PLUGIN__ID_FIRST_CHAR = "Plugin id can not start with a dot or an underscore ";

	private static final String INVALID_PLUGIN__ID_URL_SAFE = "Plugin id can not contain any non-URL-safe characters ";
	
	private static final String INVALID_PLUGIN__ID_UPPERCASE = "Plugin id can not contain uppercase letters ";
	
	private static final char DOT = '.';
	
	private static final char UNDERSCORE = '.';

	private PluginIdChecker() {
	}

	static void check(String id) throws InvalidPluginIdException{
		if (id == null){
			throw new InvalidPluginIdException(buildMessage(id, INVALID_PLUGIN__ID_NULL));
		} else if (id.charAt(0) == DOT || id.charAt(0) == UNDERSCORE){
			throw new InvalidPluginIdException(buildMessage(id, INVALID_PLUGIN__ID_FIRST_CHAR));
		} else if (!UriUtils.isSafeUri(id)){
			throw new InvalidPluginIdException(buildMessage(id, INVALID_PLUGIN__ID_URL_SAFE));
		} else if (!id.toLowerCase(Locale.getDefault()).equals(id)){
			throw new InvalidPluginIdException(buildMessage(id, INVALID_PLUGIN__ID_UPPERCASE));
		}
	}
	private static String buildMessage(String pluginId, String message){
		StringBuilder sb = new StringBuilder(message);
		sb.append("[").append(pluginId).append("]");
		return sb.toString();
	}
	
}
