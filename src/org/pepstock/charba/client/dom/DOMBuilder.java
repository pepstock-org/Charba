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

import org.pepstock.charba.client.dom.elements.Canvas;
import org.pepstock.charba.client.dom.elements.Div;
import org.pepstock.charba.client.dom.elements.Heading;
import org.pepstock.charba.client.dom.elements.Img;
import org.pepstock.charba.client.dom.elements.LineBreak;
import org.pepstock.charba.client.dom.elements.Script;
import org.pepstock.charba.client.dom.elements.Span;
import org.pepstock.charba.client.dom.elements.Style;
import org.pepstock.charba.client.dom.elements.Table;
import org.pepstock.charba.client.dom.elements.TableCell;
import org.pepstock.charba.client.dom.elements.TableRow;
import org.pepstock.charba.client.dom.elements.TextNode;

/**
 * Singleton utility to create and manage DOM element.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DOMBuilder {

	// singleton instance
	private static final DOMBuilder INSTANCE = new DOMBuilder();
	// prefix of CHARBA chart id
	private static final String CHART_PREFIX_ID = "charba-chart-";
	// counter used to create a unique ID
	private final AtomicInteger counter = new AtomicInteger();
	// flag to know if canvas is supported in the the browser
	private final boolean canvasSupported;

	/**
	 * To avoid any instantiation
	 */
	private DOMBuilder() {
		// creates a canvas
		Canvas canvas = createCanvasElement();
		// checks and stores if the canvas is supported
		this.canvasSupported = canvas.isSupported();
	}

	/**
	 * Returns the singleton instance of the builder.
	 * 
	 * @return the singleton instance of the builder
	 */
	public static DOMBuilder get() {
		return INSTANCE;
	}

	/**
	 * Creates an unique id for CHARBA charts id.
	 * 
	 * @return an unique id for CHARBA charts id
	 */
	public String createUniqueChartId() {
		return CHART_PREFIX_ID + counter.getAndIncrement();
	}

	/**
	 * Returns <code>true</code> if the passed id is a CHARBA charts id.
	 * 
	 * @param id an id instance to check
	 * @return <code>true</code> if the passed id is a CHARBA charts id
	 */
	public boolean isUniqueChartId(String id) {
		// checks if argument is consistent
		if (id != null) {
			// checks if has got the same prefix
			return id.startsWith(CHART_PREFIX_ID);
		}
		// if here argument is not consistent
		// then false
		return false;
	}

	/**
	 * Returns <code>true</code> if the canvas is supported by browser.
	 * 
	 * @return <code>true</code> if the canvas is supported by browser
	 */
	public boolean isCanvasSupported() {
		return canvasSupported;
	}

	/**
	 * Creates a &lt;canvas&gt; element.
	 * 
	 * @return the newly created element
	 */
	public Canvas createCanvasElement() {
		return DOM.getDocument().createElement(Canvas.TAG);
	}

	/**
	 * Creates a &lt;div&gt; element.
	 * 
	 * @return a &lt;div&gt; element
	 */
	public Div createDivElement() {
		return DOM.getDocument().createElement(Div.TAG);
	}

	/**
	 * Creates a &lt;script&gt; element.
	 * 
	 * @return a &lt;script&gt; element
	 */
	public Script createScriptElement() {
		return DOM.getDocument().createElement(Script.TAG);
	}

	/**
	 * Creates a &lt;style&gt; element.
	 * 
	 * @return a &lt;style&gt; element
	 */
	public Style createStyleElement() {
		return DOM.getDocument().createElement(Style.TAG);
	}

	/**
	 * Creates a &lt;br&gt; element.
	 * 
	 * @return a &lt;br&gt; element
	 */
	public LineBreak createLineBreakElement() {
		return DOM.getDocument().createElement(LineBreak.TAG);
	}

	/**
	 * Creates a &lt;span&gt; element.
	 * 
	 * @return a &lt;span&gt; element
	 */
	public Span createSpanElement() {
		return DOM.getDocument().createElement(Span.TAG);
	}

	/**
	 * Creates a &lt;img&gt; element.
	 * 
	 * @return a &lt;img&gt; element
	 */
	public Img createImageElement() {
		return createImageElement(null);
	}

	/**
	 * Creates a &lt;img&gt; element with the full URL of the image.
	 * 
	 * @param src the full URL of the image
	 * @return a &lt;img&gt; element
	 */
	public Img createImageElement(String src) {
		// creates an image
		Img image = DOM.getDocument().createElement(Img.TAG);
		// checks if url of image as argument is consistent
		if (src != null) {
			// if her the argument is consistent
			// then sets it
			image.setSrc(src);
		}
		// returns the image
		return image;
	}

	/**
	 * Creates a &lt;table&gt; element.
	 * 
	 * @return a &lt;table&gt; element
	 */
	public Table createTableElement() {
		return DOM.getDocument().createElement(Table.TAG);
	}

	/**
	 * Creates a &lt;tr&gt; element.
	 * 
	 * @return a &lt;tr&gt; element
	 */
	public TableRow createTableRowElement() {
		return DOM.getDocument().createElement(TableRow.TAG);
	}

	/**
	 * Creates a &lt;td&gt; element.
	 * 
	 * @return a &lt;td&gt; element
	 */
	public TableCell createTableCellElement() {
		return DOM.getDocument().createElement(TableCell.TAG);
	}

	/**
	 * Creates a &lt;h3&gt; element.
	 * 
	 * @return a &lt;h3&gt; element
	 */
	public Heading createHeadingElement() {
		return DOM.getDocument().createElement(Heading.TAG);
	}

	/**
	 * Creates a text node.
	 * 
	 * @param data the text node's initial text
	 * @return a text node
	 */
	public TextNode createTextNode(String data) {
		return DOM.getDocument().createTextNode(data);
	}

	/**
	 * Creates a change event (based on a mouse event, type {@link BaseEventTypes#CONTEXT_MENU}) for internal use.
	 * 
	 * @return a change event (based on a mouse event, type {@link BaseEventTypes#CONTEXT_MENU})
	 */
	public BaseNativeEvent createChangeEvent() {
		// creates a mouse event
		BaseNativeEvent event = DOM.getDocument().createEvent(BaseEventTypes.EVENT_MOUSE);
		// initializes it as context menu
		event.initEvent(BaseEventTypes.CONTEXT_MENU, true, false);
		// returns event
		return event;
	}

}
