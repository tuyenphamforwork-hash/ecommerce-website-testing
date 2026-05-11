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
			// Redirect về homepage có param UnathorizedUser
			isPass = WebUI.getUrl().contains('UnathorizedUser')
			break

		case 'order_details.php?id=1':
			// Hiển thị text Unauthorized
			isPass = WebUI.verifyTextPresent(
				'Unauthorized',
				false,
				FailureHandling.OPTIONAL
			)
			break

		case 'cart.php':
			// Hiển thị message yêu cầu login
			isPass = WebUI.verifyTextPresent(
				'Please login to view cart',
				false,
				FailureHandling.OPTIONAL
			)
			break

		case 'checkout.php':
			// Redirect về login page
			isPass = WebUI.getUrl().contains('login_required=1')
			break
	}

	WebUI.comment("Result → PASS: " + isPass)

	// Nếu fail → chụp màn hình + log bug
	if (!isPass) {
		WebUI.takeScreenshot()
		WebUI.comment("BUG: Unauthorized access handling failed → " + url)
	}

	// Continue test dù fail
	WebUI.verifyEqual(isPass, true, FailureHandling.CONTINUE_ON_FAILURE)
}

WebUI.closeBrowser()