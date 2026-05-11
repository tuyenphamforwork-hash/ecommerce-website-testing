import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling

WebUI.openBrowser('')
WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

// Search lower case
WebUI.setText(findTestObject('Guest/Homepage/txtbox_search'), 'men shirt')
WebUI.sendKeys(findTestObject('Guest/Homepage/txtbox_search'), Keys.chord(Keys.ENTER))
boolean lowerResult = WebUI.verifyElementPresent(
	findTestObject('Guest/Page_HCA E-Commerce/div_Search_MultipleKeywords'),
	10,
	FailureHandling.OPTIONAL
)

// Search upper case
WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.setText(findTestObject('Guest/Homepage/txtbox_search'), 'Men Shirt')
WebUI.sendKeys(findTestObject('Guest/Homepage/txtbox_search'), Keys.chord(Keys.ENTER))
boolean upperResult = WebUI.verifyElementPresent(
	findTestObject('Guest/Page_HCA E-Commerce/div_Search_MultipleKeywords'),
	10,
	FailureHandling.OPTIONAL
)

boolean isValid = (lowerResult == upperResult)

if (!isValid) {
	WebUI.takeScreenshot()
	WebUI.comment("❌ BUG: Search bị phân biệt hoa thường")
}

WebUI.verifyEqual(isValid, true, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.closeBrowser()