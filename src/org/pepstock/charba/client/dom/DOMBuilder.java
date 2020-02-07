/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.dom;

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.charba.client.dom.elements.BreakElement;
import org.pepstock.charba.client.dom.elements.CanvasElement;
import org.pepstock.charba.client.dom.elements.DivElement;
import org.pepstock.charba.client.dom.elements.HeadingElement;
import org.pepstock.charba.client.dom.elements.ImageElement;
import org.pepstock.charba.client.dom.elements.ScriptElement;
import org.pepstock.charba.client.dom.elements.SpanElement;
import org.pepstock.charba.client.dom.elements.StyleElement;
import org.pepstock.charba.client.dom.elements.TableCellElement;
import org.pepstock.charba.client.dom.elements.TableElement;
import org.pepstock.charba.client.dom.elements.TableRowElement;
import org.pepstock.charba.client.dom.elements.TextNode;

/**
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DOMBuilder {

	private static final DOMBuilder INSTANCE = new DOMBuilder();

	private static final String CHART_PREFIX_ID = "charba-chart-";

	private final AtomicInteger counter = new AtomicInteger();

	private final boolean canvasSupported;

	private DOMBuilder() {
		CanvasElement canvas = createCanvasElement();
		this.canvasSupported = canvas.isSupported();
	}

	public static DOMBuilder get() {
		return INSTANCE;
	}

	public String createUniqueId() {
		return CHART_PREFIX_ID + counter.getAndIncrement();
	}

	/**
	 * @return the canvasSupported
	 */
	public boolean isCanvasSupported() {
		return canvasSupported;
	}

	/**
	 * Creates a &lt;canvas&gt; element.
	 * 
	 * @return the newly created element
	 */
	public CanvasElement createCanvasElement() {
		return DOM.getDocument().createElement(CanvasElement.TAG);
	}

	/**
	 * Creates a &lt;div&gt; element.
	 * 
	 * @return the newly created element
	 */
	public DivElement createDivElement() {
		return DOM.getDocument().createElement(DivElement.TAG);
	}

	/**
	 * Creates a &lt;script&gt; element.
	 * 
	 * @return the newly created element
	 */
	public ScriptElement createScriptElement() {
		return DOM.getDocument().createElement(ScriptElement.TAG);
	}

	/**
	 * Creates a &lt;script&gt; element.
	 * 
	 * @return the newly created element
	 */
	public StyleElement createStyleElement() {
		return DOM.getDocument().createElement(StyleElement.TAG);
	}

	/**
	 * Creates a &lt;br&gt; element.
	 * 
	 * @return the newly created element
	 */
	public BreakElement createBreakElement() {
		return DOM.getDocument().createElement(BreakElement.TAG);
	}

	/**
	 * Creates a &lt;span&gt; element.
	 * 
	 * @return the newly created element
	 */
	public SpanElement createSpanElement() {
		return DOM.getDocument().createElement(SpanElement.TAG);
	}

	/**
	 * Creates a &lt;img&gt; element.
	 * 
	 * @return the newly created element
	 */
	public ImageElement createImageElement() {
		return DOM.getDocument().createElement(ImageElement.TAG);
	}

	/**
	 * Creates a &lt;table&gt; element.
	 * 
	 * @return the newly created element
	 */
	public TableElement createTableElement() {
		return DOM.getDocument().createElement(TableElement.TAG);
	}

	/**
	 * Creates a &lt;tr&gt; element.
	 * 
	 * @return the newly created element
	 */
	public TableRowElement createTableRowElement() {
		return DOM.getDocument().createElement(TableRowElement.TAG);
	}

	/**
	 * Creates a &lt;td&gt; element.
	 * 
	 * @return the newly created element
	 */
	public TableCellElement createTableCellElement() {
		return DOM.getDocument().createElement(TableCellElement.TAG);
	}
	
	/**
	 * Creates a heading element element.
	 * 
	 * @return the newly created element
	 */
	public HeadingElement createHeadingElement() {
		return DOM.getDocument().createElement(HeadingElement.TAG);
	}
	
	/**
	 * Creates a text node.
	 * 
	 * @param data the text node's initial text
	 * @return the newly created text node
	 */
	public TextNode createTextNode(String data) {
		return DOM.getDocument().createTextNode(data);
	}

	/**
	 * FIXME
	 * 
	 * @return the newly created text node
	 */
	public BaseNativeEvent createChangeEvent() {
		BaseNativeEvent event = DOM.getDocument().createEvent(BaseEventTypes.EVENT_MOUSE);
		event.initEvent(BaseEventTypes.CONTEXT_MENU);
		return event;
	}

}
