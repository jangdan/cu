Color Utility
===========

*version.txt*



Beta
--------------
0.b3.1.1 (1.19.14)
	- Updated About.java

0.b3.1.0 (1.15.14)
	- org.apache.log4j for logging
	

0.b3.0.0 (8.18.13)
	- Changed some names
	- Disabled locking of panel visibility
		- For future full-screen function
	- Easy View color data value (a.k.a. 'text') visibility toggle

	- Added background color changing slider for contrast on some grayscale colors displayed on Easy View
	- Added Toning Colors [com.leocarbonate.cu.models.ToneColor.java]

	- Sliders in Color Fading [models.ScrollColor.java] have ticks for accurate values


0.b2.2.0 (8.6.13)
	- Fixed minor color displaying flaw

0.b2.1.0 (7.21.13)
	- Windows flaw fixed 
	- Temporarily disabled changing look and feel

0.b2.0.0 (7.20.13)
	- Ready for Beta teasing
	- 한국 정보올림피아드 공모전 출전
	- Built Color Uility.app for Mac OS X
	- removed com.mkyong.core.OSValidator.java and added custom OSValidator code to
		com.leocarbonate.cu.utilites.OSValidator.java
	- created com.leocarbonate.cu.models for com.leocarbonate.cu.ColorUtility.ccc's AbstractColorChooserPanels
	- com.leocarbonate.cu.utilities.Functions.java for miscellaneous functions

	- GUI structure is now JTabbedPane
	- About pane inspired by Color Mixer [https://github.com/leocarbon/cm]
	- Settings pane - will be updated


Alpha
--------------
0.a1.1.1 (7.15.13)
	- Java Example added as 'components.FileChooserDemo2Project'
	- mkyong.com OSValidator added
	- ActionHandler.java
	- DigitalEyedropper extends SwingWorker
	- Added Look&Feel Editing
	- EasyView inspired by ColorMixer [https://github.com/leocarbon/cm]
	- Average Color [http://stackoverflow.com/a/12408627/2580875]

0.a1.1.0
	- Basic Color Chooser with JColorChooser
	- Borders for JPanels
	- DigitalEyedropper from Digital.Eyedropper
		- Not working yet