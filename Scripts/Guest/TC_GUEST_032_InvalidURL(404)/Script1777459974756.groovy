import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling

WebUI.openBrowser('')
WebUI.maximizeWindow()

// URL sai
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/abcxyz.php')

boolean is404 = WebUI.verifyTextPresent(
	'Not Found',
	false,
	FailureHandling.OPTIONAL
)

boolean isRedirectHome = WebUI.getUrl().contains('index')

boolean isValid = is404 || isRedirectHome

if (!isValid) {
	WebUI.takeScreenshot()
	WebUI.comment("❌ BUG: Invalid URL không xử lý đúng")
}

WebUI.verifyEqual(isValid, true, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.closeBrowser()