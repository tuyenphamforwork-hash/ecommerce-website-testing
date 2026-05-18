import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

String keyword = 'men shirt'

WebUI.setText(findTestObject('GUEST/Homepage/txtbox_search'), keyword)
WebUI.sendKeys(findTestObject('GUEST/Homepage/txtbox_search'), Keys.chord(Keys.ENTER))

boolean hasResultBefore = WebUI.verifyElementPresent(
	findTestObject('GUEST/Page_HCA E-Commerce/div_Search_MultipleKeywords'),
	10,
	FailureHandling.OPTIONAL
)

WebUI.verifyEqual(hasResultBefore, true)

String urlBefore = WebUI.getUrl()

WebUI.refresh()
WebUI.delay(2)

String urlAfter = WebUI.getUrl()

boolean hasResultAfter = WebUI.verifyElementPresent(
	findTestObject('GUEST/Page_HCA E-Commerce/div_Search_MultipleKeywords'),
	10,
	FailureHandling.OPTIONAL
)

boolean isValid =
	(urlAfter.contains('men') && urlAfter.contains('shirt') && hasResultAfter) ||  
	(!urlAfter.contains('men') && !urlAfter.contains('shirt'))                     

WebUI.comment("Before URL: " + urlBefore)
WebUI.comment("After URL: " + urlAfter)
WebUI.comment("Result after refresh: " + hasResultAfter)

if (!isValid) {
	WebUI.takeScreenshot()
	WebUI.comment("BUG: Refresh after search incorrect")
}

WebUI.verifyEqual(isValid, true, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.closeBrowser()