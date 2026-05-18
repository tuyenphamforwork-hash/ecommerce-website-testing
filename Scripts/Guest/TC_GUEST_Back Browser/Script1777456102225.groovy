	import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
	import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
	import com.kms.katalon.core.model.FailureHandling as FailureHandling
	import internal.GlobalVariable as GlobalVariable
	import org.openqa.selenium.Keys as Keys
	
	WebUI.openBrowser('')
	
	WebUI.maximizeWindow()
	
	WebUI.navigateToUrl(GlobalVariable.baseUrl)
	
	WebUI.setText(findTestObject('GUEST/Homepage/txtbox_search'), 'shirt')
	
	WebUI.sendKeys(findTestObject('GUEST/Homepage/txtbox_search'), Keys.chord(Keys.ENTER))
	
	String searchUrl = WebUI.getUrl()
	
	WebUI.click(findTestObject('GUEST/Page_HCA E-Commerce/productName_Tshirt'))
	
	WebUI.verifyElementPresent(findTestObject('GUEST/Page_HCA E-Commerce/productTitle_Tshirt'), 10)
	
	WebUI.back()
	
	WebUI.waitForPageLoad(10)
	
	String backUrl = WebUI.getUrl()
	
	WebUI.comment('Search URL: ' + searchUrl)
	
	WebUI.comment('Back URL: ' + backUrl)
	
	boolean isCorrectUrl = backUrl.contains('search') && backUrl.toLowerCase().contains('shirt')
	
	boolean isProductListVisible = WebUI.verifyElementPresent(
		findTestObject('GUEST/Page_HCA E-Commerce/div_Search_Result'),
		5,
		FailureHandling.OPTIONAL
	)
	
	boolean isResubmitPage = WebUI.verifyTextPresent(
		'Confirm Form Resubmission',
		false,
		FailureHandling.OPTIONAL
	)
	
	WebUI.comment(
		"Check → corect URL : " + isCorrectUrl +
		" | ProductList: " + isProductListVisible +
		" | Resubmit: " + isResubmitPage
	)
	
	if (isResubmitPage) {
		WebUI.takeScreenshot()
		WebUI.comment('BUG: Back error Confirm Form Resubmission (ERR_CACHE_MISS)')
	}
	
	boolean isValid = isCorrectUrl && isProductListVisible && !isResubmitPage
	
	WebUI.verifyEqual(isValid, true)
	
	WebUI.closeBrowser()
	