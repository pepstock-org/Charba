package org.pepstock.charba.client.jsinterop.utils;

import jsinterop.annotations.JsType;

/**
 * The console object provides access to the browser's debugging console
 */
@JsType(isNative = true)
public interface Console {

    /**
     * Outputs a message to the Web Console.
     *
     * @param objects A list of JavaScript objects to output. The string representations of each of these
     *            objects are appended together in the order listed and output.
     */
    void log(Object... objects);

}
