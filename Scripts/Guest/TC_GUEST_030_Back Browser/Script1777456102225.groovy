	import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
	import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
	import com.kms.katalon.core.model.FailureHandling as FailureHandling
	import internal.GlobalVariable as GlobalVariable
	import org.openqa.selenium.Keys as Keys
	
	WebUI.openBrowser('')
	
	WebUI.maximizeWindow()
	
	// Homepage
	WebUI.navigateToUrl(GlobalVariable.baseUrl)
	
	// Search
	WebUI.setText(findTestObject('Guest/Homepage/txtbox_search'), 'shirt')
	
	WebUI.sendKeys(findTestObject('Guest/Homepage/txtbox_search'), Keys.chord(Keys.ENTER))
	
	// Save URL
	String searchUrl = WebUI.getUrl()
	
	// Click product
	WebUI.click(findTestObject('Guest/Page_HCA E-Commerce/productName_Tshirt'))
	
	WebUI.verifyElementPresent(findTestObject('Guest/Page_HCA E-Commerce/productTitle_Tshirt'), 10)
	
	// Back
	WebUI.back()
	
	WebUI.waitForPageLoad(10)
	
	String backUrl = WebUI.getUrl()
	
	WebUI.comment('Search URL: ' + searchUrl)
	
	WebUI.comment('Back URL: ' + backUrl)
	
	// ===== VERIFY EXPECTED: BACK PHẢI VỀ SEARCH =====
	
	// URL phải chứa search + keyword
	boolean isCorrectUrl = backUrl.contains('search') && backUrl.toLowerCase().contains('shirt')
	
	// Phải hiển thị lại danh sách sản phẩm
	boolean isProductListVisible = WebUI.verifyElementPresent(
		findTestObject('Guest/Page_HCA E-Commerce/div_Search_Result'),
		5,
		FailureHandling.OPTIONAL
	)
	
	// Không được xuất hiện lỗi resubmit
	boolean isResubmitPage = WebUI.verifyTextPresent(
		'Confirm Form Resubmission',
		false,
		FailureHandling.OPTIONAL
	)
	
	// ===== LOG =====
	WebUI.comment(
		"Check → URL đúng: " + isCorrectUrl +
		" | ProductList: " + isProductListVisible +
		" | Resubmit: " + isResubmitPage
	)
	
	// ===== BUG HANDLE =====
	if (isResubmitPage) {
		WebUI.takeScreenshot()
		WebUI.comment('❌ BUG: Back bị lỗi Confirm Form Resubmission (ERR_CACHE_MISS)')
	}
	
	// ===== FINAL ASSERT =====
	// PHẢI thỏa cả 3 điều kiện mới PASS
	boolean isValid = isCorrectUrl && isProductListVisible && !isResubmitPage
	
	WebUI.verifyEqual(isValid, true)
	
	// Close
	WebUI.closeBrowser()
	