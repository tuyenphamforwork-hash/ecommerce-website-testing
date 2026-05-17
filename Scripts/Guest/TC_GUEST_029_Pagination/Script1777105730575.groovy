import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling

WebUI.openBrowser('')
WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

// Search để có nhiều page
WebUI.setText(findTestObject('GUEST/Homepage/txtbox_search'), 'shirt')
WebUI.sendKeys(findTestObject('GUEST/Homepage/txtbox_search'), Keys.chord(Keys.ENTER))

String page1 = WebUI.getUrl()

// Click Next
boolean hasNext = WebUI.verifyElementPresent(
	findTestObject('GUEST/Page_HCA E-Commerce/pagination_next'),
	5,
	FailureHandling.OPTIONAL
)

if (hasNext) {

	WebUI.click(findTestObject('GUEST/Page_HCA E-Commerce/pagination_next'))
	WebUI.delay(2)

	String page2 = WebUI.getUrl()

	boolean nextWorked = !page1.equals(page2)

	// Click Previous
	boolean hasPrev = WebUI.verifyElementPresent(
		findTestObject('GUEST/Page_HCA E-Commerce/pagination_prev'),
		5,
		FailureHandling.OPTIONAL
	)

	if (hasPrev) {
		WebUI.click(findTestObject('GUEST/Page_HCA E-Commerce/pagination_prev'))
		WebUI.delay(2)
	}

	if (!nextWorked) {
		WebUI.takeScreenshot()
		WebUI.comment("❌ BUG: Pagination Next không hoạt động")
	}

	WebUI.verifyEqual(nextWorked, true, FailureHandling.CONTINUE_ON_FAILURE)

} else {
	WebUI.comment("⚠️ Không có pagination")
}

WebUI.closeBrowser()