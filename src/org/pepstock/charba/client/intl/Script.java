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
package org.pepstock.charba.client.intl;

/**
 * Enumerates the standard codes for script identification  which can be used to create and manage locale.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Script implements IsLocaleItem
{
	/**
	 * International key, to use for localization, for <b>Adlam</b> script.
	 */
	 ADLM("Adlm", "Adlam"),

	/**
	 * International key, to use for localization, for <b>Afaka</b> script.
	 */
	 AFAK("Afak", "Afaka"),

	/**
	 * International key, to use for localization, for <b>Caucasian Albanian</b> script.
	 */
	 AGHB("Aghb", "Caucasian Albanian"),

	/**
	 * International key, to use for localization, for <b>Ahom, Tai Ahom</b> script.
	 */
	 AHOM("Ahom", "Ahom, Tai Ahom"),

	/**
	 * International key, to use for localization, for <b>Arabic</b> script.
	 */
	 ARAB("Arab", "Arabic"),

	/**
	 * International key, to use for localization, for <b>Arabic (Nastaliq variant)</b> script.
	 */
	 ARAN("Aran", "Arabic (Nastaliq variant)"),

	/**
	 * International key, to use for localization, for <b>Imperial Aramaic</b> script.
	 */
	 ARMI("Armi", "Imperial Aramaic"),

	/**
	 * International key, to use for localization, for <b>Armenian</b> script.
	 */
	 ARMN("Armn", "Armenian"),

	/**
	 * International key, to use for localization, for <b>Avestan</b> script.
	 */
	 AVST("Avst", "Avestan"),

	/**
	 * International key, to use for localization, for <b>Balinese</b> script.
	 */
	 BALI("Bali", "Balinese"),

	/**
	 * International key, to use for localization, for <b>Bamum</b> script.
	 */
	 BAMU("Bamu", "Bamum"),

	/**
	 * International key, to use for localization, for <b>Bassa Vah</b> script.
	 */
	 BASS("Bass", "Bassa Vah"),

	/**
	 * International key, to use for localization, for <b>Batak</b> script.
	 */
	 BATK("Batk", "Batak"),

	/**
	 * International key, to use for localization, for <b>Bengali (Bangla)</b> script.
	 */
	 BENG("Beng", "Bengali (Bangla)"),

	/**
	 * International key, to use for localization, for <b>Bhaiksuki</b> script.
	 */
	 BHKS("Bhks", "Bhaiksuki"),

	/**
	 * International key, to use for localization, for <b>Blissymbols</b> script.
	 */
	 BLIS("Blis", "Blissymbols"),

	/**
	 * International key, to use for localization, for <b>Bopomofo</b> script.
	 */
	 BOPO("Bopo", "Bopomofo"),

	/**
	 * International key, to use for localization, for <b>Brahmi</b> script.
	 */
	 BRAH("Brah", "Brahmi"),

	/**
	 * International key, to use for localization, for <b>Braille</b> script.
	 */
	 BRAI("Brai", "Braille"),

	/**
	 * International key, to use for localization, for <b>Buginese</b> script.
	 */
	 BUGI("Bugi", "Buginese"),

	/**
	 * International key, to use for localization, for <b>Buhid</b> script.
	 */
	 BUHD("Buhd", "Buhid"),

	/**
	 * International key, to use for localization, for <b>Chakma</b> script.
	 */
	 CAKM("Cakm", "Chakma"),

	/**
	 * International key, to use for localization, for <b>Unified Canadian Aboriginal Syllabics</b> script.
	 */
	 CANS("Cans", "Unified Canadian Aboriginal Syllabics"),

	/**
	 * International key, to use for localization, for <b>Carian</b> script.
	 */
	 CARI("Cari", "Carian"),

	/**
	 * International key, to use for localization, for <b>Cham</b> script.
	 */
	 CHAM("Cham", "Cham"),

	/**
	 * International key, to use for localization, for <b>Cherokee</b> script.
	 */
	 CHER("Cher", "Cherokee"),

	/**
	 * International key, to use for localization, for <b>Chorasmian</b> script.
	 */
	 CHRS("Chrs", "Chorasmian"),

	/**
	 * International key, to use for localization, for <b>Cirth</b> script.
	 */
	 CIRT("Cirt", "Cirth"),

	/**
	 * International key, to use for localization, for <b>Coptic</b> script.
	 */
	 COPT("Copt", "Coptic"),

	/**
	 * International key, to use for localization, for <b>Cypro-Minoan</b> script.
	 */
	 CPMN("Cpmn", "Cypro-Minoan"),

	/**
	 * International key, to use for localization, for <b>Cypriot syllabary</b> script.
	 */
	 CPRT("Cprt", "Cypriot syllabary"),

	/**
	 * International key, to use for localization, for <b>Cyrillic</b> script.
	 */
	 CYRL("Cyrl", "Cyrillic"),

	/**
	 * International key, to use for localization, for <b>Cyrillic (Old Church Slavonic variant)</b> script.
	 */
	 CYRS("Cyrs", "Cyrillic (Old Church Slavonic variant)"),

	/**
	 * International key, to use for localization, for <b>Devanagari (Nagari)</b> script.
	 */
	 DEVA("Deva", "Devanagari (Nagari)"),

	/**
	 * International key, to use for localization, for <b>Dives Akuru</b> script.
	 */
	 DIAK("Diak", "Dives Akuru"),

	/**
	 * International key, to use for localization, for <b>Dogra</b> script.
	 */
	 DOGR("Dogr", "Dogra"),

	/**
	 * International key, to use for localization, for <b>Deseret (Mormon)</b> script.
	 */
	 DSRT("Dsrt", "Deseret (Mormon)"),

	/**
	 * International key, to use for localization, for <b>Duployan shorthand, Duployan stenography</b> script.
	 */
	 DUPL("Dupl", "Duployan shorthand, Duployan stenography"),

	/**
	 * International key, to use for localization, for <b>Egyptian demotic</b> script.
	 */
	 EGYD("Egyd", "Egyptian demotic"),

	/**
	 * International key, to use for localization, for <b>Egyptian hieratic</b> script.
	 */
	 EGYH("Egyh", "Egyptian hieratic"),

	/**
	 * International key, to use for localization, for <b>Egyptian hieroglyphs</b> script.
	 */
	 EGYP("Egyp", "Egyptian hieroglyphs"),

	/**
	 * International key, to use for localization, for <b>Elbasan</b> script.
	 */
	 ELBA("Elba", "Elbasan"),

	/**
	 * International key, to use for localization, for <b>Elymaic</b> script.
	 */
	 ELYM("Elym", "Elymaic"),

	/**
	 * International key, to use for localization, for <b>Ethiopic (Geʻez)</b> script.
	 */
	 ETHI("Ethi", "Ethiopic (Geʻez)"),

	/**
	 * International key, to use for localization, for <b>Khutsuri (Asomtavruli and Nuskhuri)</b> script.
	 */
	 GEOK("Geok", "Khutsuri (Asomtavruli and Nuskhuri)"),

	/**
	 * International key, to use for localization, for <b>Georgian (Mkhedruli and Mtavruli)</b> script.
	 */
	 GEOR("Geor", "Georgian (Mkhedruli and Mtavruli)"),

	/**
	 * International key, to use for localization, for <b>Glagolitic</b> script.
	 */
	 GLAG("Glag", "Glagolitic"),

	/**
	 * International key, to use for localization, for <b>Gunjala Gondi</b> script.
	 */
	 GONG("Gong", "Gunjala Gondi"),

	/**
	 * International key, to use for localization, for <b>Masaram Gondi</b> script.
	 */
	 GONM("Gonm", "Masaram Gondi"),

	/**
	 * International key, to use for localization, for <b>Gothic</b> script.
	 */
	 GOTH("Goth", "Gothic"),

	/**
	 * International key, to use for localization, for <b>Grantha</b> script.
	 */
	 GRAN("Gran", "Grantha"),

	/**
	 * International key, to use for localization, for <b>Greek</b> script.
	 */
	 GREK("Grek", "Greek"),

	/**
	 * International key, to use for localization, for <b>Gujarati</b> script.
	 */
	 GUJR("Gujr", "Gujarati"),

	/**
	 * International key, to use for localization, for <b>Gurmukhi</b> script.
	 */
	 GURU("Guru", "Gurmukhi"),

	/**
	 * International key, to use for localization, for <b>Han with Bopomofo (alias for Han + Bopomofo)</b> script.
	 */
	 HANB("Hanb", "Han with Bopomofo (alias for Han + Bopomofo)"),

	/**
	 * International key, to use for localization, for <b>Hangul (Hangŭl, Hangeul)</b> script.
	 */
	 HANG("Hang", "Hangul (Hangŭl, Hangeul)"),

	/**
	 * International key, to use for localization, for <b>Han (Hanzi, Kanji, Hanja)</b> script.
	 */
	 HANI("Hani", "Han (Hanzi, Kanji, Hanja)"),

	/**
	 * International key, to use for localization, for <b>Hanunoo (Hanunóo)</b> script.
	 */
	 HANO("Hano", "Hanunoo (Hanunóo)"),

	/**
	 * International key, to use for localization, for <b>Han (Simplified variant)</b> script.
	 */
	 HANS("Hans", "Han (Simplified variant)"),

	/**
	 * International key, to use for localization, for <b>Han (Traditional variant)</b> script.
	 */
	 HANT("Hant", "Han (Traditional variant)"),

	/**
	 * International key, to use for localization, for <b>Hatran</b> script.
	 */
	 HATR("Hatr", "Hatran"),

	/**
	 * International key, to use for localization, for <b>Hebrew</b> script.
	 */
	 HEBR("Hebr", "Hebrew"),

	/**
	 * International key, to use for localization, for <b>Hiragana</b> script.
	 */
	 HIRA("Hira", "Hiragana"),

	/**
	 * International key, to use for localization, for <b>Anatolian Hieroglyphs (Luwian Hieroglyphs, Hittite Hieroglyphs)</b> script.
	 */
	 HLUW("Hluw", "Anatolian Hieroglyphs (Luwian Hieroglyphs, Hittite Hieroglyphs)"),

	/**
	 * International key, to use for localization, for <b>Pahawh Hmong</b> script.
	 */
	 HMNG("Hmng", "Pahawh Hmong"),

	/**
	 * International key, to use for localization, for <b>Nyiakeng Puachue Hmong</b> script.
	 */
	 HMNP("Hmnp", "Nyiakeng Puachue Hmong"),

	/**
	 * International key, to use for localization, for <b>Japanese syllabaries (alias for Hiragana + Katakana)</b> script.
	 */
	 HRKT("Hrkt", "Japanese syllabaries (alias for Hiragana + Katakana)"),

	/**
	 * International key, to use for localization, for <b>Old Hungarian (Hungarian Runic)</b> script.
	 */
	 HUNG("Hung", "Old Hungarian (Hungarian Runic)"),

	/**
	 * International key, to use for localization, for <b>Indus (Harappan)</b> script.
	 */
	 INDS("Inds", "Indus (Harappan)"),

	/**
	 * International key, to use for localization, for <b>Old Italic (Etruscan, Oscan, etc.)</b> script.
	 */
	 ITAL("Ital", "Old Italic (Etruscan, Oscan, etc.)"),

	/**
	 * International key, to use for localization, for <b>Jamo (alias for Jamo subset of Hangul)</b> script.
	 */
	 JAMO("Jamo", "Jamo (alias for Jamo subset of Hangul)"),

	/**
	 * International key, to use for localization, for <b>Javanese</b> script.
	 */
	 JAVA("Java", "Javanese"),

	/**
	 * International key, to use for localization, for <b>Japanese (alias for Han + Hiragana + Katakana)</b> script.
	 */
	 JPAN("Jpan", "Japanese (alias for Han + Hiragana + Katakana)"),

	/**
	 * International key, to use for localization, for <b>Jurchen</b> script.
	 */
	 JURC("Jurc", "Jurchen"),

	/**
	 * International key, to use for localization, for <b>Kayah Li</b> script.
	 */
	 KALI("Kali", "Kayah Li"),

	/**
	 * International key, to use for localization, for <b>Katakana</b> script.
	 */
	 KANA("Kana", "Katakana"),

	/**
	 * International key, to use for localization, for <b>Kharoshthi</b> script.
	 */
	 KHAR("Khar", "Kharoshthi"),

	/**
	 * International key, to use for localization, for <b>Khmer</b> script.
	 */
	 KHMR("Khmr", "Khmer"),

	/**
	 * International key, to use for localization, for <b>Khojki</b> script.
	 */
	 KHOJ("Khoj", "Khojki"),

	/**
	 * International key, to use for localization, for <b>Khitan large script</b> script.
	 */
	 KITL("Kitl", "Khitan large script"),

	/**
	 * International key, to use for localization, for <b>Khitan small script</b> script.
	 */
	 KITS("Kits", "Khitan small script"),

	/**
	 * International key, to use for localization, for <b>Kannada</b> script.
	 */
	 KNDA("Knda", "Kannada"),

	/**
	 * International key, to use for localization, for <b>Korean (alias for Hangul + Han)</b> script.
	 */
	 KORE("Kore", "Korean (alias for Hangul + Han)"),

	/**
	 * International key, to use for localization, for <b>Kpelle</b> script.
	 */
	 KPEL("Kpel", "Kpelle"),

	/**
	 * International key, to use for localization, for <b>Kaithi</b> script.
	 */
	 KTHI("Kthi", "Kaithi"),

	/**
	 * International key, to use for localization, for <b>Tai Tham (Lanna)</b> script.
	 */
	 LANA("Lana", "Tai Tham (Lanna)"),

	/**
	 * International key, to use for localization, for <b>Lao</b> script.
	 */
	 LAOO("Laoo", "Lao"),

	/**
	 * International key, to use for localization, for <b>Latin (Fraktur variant)</b> script.
	 */
	 LATF("Latf", "Latin (Fraktur variant)"),

	/**
	 * International key, to use for localization, for <b>Latin (Gaelic variant)</b> script.
	 */
	 LATG("Latg", "Latin (Gaelic variant)"),

	/**
	 * International key, to use for localization, for <b>Latin</b> script.
	 */
	 LATN("Latn", "Latin"),

	/**
	 * International key, to use for localization, for <b>Leke</b> script.
	 */
	 LEKE("Leke", "Leke"),

	/**
	 * International key, to use for localization, for <b>Lepcha (Róng)</b> script.
	 */
	 LEPC("Lepc", "Lepcha (Róng)"),

	/**
	 * International key, to use for localization, for <b>Limbu</b> script.
	 */
	 LIMB("Limb", "Limbu"),

	/**
	 * International key, to use for localization, for <b>Linear A</b> script.
	 */
	 LINA("Lina", "Linear A"),

	/**
	 * International key, to use for localization, for <b>Linear B</b> script.
	 */
	 LINB("Linb", "Linear B"),

	/**
	 * International key, to use for localization, for <b>Lisu (Fraser)</b> script.
	 */
	 LISU("Lisu", "Lisu (Fraser)"),

	/**
	 * International key, to use for localization, for <b>Loma</b> script.
	 */
	 LOMA("Loma", "Loma"),

	/**
	 * International key, to use for localization, for <b>Lycian</b> script.
	 */
	 LYCI("Lyci", "Lycian"),

	/**
	 * International key, to use for localization, for <b>Lydian</b> script.
	 */
	 LYDI("Lydi", "Lydian"),

	/**
	 * International key, to use for localization, for <b>Mahajani</b> script.
	 */
	 MAHJ("Mahj", "Mahajani"),

	/**
	 * International key, to use for localization, for <b>Makasar</b> script.
	 */
	 MAKA("Maka", "Makasar"),

	/**
	 * International key, to use for localization, for <b>Mandaic, Mandaean</b> script.
	 */
	 MAND("Mand", "Mandaic, Mandaean"),

	/**
	 * International key, to use for localization, for <b>Manichaean</b> script.
	 */
	 MANI("Mani", "Manichaean"),

	/**
	 * International key, to use for localization, for <b>Marchen</b> script.
	 */
	 MARC("Marc", "Marchen"),

	/**
	 * International key, to use for localization, for <b>Mayan hieroglyphs</b> script.
	 */
	 MAYA("Maya", "Mayan hieroglyphs"),

	/**
	 * International key, to use for localization, for <b>Medefaidrin (Oberi Okaime, Oberi Ɔkaimɛ)</b> script.
	 */
	 MEDF("Medf", "Medefaidrin (Oberi Okaime, Oberi Ɔkaimɛ)"),

	/**
	 * International key, to use for localization, for <b>Mende Kikakui</b> script.
	 */
	 MEND("Mend", "Mende Kikakui"),

	/**
	 * International key, to use for localization, for <b>Meroitic Cursive</b> script.
	 */
	 MERC("Merc", "Meroitic Cursive"),

	/**
	 * International key, to use for localization, for <b>Meroitic Hieroglyphs</b> script.
	 */
	 MERO("Mero", "Meroitic Hieroglyphs"),

	/**
	 * International key, to use for localization, for <b>Malayalam</b> script.
	 */
	 MLYM("Mlym", "Malayalam"),

	/**
	 * International key, to use for localization, for <b>Modi, Moḍī</b> script.
	 */
	 MODI("Modi", "Modi, Moḍī"),

	/**
	 * International key, to use for localization, for <b>Mongolian</b> script.
	 */
	 MONG("Mong", "Mongolian"),

	/**
	 * International key, to use for localization, for <b>Moon (Moon code, Moon script, Moon type)</b> script.
	 */
	 MOON("Moon", "Moon (Moon code, Moon script, Moon type)"),

	/**
	 * International key, to use for localization, for <b>Mro, Mru</b> script.
	 */
	 MROO("Mroo", "Mro, Mru"),

	/**
	 * International key, to use for localization, for <b>Meitei Mayek (Meithei, Meetei)</b> script.
	 */
	 MTEI("Mtei", "Meitei Mayek (Meithei, Meetei)"),

	/**
	 * International key, to use for localization, for <b>Multani</b> script.
	 */
	 MULT("Mult", "Multani"),

	/**
	 * International key, to use for localization, for <b>Myanmar (Burmese)</b> script.
	 */
	 MYMR("Mymr", "Myanmar (Burmese)"),

	/**
	 * International key, to use for localization, for <b>Nandinagari</b> script.
	 */
	 NAND("Nand", "Nandinagari"),

	/**
	 * International key, to use for localization, for <b>Old North Arabian (Ancient North Arabian)</b> script.
	 */
	 NARB("Narb", "Old North Arabian (Ancient North Arabian)"),

	/**
	 * International key, to use for localization, for <b>Nabataean</b> script.
	 */
	 NBAT("Nbat", "Nabataean"),

	/**
	 * International key, to use for localization, for <b>Newa, Newar, Newari, Nepāla lipi</b> script.
	 */
	 NEWA("Newa", "Newa, Newar, Newari, Nepāla lipi"),

	/**
	 * International key, to use for localization, for <b>Naxi Dongba (na²¹ɕi³³ to³³ba²¹, Nakhi Tomba)</b> script.
	 */
	 NKDB("Nkdb", "Naxi Dongba (na²¹ɕi³³ to³³ba²¹, Nakhi Tomba)"),

	/**
	 * International key, to use for localization, for <b>Naxi Geba (na²¹ɕi³³ gʌ²¹ba²¹, 'Na-'Khi ²Ggŏ-¹baw, Nakhi Geba)</b> script.
	 */
	 NKGB("Nkgb", "Naxi Geba (na²¹ɕi³³ gʌ²¹ba²¹, 'Na-'Khi ²Ggŏ-¹baw, Nakhi Geba)"),

	/**
	 * International key, to use for localization, for <b>N’Ko</b> script.
	 */
	 NKOO("Nkoo", "N’Ko"),

	/**
	 * International key, to use for localization, for <b>Nüshu</b> script.
	 */
	 NSHU("Nshu", "Nüshu"),

	/**
	 * International key, to use for localization, for <b>Ogham</b> script.
	 */
	 OGAM("Ogam", "Ogham"),

	/**
	 * International key, to use for localization, for <b>Ol Chiki (Ol Cemet’, Ol, Santali)</b> script.
	 */
	 OLCK("Olck", "Ol Chiki (Ol Cemet’, Ol, Santali)"),

	/**
	 * International key, to use for localization, for <b>Old Turkic, Orkhon Runic</b> script.
	 */
	 ORKH("Orkh", "Old Turkic, Orkhon Runic"),

	/**
	 * International key, to use for localization, for <b>Oriya (Odia)</b> script.
	 */
	 ORYA("Orya", "Oriya (Odia)"),

	/**
	 * International key, to use for localization, for <b>Osage</b> script.
	 */
	 OSGE("Osge", "Osage"),

	/**
	 * International key, to use for localization, for <b>Osmanya</b> script.
	 */
	 OSMA("Osma", "Osmanya"),

	/**
	 * International key, to use for localization, for <b>Palmyrene</b> script.
	 */
	 PALM("Palm", "Palmyrene"),

	/**
	 * International key, to use for localization, for <b>Pau Cin Hau</b> script.
	 */
	 PAUC("Pauc", "Pau Cin Hau"),

	/**
	 * International key, to use for localization, for <b>Old Permic</b> script.
	 */
	 PERM("Perm", "Old Permic"),

	/**
	 * International key, to use for localization, for <b>Phags-pa</b> script.
	 */
	 PHAG("Phag", "Phags-pa"),

	/**
	 * International key, to use for localization, for <b>Inscriptional Pahlavi</b> script.
	 */
	 PHLI("Phli", "Inscriptional Pahlavi"),

	/**
	 * International key, to use for localization, for <b>Psalter Pahlavi</b> script.
	 */
	 PHLP("Phlp", "Psalter Pahlavi"),

	/**
	 * International key, to use for localization, for <b>Book Pahlavi</b> script.
	 */
	 PHLV("Phlv", "Book Pahlavi"),

	/**
	 * International key, to use for localization, for <b>Phoenician</b> script.
	 */
	 PHNX("Phnx", "Phoenician"),

	/**
	 * International key, to use for localization, for <b>Klingon (KLI pIqaD)</b> script.
	 */
	 PIQD("Piqd", "Klingon (KLI pIqaD)"),

	/**
	 * International key, to use for localization, for <b>Miao (Pollard)</b> script.
	 */
	 PLRD("Plrd", "Miao (Pollard)"),

	/**
	 * International key, to use for localization, for <b>Inscriptional Parthian</b> script.
	 */
	 PRTI("Prti", "Inscriptional Parthian"),

	/**
	 * International key, to use for localization, for <b>Reserved for private use (start)</b> script.
	 */
	 QAAA("Qaaa", "Reserved for private use (start)"),

	/**
	 * International key, to use for localization, for <b>Reserved for private use (end)</b> script.
	 */
	 QABX("Qabx", "Reserved for private use (end)"),

	/**
	 * International key, to use for localization, for <b>Rejang (Redjang, Kaganga)</b> script.
	 */
	 RJNG("Rjng", "Rejang (Redjang, Kaganga)"),

	/**
	 * International key, to use for localization, for <b>Hanifi Rohingya</b> script.
	 */
	 ROHG("Rohg", "Hanifi Rohingya"),

	/**
	 * International key, to use for localization, for <b>Rongorongo</b> script.
	 */
	 RORO("Roro", "Rongorongo"),

	/**
	 * International key, to use for localization, for <b>Runic</b> script.
	 */
	 RUNR("Runr", "Runic"),

	/**
	 * International key, to use for localization, for <b>Samaritan</b> script.
	 */
	 SAMR("Samr", "Samaritan"),

	/**
	 * International key, to use for localization, for <b>Sarati</b> script.
	 */
	 SARA("Sara", "Sarati"),

	/**
	 * International key, to use for localization, for <b>Old South Arabian</b> script.
	 */
	 SARB("Sarb", "Old South Arabian"),

	/**
	 * International key, to use for localization, for <b>Saurashtra</b> script.
	 */
	 SAUR("Saur", "Saurashtra"),

	/**
	 * International key, to use for localization, for <b>SignWriting</b> script.
	 */
	 SGNW("Sgnw", "SignWriting"),

	/**
	 * International key, to use for localization, for <b>Shavian (Shaw)</b> script.
	 */
	 SHAW("Shaw", "Shavian (Shaw)"),

	/**
	 * International key, to use for localization, for <b>Sharada, Śāradā</b> script.
	 */
	 SHRD("Shrd", "Sharada, Śāradā"),

	/**
	 * International key, to use for localization, for <b>Shuishu</b> script.
	 */
	 SHUI("Shui", "Shuishu"),

	/**
	 * International key, to use for localization, for <b>Siddham, Siddhaṃ, Siddhamātṛkā</b> script.
	 */
	 SIDD("Sidd", "Siddham, Siddhaṃ, Siddhamātṛkā"),

	/**
	 * International key, to use for localization, for <b>Khudawadi, Sindhi</b> script.
	 */
	 SIND("Sind", "Khudawadi, Sindhi"),

	/**
	 * International key, to use for localization, for <b>Sinhala</b> script.
	 */
	 SINH("Sinh", "Sinhala"),

	/**
	 * International key, to use for localization, for <b>Sogdian</b> script.
	 */
	 SOGD("Sogd", "Sogdian"),

	/**
	 * International key, to use for localization, for <b>Old Sogdian</b> script.
	 */
	 SOGO("Sogo", "Old Sogdian"),

	/**
	 * International key, to use for localization, for <b>Sora Sompeng</b> script.
	 */
	 SORA("Sora", "Sora Sompeng"),

	/**
	 * International key, to use for localization, for <b>Soyombo</b> script.
	 */
	 SOYO("Soyo", "Soyombo"),

	/**
	 * International key, to use for localization, for <b>Sundanese</b> script.
	 */
	 SUND("Sund", "Sundanese"),

	/**
	 * International key, to use for localization, for <b>Syloti Nagri</b> script.
	 */
	 SYLO("Sylo", "Syloti Nagri"),

	/**
	 * International key, to use for localization, for <b>Syriac</b> script.
	 */
	 SYRC("Syrc", "Syriac"),

	/**
	 * International key, to use for localization, for <b>Syriac (Estrangelo variant)</b> script.
	 */
	 SYRE("Syre", "Syriac (Estrangelo variant)"),

	/**
	 * International key, to use for localization, for <b>Syriac (Western variant)</b> script.
	 */
	 SYRJ("Syrj", "Syriac (Western variant)"),

	/**
	 * International key, to use for localization, for <b>Syriac (Eastern variant)</b> script.
	 */
	 SYRN("Syrn", "Syriac (Eastern variant)"),

	/**
	 * International key, to use for localization, for <b>Tagbanwa</b> script.
	 */
	 TAGB("Tagb", "Tagbanwa"),

	/**
	 * International key, to use for localization, for <b>Takri, Ṭākrī, Ṭāṅkrī</b> script.
	 */
	 TAKR("Takr", "Takri, Ṭākrī, Ṭāṅkrī"),

	/**
	 * International key, to use for localization, for <b>Tai Le</b> script.
	 */
	 TALE("Tale", "Tai Le"),

	/**
	 * International key, to use for localization, for <b>New Tai Lue</b> script.
	 */
	 TALU("Talu", "New Tai Lue"),

	/**
	 * International key, to use for localization, for <b>Tamil</b> script.
	 */
	 TAML("Taml", "Tamil"),

	/**
	 * International key, to use for localization, for <b>Tangut</b> script.
	 */
	 TANG("Tang", "Tangut"),

	/**
	 * International key, to use for localization, for <b>Tai Viet</b> script.
	 */
	 TAVT("Tavt", "Tai Viet"),

	/**
	 * International key, to use for localization, for <b>Telugu</b> script.
	 */
	 TELU("Telu", "Telugu"),

	/**
	 * International key, to use for localization, for <b>Tengwar</b> script.
	 */
	 TENG("Teng", "Tengwar"),

	/**
	 * International key, to use for localization, for <b>Tifinagh (Berber)</b> script.
	 */
	 TFNG("Tfng", "Tifinagh (Berber)"),

	/**
	 * International key, to use for localization, for <b>Tagalog (Baybayin, Alibata)</b> script.
	 */
	 TGLG("Tglg", "Tagalog (Baybayin, Alibata)"),

	/**
	 * International key, to use for localization, for <b>Thaana</b> script.
	 */
	 THAA("Thaa", "Thaana"),

	/**
	 * International key, to use for localization, for <b>Thai</b> script.
	 */
	 THAI("Thai", "Thai"),

	/**
	 * International key, to use for localization, for <b>Tibetan</b> script.
	 */
	 TIBT("Tibt", "Tibetan"),

	/**
	 * International key, to use for localization, for <b>Tirhuta</b> script.
	 */
	 TIRH("Tirh", "Tirhuta"),

	/**
	 * International key, to use for localization, for <b>Toto</b> script.
	 */
	 TOTO("Toto", "Toto"),

	/**
	 * International key, to use for localization, for <b>Ugaritic</b> script.
	 */
	 UGAR("Ugar", "Ugaritic"),

	/**
	 * International key, to use for localization, for <b>Vai</b> script.
	 */
	 VAII("Vaii", "Vai"),

	/**
	 * International key, to use for localization, for <b>Visible Speech</b> script.
	 */
	 VISP("Visp", "Visible Speech"),

	/**
	 * International key, to use for localization, for <b>Warang Citi (Varang Kshiti)</b> script.
	 */
	 WARA("Wara", "Warang Citi (Varang Kshiti)"),

	/**
	 * International key, to use for localization, for <b>Wancho</b> script.
	 */
	 WCHO("Wcho", "Wancho"),

	/**
	 * International key, to use for localization, for <b>Woleai</b> script.
	 */
	 WOLE("Wole", "Woleai"),

	/**
	 * International key, to use for localization, for <b>Old Persian</b> script.
	 */
	 XPEO("Xpeo", "Old Persian"),

	/**
	 * International key, to use for localization, for <b>Cuneiform, Sumero-Akkadian</b> script.
	 */
	 XSUX("Xsux", "Cuneiform, Sumero-Akkadian"),

	/**
	 * International key, to use for localization, for <b>Yezidi</b> script.
	 */
	 YEZI("Yezi", "Yezidi"),

	/**
	 * International key, to use for localization, for <b>Yi</b> script.
	 */
	 YIII("Yiii", "Yi"),

	/**
	 * International key, to use for localization, for <b>Zanabazar Square (Zanabazarin Dörböljin Useg, Xewtee Dörböljin Bicig, Horizontal Square Script)</b> script.
	 */
	 ZANB("Zanb", "Zanabazar Square (Zanabazarin Dörböljin Useg, Xewtee Dörböljin Bicig, Horizontal Square Script)"),

	/**
	 * International key, to use for localization, for <b>Code for inherited script</b> script.
	 */
	 ZINH("Zinh", "Code for inherited script"),

	/**
	 * International key, to use for localization, for <b>Mathematical notation</b> script.
	 */
	 ZMTH("Zmth", "Mathematical notation"),

	/**
	 * International key, to use for localization, for <b>Symbols (Emoji variant)</b> script.
	 */
	 ZSYE("Zsye", "Symbols (Emoji variant)"),

	/**
	 * International key, to use for localization, for <b>Symbols</b> script.
	 */
	 ZSYM("Zsym", "Symbols"),

	/**
	 * International key, to use for localization, for <b>Code for unwritten documents</b> script.
	 */
	 ZXXX("Zxxx", "Code for unwritten documents"),

	/**
	 * International key, to use for localization, for <b>Code for undetermined script</b> script.
	 */
	 ZYYY("Zyyy", "Code for undetermined script"),

	/**
	 * International key, to use for localization, for <b>Code for uncoded script</b> script.
	 */
	 ZZZZ("Zzzz", "Code for uncoded script");

	// value for key
	private final String value;
	// name of region
	private final String name;

	/**
	 * Creates the object with script value and its name.
	 * 
	 * @param value script identifier
	 * @param name name of the region
	 */
	private Script(String value, String name) {
		this.value = value;
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/**
	 * Returns the name of the region.
	 * 
	 * @return the name of the region
	 */
	@Override
	public String getName() {
		return name;
	}

}