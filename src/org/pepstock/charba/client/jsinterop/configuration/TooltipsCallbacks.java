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
package org.pepstock.charba.client.jsinterop.configuration;

import java.util.List;

import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.Defaults;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipBodyCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipFooterCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipLabelCallback;
import org.pepstock.charba.client.jsinterop.callbacks.TooltipTitleCallback;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipBodyHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipFooterHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipLabelHandler;
import org.pepstock.charba.client.jsinterop.callbacks.handlers.TooltipTitleHandler;
import org.pepstock.charba.client.jsinterop.items.TooltipItem;
import org.pepstock.charba.client.jsinterop.items.TooltipLabelColor;
import org.pepstock.charba.client.jsinterop.options.EventableOptions;

/**
 * Contains all callbacks defined for a toolitp.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TooltipsCallbacks extends ConfigurationContainer<EventableOptions> implements TooltipTitleHandler, TooltipFooterHandler, TooltipLabelHandler, TooltipBodyHandler{

	/**
	 * @param chart
	 * @param configuration
	 */
	TooltipsCallbacks(AbstractChart<?, ?> chart, EventableOptions configuration) {
		super(chart, configuration);
		if (hasGlobalBodyCallback()) {
			getConfiguration().setTooltipBodyHandler(this);
		}
		if (hasGlobalTitleCallback()) {
			getConfiguration().setTooltipTitleHandler(this);
		}
		if (hasGlobalFooterCallback()) {
			getConfiguration().setTooltipFooterHandler(this);
		}
		if (hasGlobalLabelCallback()) {
			getConfiguration().setTooltipLabelHandler(this);
		}
	}
	
	private boolean hasGlobalTitleCallback() {
		return Defaults.getGlobal().getTooltips().getCallbacks().getTitleCallback() != null ||
		       Defaults.chart(getChart()).getTooltips().getCallbacks().getTitleCallback()!= null;  
	}
	
	/**
	 * @return the titleCallback
	 */
	public TooltipTitleCallback getTitleCallback() {
		return getConfiguration().getTooltips().getCallbacks().getTitleCallback();
	}

	/**
	 * @param titleCallback the titleCallback to set
	 */
	public void setTitleCallback(TooltipTitleCallback titleCallback) {
		getConfiguration().getTooltips().getCallbacks().setTitleCallback(titleCallback);
		if (!hasGlobalTitleCallback()) {
			if (titleCallback == null) {
				getConfiguration().setTooltipTitleHandler(null);
			} else {
				getConfiguration().setTooltipTitleHandler(this);
			}
		}
	}

	private boolean hasGlobalBodyCallback() {
		return Defaults.getGlobal().getTooltips().getCallbacks().getBodyCallback() != null ||
		       Defaults.chart(getChart()).getTooltips().getCallbacks().getBodyCallback()!= null;  
	}

	/**
	 * @return the bodyCallback
	 */
	public TooltipBodyCallback getBodyCallback() {
		return getConfiguration().getTooltips().getCallbacks().getBodyCallback();
	}

	/**
	 * @param bodyCallback the bodyCallback to set
	 */
	public void setBodyCallback(TooltipBodyCallback bodyCallback) {
		getConfiguration().getTooltips().getCallbacks().setBodyCallback(bodyCallback);
		if (!hasGlobalBodyCallback()) {
			if (bodyCallback == null) {
				getConfiguration().setTooltipBodyHandler(null);
			} else {
				getConfiguration().setTooltipBodyHandler(this);
			}
		}
	}

	private boolean hasGlobalLabelCallback() {
		return Defaults.getGlobal().getTooltips().getCallbacks().getLabelCallback() != null ||
		       Defaults.chart(getChart()).getTooltips().getCallbacks().getLabelCallback()!= null;  
	}
	
	/**
	 * @return the labelCallback
	 */
	public TooltipLabelCallback getLabelCallback() {
		return getConfiguration().getTooltips().getCallbacks().getLabelCallback();
	}

	/**
	 * @param labelCallback the labelCallback to set
	 */
	public void setLabelCallback(TooltipLabelCallback labelCallback) {
		getConfiguration().getTooltips().getCallbacks().setLabelCallback(labelCallback);
		if (!hasGlobalLabelCallback()) {
			if (labelCallback == null) {
				getConfiguration().setTooltipLabelHandler(null);
			} else {
				getConfiguration().setTooltipLabelHandler(this);
			}
		}
	}

	private boolean hasGlobalFooterCallback() {
		return Defaults.getGlobal().getTooltips().getCallbacks().getFooterCallback() != null ||
		       Defaults.chart(getChart()).getTooltips().getCallbacks().getFooterCallback()!= null;  
	}
	
	/**
	 * @return the footerCallback
	 */
	public TooltipFooterCallback getFooterCallback() {
		return getConfiguration().getTooltips().getCallbacks().getFooterCallback();
	}

	/**
	 * @param footerCallback the footerCallback to set
	 */
	public void setFooterCallback(TooltipFooterCallback footerCallback) {
		getConfiguration().getTooltips().getCallbacks().setFooterCallback(footerCallback);
		if (!hasGlobalFooterCallback()) {
			if (footerCallback == null) {
				getConfiguration().setTooltipFooterHandler(null);
			} else {
				getConfiguration().setTooltipFooterHandler(this);
			}
		}
	}
	
	private TooltipBodyCallback getBodyCallbackToInvoke() {
		// checks if callback is consistent
		if (getBodyCallback() != null) {
			// calls callback
			return getBodyCallback();
		} else if (Defaults.chart(getChart()).getTooltips().getCallbacks().getBodyCallback() != null) {
			// calls callback
			return Defaults.chart(getChart()).getTooltips().getCallbacks().getBodyCallback();
		} else if (Defaults.getGlobal().getTooltips().getCallbacks().getBodyCallback() != null) {
			// calls callback
			return Defaults.getGlobal().getTooltips().getCallbacks().getBodyCallback();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipBodyHandler#onBeforeBody(java.lang.Object, java.util.List)
	 */
	@Override
	public String[] onBeforeBody(Object context, List<TooltipItem> items) {
		TooltipBodyCallback toInvoke = getBodyCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onBeforeBody(getChart(), items);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipBodyHandler#onAfterBody(java.lang.Object, java.util.List)
	 */
	@Override
	public String[] onAfterBody(Object context, List<TooltipItem> items) {
		TooltipBodyCallback toInvoke = getBodyCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onAfterBody(getChart(), items);
		}
		return null;
	}
	
	private TooltipLabelCallback getLabelCallbackToInvoke() {
		// checks if callback is consistent
		if (getLabelCallback() != null) {
			// calls callback
			return getLabelCallback();
		} else if (Defaults.chart(getChart()).getTooltips().getCallbacks().getLabelCallback() != null) {
			// calls callback
			return Defaults.chart(getChart()).getTooltips().getCallbacks().getLabelCallback();
		} else if (Defaults.getGlobal().getTooltips().getCallbacks().getLabelCallback() != null) {
			// calls callback
			return Defaults.getGlobal().getTooltips().getCallbacks().getLabelCallback();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipLabelHandler#onBeforeLabel(java.lang.Object, org.pepstock.charba.client.jsinterop.items.TooltipItem)
	 */
	@Override
	public String onBeforeLabel(Object context, TooltipItem item) {
		TooltipLabelCallback toInvoke = getLabelCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onBeforeLabel(getChart(), item);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipLabelHandler#onLabel(java.lang.Object, org.pepstock.charba.client.jsinterop.items.TooltipItem)
	 */
	@Override
	public String onLabel(Object context, TooltipItem item) {
		TooltipLabelCallback toInvoke = getLabelCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onLabel(getChart(), item);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipLabelHandler#onLabelColor(java.lang.Object, org.pepstock.charba.client.jsinterop.items.TooltipItem)
	 */
	@Override
	public TooltipLabelColor onLabelColor(Object context, TooltipItem item) {
		TooltipLabelCallback toInvoke = getLabelCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onLabelColor(getChart(), item);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipLabelHandler#onLabelTextColor(java.lang.Object, org.pepstock.charba.client.jsinterop.items.TooltipItem)
	 */
	@Override
	public IsColor onLabelTextColor(Object context, TooltipItem item) {
		TooltipLabelCallback toInvoke = getLabelCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onLabelTextColor(getChart(), item);
		}
		return null;

	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipLabelHandler#onAfterLabel(java.lang.Object, org.pepstock.charba.client.jsinterop.items.TooltipItem)
	 */
	@Override
	public String onAfterLabel(Object context, TooltipItem item) {
		TooltipLabelCallback toInvoke = getLabelCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onAfterLabel(getChart(), item);
		}
		return null;
	}

	private TooltipFooterCallback getFooterCallbackToInvoke() {
		// checks if callback is consistent
		if (getFooterCallback() != null) {
			// calls callback
			return getFooterCallback();
		} else if (Defaults.chart(getChart()).getTooltips().getCallbacks().getFooterCallback() != null) {
			// calls callback
			return Defaults.chart(getChart()).getTooltips().getCallbacks().getFooterCallback();
		} else if (Defaults.getGlobal().getTooltips().getCallbacks().getFooterCallback() != null) {
			// calls callback
			return Defaults.getGlobal().getTooltips().getCallbacks().getFooterCallback();
		}
		return null;
	}

	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipFooterHandler#onBeforeFooter(java.lang.Object, java.util.List)
	 */
	@Override
	public String[] onBeforeFooter(Object context, List<TooltipItem> items) {
		TooltipFooterCallback toInvoke = getFooterCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onBeforeFooter(getChart(), items);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipFooterHandler#onFooter(java.lang.Object, java.util.List)
	 */
	@Override
	public String[] onFooter(Object context, List<TooltipItem> items) {
		TooltipFooterCallback toInvoke = getFooterCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onFooter(getChart(), items);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipFooterHandler#onAfterFooter(java.lang.Object, java.util.List)
	 */
	@Override
	public String[] onAfterFooter(Object context, List<TooltipItem> items) {
		TooltipFooterCallback toInvoke = getFooterCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onAfterFooter(getChart(), items);
		}
		return null;
	}

	private TooltipTitleCallback getTitleCallbackToInvoke() {
		// checks if callback is consistent
		if (getTitleCallback() != null) {
			// calls callback
			return getTitleCallback();
		} else if (Defaults.chart(getChart()).getTooltips().getCallbacks().getTitleCallback() != null) {
			// calls callback
			return Defaults.chart(getChart()).getTooltips().getCallbacks().getTitleCallback();
		} else if (Defaults.getGlobal().getTooltips().getCallbacks().getTitleCallback() != null) {
			// calls callback
			return Defaults.getGlobal().getTooltips().getCallbacks().getTitleCallback();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipTitleHandler#onBeforeTitle(java.lang.Object, java.util.List)
	 */
	@Override
	public String[] onBeforeTitle(Object context, List<TooltipItem> items) {
		TooltipTitleCallback toInvoke = getTitleCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onBeforeTitle(getChart(), items);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipTitleHandler#onTitle(java.lang.Object, java.util.List)
	 */
	@Override
	public String[] onTitle(Object context, List<TooltipItem> items) {
		TooltipTitleCallback toInvoke = getTitleCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onTitle(getChart(), items);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.callbacks.TooltipTitleHandler#onAfterTitle(java.lang.Object, java.util.List)
	 */
	@Override
	public String[] onAfterTitle(Object context, List<TooltipItem> items) {
		TooltipTitleCallback toInvoke = getTitleCallbackToInvoke();
		// checks if callback is consistent
		if (toInvoke != null) {
			// calls callback
			return toInvoke.onAfterTitle(getChart(), items);
		}
		return null;

	}

}