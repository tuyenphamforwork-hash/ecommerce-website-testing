import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.maximizeWindow()

def protectedUrls = [
	'profile.php',
	'order_details.php?id=1',
	'cart.php',
	'checkout.php'
]

for (def url : protectedUrls) {

	WebUI.navigateToUrl(GlobalVariable.baseUrl + '/' + url)
	WebUI.comment("Testing URL: " + url)

	boolean isPass = false

	switch (url) {

		case 'profile.php':
			isPass = WebUI.getUrl().contains('UnathorizedUser')
			break

		case 'order_details.php?id=1':
			isPass = WebUI.verifyTextPresent(
				'Unauthorized',
				false,
				FailureHandling.OPTIONAL
			)
			break

		case 'cart.php':
			isPass = WebUI.verifyTextPresent(
				'Please login to view cart',
				false,
				FailureHandling.OPTIONAL
			)
			break

		case 'checkout.php':
			isPass = WebUI.getUrl().contains('login_required=1')
			break
	}

	WebUI.comment("Result → PASS: " + isPass)

	if (!isPass) {
		WebUI.takeScreenshot()
		WebUI.comment("BUG: Unauthorized access handling failed → " + url)
	}

	WebUI.verifyEqual(isPass, true, FailureHandling.CONTINUE_ON_FAILURE)
}

WebUI.closeBrowser()