package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;

public class Options extends BaseOptions<Animation, Legend>{
	
	private final Animation animation;

	private final Legend legend;
	
	public Options(IsDefaultOptions defaultValues) {
		this(defaultValues, null);
	}

	protected Options(IsDefaultOptions defaultValues, NativeOptions delegated) {
		// if delegated == null, is global or chart, noit config
		super(defaultValues, delegated);
		animation = new Animation(this, getDefaultValues().getAnimation(), getDelegated().getAnimation());
		legend = new Legend(this, getDefaultValues().getLegend(),getDelegated().getLegend());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseOptions#getAnimation()
	 */
	@Override
	public Animation getAnimation() {
		return animation;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseOptions#getLegend()
	 */
	@Override
	public Legend getLegend() {
		return legend;
	}

}
