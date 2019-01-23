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
package org.pepstock.charba.client.colors;

/**
 * GWT material color constants.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#colors">GWT material colors</a>
 *
 */
public enum GwtMaterialColor implements IsColor
{
	RED_LIGHTEN_5("#ffebee"),
	RED_LIGHTEN_4("#ffcdd2"),
	RED_LIGHTEN_3("#ef9a9a"),
	RED_LIGHTEN_2("#e57373"),
	RED_LIGHTEN_1("#ef5350"),
	RED("#f44336"),
	RED_DARKEN_1("#e53935"),
	RED_DARKEN_2("#d32f2f"),
	RED_DARKEN_3("#c62828"),
	RED_DARKEN_4("#b71c1c"),
	RED_ACCENT_1("#ff8a80"),
	RED_ACCENT_2("#ff5252"),
	RED_ACCENT_3("#ff1744"),
	RED_ACCENT_4("#d50000"),
	PINK_LIGHTEN_5("#fce4ec"),
	PINK_LIGHTEN_4("#f8bbd0"),
	PINK_LIGHTEN_3("#f48fb1"),
	PINK_LIGHTEN_2("#f06292"),
	PINK_LIGHTEN_1("#ec407a"),
	PINK("#e91e63"),
	PINK_DARKEN_1("#d81b60"),
	PINK_DARKEN_2("#c2185b"),
	PINK_DARKEN_3("#ad1457"),
	PINK_DARKEN_4("#880e4f"),
	PINK_ACCENT_1("#ff80ab"),
	PINK_ACCENT_2("#ff4081"),
	PINK_ACCENT_3("#f50057"),
	PINK_ACCENT_4("#c51162"),
	PURPLE_LIGHTEN_5("#f3e5f5"),
	PURPLE_LIGHTEN_4("#e1bee7"),
	PURPLE_LIGHTEN_3("#ce93d8"),
	PURPLE_LIGHTEN_2("#ba68c8"),
	PURPLE_LIGHTEN_1("#ab47bc"),
	PURPLE("#9c27b0"),
	PURPLE_DARKEN_1("#8e24aa"),
	PURPLE_DARKEN_2("#7b1fa2"),
	PURPLE_DARKEN_3("#6a1b9a"),
	PURPLE_DARKEN_4("#4a148c"),
	PURPLE_ACCENT_1("#ea80fc"),
	PURPLE_ACCENT_2("#e040fb"),
	PURPLE_ACCENT_3("#d500f9"),
	PURPLE_ACCENT_4("#aa00ff"),
	DEEP_PURPLE_LIGHTEN_5("#ede7f6"),
	DEEP_PURPLE_LIGHTEN_4("#d1c4e9"),
	DEEP_PURPLE_LIGHTEN_3("#b39ddb"),
	DEEP_PURPLE_LIGHTEN_2("#9575cd"),
	DEEP_PURPLE_LIGHTEN_1("#7e57c2"),
	DEEP_PURPLE("#673ab7"),
	DEEP_PURPLE_DARKEN_1("#5e35b1"),
	DEEP_PURPLE_DARKEN_2("#512da8"),
	DEEP_PURPLE_DARKEN_3("#4527a0"),
	DEEP_PURPLE_DARKEN_4("#311b92"),
	DEEP_PURPLE_ACCENT_1("#b388ff"),
	DEEP_PURPLE_ACCENT_2("#7c4dff"),
	DEEP_PURPLE_ACCENT_3("#651fff"),
	DEEP_PURPLE_ACCENT_4("#6200ea"),
	INDIGO_LIGHTEN_5("#e8eaf6"),
	INDIGO_LIGHTEN_4("#c5cae9"),
	INDIGO_LIGHTEN_3("#9fa8da"),
	INDIGO_LIGHTEN_2("#7986cb"),
	INDIGO_LIGHTEN_1("#5c6bc0"),
	INDIGO("#3f51b5"),
	INDIGO_DARKEN_1("#3949ab"),
	INDIGO_DARKEN_2("#303f9f"),
	INDIGO_DARKEN_3("#283593"),
	INDIGO_DARKEN_4("#1a237e"),
	INDIGO_ACCENT_1("#8c9eff"),
	INDIGO_ACCENT_2("#536dfe"),
	INDIGO_ACCENT_3("#3d5afe"),
	INDIGO_ACCENT_4("#304ffe"),
	BLUE_LIGHTEN_5("#e3f2fd"),
	BLUE_LIGHTEN_4("#bbdefb"),
	BLUE_LIGHTEN_3("#90caf9"),
	BLUE_LIGHTEN_2("#64b5f6"),
	BLUE_LIGHTEN_1("#42a5f5"),
	BLUE("#2196f3"),
	BLUE_DARKEN_1("#1e88e5"),
	BLUE_DARKEN_2("#1976d2"),
	BLUE_DARKEN_3("#1565c0"),
	BLUE_DARKEN_4("#0d47a1"),
	BLUE_ACCENT_1("#82b1ff"),
	BLUE_ACCENT_2("#448aff"),
	BLUE_ACCENT_3("#2979ff"),
	BLUE_ACCENT_4("#2962ff"),
	LIGHT_BLUE_LIGHTEN_5("#e1f5fe"),
	LIGHT_BLUE_LIGHTEN_4("#b3e5fc"),
	LIGHT_BLUE_LIGHTEN_3("#81d4fa"),
	LIGHT_BLUE_LIGHTEN_2("#4fc3f7"),
	LIGHT_BLUE_LIGHTEN_1("#29b6f6"),
	LIGHT_BLUE("#03a9f4"),
	LIGHT_BLUE_DARKEN_1("#039be5"),
	LIGHT_BLUE_DARKEN_2("#0288d1"),
	LIGHT_BLUE_DARKEN_3("#0277bd"),
	LIGHT_BLUE_DARKEN_4("#01579b"),
	LIGHT_BLUE_ACCENT_1("#80d8ff"),
	LIGHT_BLUE_ACCENT_2("#40c4ff"),
	LIGHT_BLUE_ACCENT_3("#00b0ff"),
	LIGHT_BLUE_ACCENT_4("#0091ea"),
	CYAN_LIGHTEN_5("#e0f7fa"),
	CYAN_LIGHTEN_4("#b2ebf2"),
	CYAN_LIGHTEN_3("#80deea"),
	CYAN_LIGHTEN_2("#4dd0e1"),
	CYAN_LIGHTEN_1("#26c6da"),
	CYAN("#00bcd4"),
	CYAN_DARKEN_1("#00acc1"),
	CYAN_DARKEN_2("#0097a7"),
	CYAN_DARKEN_3("#00838f"),
	CYAN_DARKEN_4("#006064"),
	CYAN_ACCENT_1("#84ffff"),
	CYAN_ACCENT_2("#18ffff"),
	CYAN_ACCENT_3("#00e5ff"),
	CYAN_ACCENT_4("#00b8d4"),
	TEAL_LIGHTEN_5("#e0f2f1"),
	TEAL_LIGHTEN_4("#b2dfdb"),
	TEAL_LIGHTEN_3("#80cbc4"),
	TEAL_LIGHTEN_2("#4db6ac"),
	TEAL_LIGHTEN_1("#26a69a"),
	TEAL("#009688"),
	TEAL_DARKEN_1("#00897b"),
	TEAL_DARKEN_2("#00796b"),
	TEAL_DARKEN_3("#00695c"),
	TEAL_DARKEN_4("#004d40"),
	TEAL_ACCENT_1("#a7ffeb"),
	TEAL_ACCENT_2("#64ffda"),
	TEAL_ACCENT_3("#1de9b6"),
	TEAL_ACCENT_4("#00bfa5"),
	GREEN_LIGHTEN_5("#e8f5e9"),
	GREEN_LIGHTEN_4("#c8e6c9"),
	GREEN_LIGHTEN_3("#a5d6a7"),
	GREEN_LIGHTEN_2("#81c784"),
	GREEN_LIGHTEN_1("#66bb6a"),
	GREEN("#4caf50"),
	GREEN_DARKEN_1("#43a047"),
	GREEN_DARKEN_2("#388e3c"),
	GREEN_DARKEN_3("#2e7d32"),
	GREEN_DARKEN_4("#1b5e20"),
	GREEN_ACCENT_1("#b9f6ca"),
	GREEN_ACCENT_2("#69f0ae"),
	GREEN_ACCENT_3("#00e676"),
	GREEN_ACCENT_4("#00c853"),
	LIGHT_GREEN_LIGHTEN_5("#f1f8e9"),
	LIGHT_GREEN_LIGHTEN_4("#dcedc8"),
	LIGHT_GREEN_LIGHTEN_3("#c5e1a5"),
	LIGHT_GREEN_LIGHTEN_2("#aed581"),
	LIGHT_GREEN_LIGHTEN_1("#9ccc65"),
	LIGHT_GREEN("#8bc34a"),
	LIGHT_GREEN_DARKEN_1("#7cb342"),
	LIGHT_GREEN_DARKEN_2("#689f38"),
	LIGHT_GREEN_DARKEN_3("#558b2f"),
	LIGHT_GREEN_DARKEN_4("#33691e"),
	LIGHT_GREEN_ACCENT_1("#ccff90"),
	LIGHT_GREEN_ACCENT_2("#b2ff59"),
	LIGHT_GREEN_ACCENT_3("#76ff03"),
	LIGHT_GREEN_ACCENT_4("#64dd17"),
	LIME_LIGHTEN_5("#f9fbe7"),
	LIME_LIGHTEN_4("#f0f4c3"),
	LIME_LIGHTEN_3("#e6ee9c"),
	LIME_LIGHTEN_2("#dce775"),
	LIME_LIGHTEN_1("#d4e157"),
	LIME("#cddc39"),
	LIME_DARKEN_1("#c0ca33"),
	LIME_DARKEN_2("#afb42b"),
	LIME_DARKEN_3("#9e9d24"),
	LIME_DARKEN_4("#827717"),
	LIME_ACCENT_1("#f4ff81"),
	LIME_ACCENT_2("#eeff41"),
	LIME_ACCENT_3("#c6ff00"),
	LIME_ACCENT_4("#aeea00"),
	YELLOW_LIGHTEN_5("#fffde7"),
	YELLOW_LIGHTEN_4("#fff9c4"),
	YELLOW_LIGHTEN_3("#fff59d"),
	YELLOW_LIGHTEN_2("#fff176"),
	YELLOW_LIGHTEN_1("#ffee58"),
	YELLOW("#ffeb3b"),
	YELLOW_DARKEN_1("#fdd835"),
	YELLOW_DARKEN_2("#fbc02d"),
	YELLOW_DARKEN_3("#f9a825"),
	YELLOW_DARKEN_4("#f57f17"),
	YELLOW_ACCENT_1("#ffff8d"),
	YELLOW_ACCENT_2("#ffff00"),
	YELLOW_ACCENT_3("#ffea00"),
	YELLOW_ACCENT_4("#ffd600"),
	AMBER_LIGHTEN_5("#fff8e1"),
	AMBER_LIGHTEN_4("#ffecb3"),
	AMBER_LIGHTEN_3("#ffe082"),
	AMBER_LIGHTEN_2("#ffd54f"),
	AMBER_LIGHTEN_1("#ffca28"),
	AMBER("#ffc107"),
	AMBER_DARKEN_1("#ffb300"),
	AMBER_DARKEN_2("#ffa000"),
	AMBER_DARKEN_3("#ff8f00"),
	AMBER_DARKEN_4("#ff6f00"),
	AMBER_ACCENT_1("#ffe57f"),
	AMBER_ACCENT_2("#ffd740"),
	AMBER_ACCENT_3("#ffc400"),
	AMBER_ACCENT_4("#ffab00"),
	ORANGE_LIGHTEN_5("#fff3e0"),
	ORANGE_LIGHTEN_4("#ffe0b2"),
	ORANGE_LIGHTEN_3("#ffcc80"),
	ORANGE_LIGHTEN_2("#ffb74d"),
	ORANGE_LIGHTEN_1("#ffa726"),
	ORANGE("#ff9800"),
	ORANGE_DARKEN_1("#fb8c00"),
	ORANGE_DARKEN_2("#f57c00"),
	ORANGE_DARKEN_3("#ef6c00"),
	ORANGE_DARKEN_4("#e65100"),
	ORANGE_ACCENT_1("#ffd180"),
	ORANGE_ACCENT_2("#ffab40"),
	ORANGE_ACCENT_3("#ff9100"),
	ORANGE_ACCENT_4("#ff6d00"),
	DEEP_ORANGE_LIGHTEN_5("#fbe9e7"),
	DEEP_ORANGE_LIGHTEN_4("#ffccbc"),
	DEEP_ORANGE_LIGHTEN_3("#ffab91"),
	DEEP_ORANGE_LIGHTEN_2("#ff8a65"),
	DEEP_ORANGE_LIGHTEN_1("#ff7043"),
	DEEP_ORANGE("#ff5722"),
	DEEP_ORANGE_DARKEN_1("#f4511e"),
	DEEP_ORANGE_DARKEN_2("#e64a19"),
	DEEP_ORANGE_DARKEN_3("#d84315"),
	DEEP_ORANGE_DARKEN_4("#bf360c"),
	DEEP_ORANGE_ACCENT_1("#ff9e80"),
	DEEP_ORANGE_ACCENT_2("#ff6e40"),
	DEEP_ORANGE_ACCENT_3("#ff3d00"),
	DEEP_ORANGE_ACCENT_4("#dd2c00"),
	BROWN_LIGHTEN_5("#efebe9"),
	BROWN_LIGHTEN_4("#d7ccc8"),
	BROWN_LIGHTEN_3("#bcaaa4"),
	BROWN_LIGHTEN_2("#a1887f"),
	BROWN_LIGHTEN_1("#8d6e63"),
	BROWN("#795548"),
	BROWN_DARKEN_1("#6d4c41"),
	BROWN_DARKEN_2("#5d4037"),
	BROWN_DARKEN_3("#4e342e"),
	BROWN_DARKEN_4("#3e2723"),
	GREY_LIGHTEN_5("#fafafa"),
	GREY_LIGHTEN_4("#f5f5f5"),
	GREY_LIGHTEN_3("#eeeeee"),
	GREY_LIGHTEN_2("#e0e0e0"),
	GREY_LIGHTEN_1("#bdbdbd"),
	GREY("#9e9e9e"),
	GREY_DARKEN_1("#757575"),
	GREY_DARKEN_2("#616161"),
	GREY_DARKEN_3("#424242"),
	GREY_DARKEN_4("#212121"),
	BLUE_GREY_LIGHTEN_5("#eceff1"),
	BLUE_GREY_LIGHTEN_4("#cfd8dc"),
	BLUE_GREY_LIGHTEN_3("#b0bec5"),
	BLUE_GREY_LIGHTEN_2("#90a4ae"),
	BLUE_GREY_LIGHTEN_1("#78909c"),
	BLUE_GREY("#607d8b"),
	BLUE_GREY_DARKEN_1("#546e7a"),
	BLUE_GREY_DARKEN_2("#455a64"),
	BLUE_GREY_DARKEN_3("#37474f"),
	BLUE_GREY_DARKEN_4("#263238"),
	BLACK("#000000"),
	WHITE("#ffffff");

	// color string representation in HEX
	private final String hexValue;
	// color instance
	private final IsColor color;

	/**
	 * Creates a color with HEX value.
	 * 
	 * @param hexValue color string representation in HEX
	 */
	private GwtMaterialColor(String hexValue) {
		this.hexValue = hexValue;
		// checks if the HEX value
		String newHexvalue = hexValue.substring(1);
		// reads colors
		String redValue = newHexvalue.substring(0, 2);
		int red = Integer.parseInt(redValue, 16);
		String greenValue = newHexvalue.substring(2, 4);
		int green = Integer.parseInt(greenValue, 16);
		String blueValue = newHexvalue.substring(4);
		int blue = Integer.parseInt(blueValue, 16);
		color = new Color(red, green, blue, Color.DEFAULT_ALPHA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#getRed()
	 */
	@Override
	public int getRed() {
		return color.getRed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#getGreen()
	 */
	@Override
	public int getGreen() {
		return color.getGreen();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#getBlue()
	 */
	@Override
	public int getBlue() {
		return color.getBlue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#getAlpha()
	 */
	@Override
	public double getAlpha() {
		return color.getAlpha();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#alpha(double)
	 */
	@Override
	public IsColor alpha(double alpha) {
		return color.alpha(alpha);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#toRGBA()
	 */
	@Override
	public String toRGBA() {
		return color.toRGBA();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#toRGB()
	 */
	@Override
	public String toRGB() {
		return color.toRGB();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.utils.IsColor#toHex()
	 */
	@Override
	public String toHex() {
		return hexValue;
	}
}
