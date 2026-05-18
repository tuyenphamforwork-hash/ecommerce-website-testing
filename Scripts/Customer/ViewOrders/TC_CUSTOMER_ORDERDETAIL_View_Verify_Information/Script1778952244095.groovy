import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

def customer = [('firstName') : 'Customer', ('lastName') : 'Test', ('house') : '03', ('street') : 'Quang Trung', ('city') : 'Da Nang'
    , ('postcode') : '55000', ('country') : 'Vietnam', ('phone') : '0912345678', ('email') : 'customer2@gmail.com']

String password = 'Customer@123456'

WebUI.openBrowser('')

WebUI.maximizeWindow()

try {
    WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

    WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), customer.email)

    WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), password)

    WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

    WebUI.waitForElementVisible(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 15)

    WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

    WebUI.delay(2)

    boolean emptyLabelVisible = WebUI.verifyElementVisible(findTestObject('CUSTOMER/cart/lbl_empty_cart_message'), FailureHandling.OPTIONAL)

    boolean isEmptyCart = false

    if (emptyLabelVisible) {
        String emptyMessage = WebUI.getText(findTestObject('CUSTOMER/cart/lbl_empty_cart_message')).trim()

        println(('Cart message = [' + emptyMessage) + ']')

        if (emptyMessage.equalsIgnoreCase('No item available in cart')) {
            isEmptyCart = true
        }
    }
    
    if (isEmptyCart) {
        println('Cart empty -> add product automatically')

        WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

        WebUI.waitForElementClickable(findTestObject('CUSTOMER/product_detail/productname_jacket'), 10)

        WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

        WebUI.waitForElementClickable(findTestObject('CUSTOMER/product_detail/btn_AddToCart'), 10)

        WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

        println('Product added successfully')

        WebUI.waitForElementClickable(findTestObject('CUSTOMER/HomePage/icon_cart'), 10)

        WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

        WebUI.waitForElementVisible(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'), 10)
    } else {
        println('Cart already has products')
    }
    
    WebUI.click(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'))

    WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'), customer.firstName)

    WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'), customer.lastName)

    WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'), customer.house)

    WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_street'), customer.street)

    WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'), customer.city)

    WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'), customer.postcode)

    WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'), customer.country)

    WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'), customer.phone)

    WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'), customer.email)

    WebUI.click(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'))

    WebUI.waitForElementVisible(findTestObject('CUSTOMER/Payment_Stripe/btn_Pay'), 20)

    WebUI.click(findTestObject('CUSTOMER/Payment_Stripe/exchange_dolar_total_price'))

    String stripeTotalPrice = WebUI.getText(findTestObject('CUSTOMER/Payment_Stripe/exchange_dolar_total_price'))

    assert stripeTotalPrice.contains('$')

    String stripeItem1 = WebUI.getText(findTestObject('CUSTOMER/Payment_Stripe/name_item1')).trim()

    int stripeQuantity = WebUI.findWebElements(findTestObject('CUSTOMER/Payment_Stripe/list_items'), 10).size()

    WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardNumber'), '4242424242424242')

    WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardExpiry'), '1230')

    WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardCvc'), '123')

    WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_Cardholder name_billingName'), 'Customer Test')

    WebUI.selectOptionByLabel(findTestObject('CUSTOMER/Payment_Stripe/select_country'), customer.country, false)

    WebUI.click(findTestObject('CUSTOMER/Payment_Stripe/btn_Pay'))

    WebUI.waitForElementVisible(findTestObject('CUSTOMER/Payment_Stripe/txt_payment_success'), 20)

    WebUI.waitForElementClickable(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 15)

    WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

    WebUI.waitForElementVisible(findTestObject('CUSTOMER/Order_History/btn_ViewDetails'), 15)

    String historyTotalPrice = WebUI.getText(findTestObject('CUSTOMER/Order_History/txtview_totalPrice'))

    String historyStatus = WebUI.getText(findTestObject('CUSTOMER/Order_History/txtview_status_Payment'))

    String historyDateTime = WebUI.getText(findTestObject('CUSTOMER/Order_History/txtview_date_time'))

    WebUI.click(findTestObject('CUSTOMER/Order_History/btn_ViewDetails'))

    WebUI.waitForElementVisible(findTestObject('CUSTOMER/OrderDetails/txtview_TotalPrice'), 15)

    String detailTotalPrice = WebUI.getText(findTestObject('CUSTOMER/OrderDetails/txtview_TotalPrice'))

    String detailStatus = WebUI.getText(findTestObject('CUSTOMER/OrderDetails/txtview_StatusPayment'))

    String detailAddress = WebUI.getText(findTestObject('CUSTOMER/OrderDetails/txtview_userAddress'))

    String detailPhone = WebUI.getText(findTestObject('CUSTOMER/OrderDetails/txtview_userPhone'))

    String detailEmail = WebUI.getText(findTestObject('CUSTOMER/OrderDetails/txtview_userEmail'))

    String detailDateTime = WebUI.getText(findTestObject('CUSTOMER/OrderDetails/txtview_date_time_order'))

    String detailItems = WebUI.getText(findTestObject('CUSTOMER/OrderDetails/txtview_itemsInformation'))

    assert normalizePrice(historyTotalPrice) == normalizePrice(removeLabel(detailTotalPrice, 'Total:'))

    assert normalizePrice(stripeTotalPrice) == normalizePrice(removeLabel(detailTotalPrice, 'Total:'))

    assert normalizeText(historyStatus).equalsIgnoreCase(removeLabel(detailStatus, 'Status:'))

    assert normalizeText(historyDateTime) == normalizeText(removeLabel(detailDateTime, 'Date:'))

    assert removeLabel(detailPhone, 'Phone:') == customer.phone

    assert removeLabel(detailEmail, 'Email:').equalsIgnoreCase(customer.email)

    assert detailAddress.contains("$customer.firstName $customer.lastName")

    assert detailAddress.contains(customer.house)

    assert detailAddress.contains(customer.street)

    assert detailAddress.contains(customer.city)

    assert detailAddress.contains(customer.postcode)

    assert detailAddress.contains(customer.country)

    assert detailItems.length() > 0

    assert detailItems.contains('Qty')

    assert normalizeText(detailItems).toLowerCase().contains(normalizeText(stripeItem1).toLowerCase())

    assert stripeQuantity >= 1

    WebUI.click(findTestObject('CUSTOMER/OrderDetails/btn_close_OrderDetails'))
}
finally { 
    WebUI.closeBrowser()
}

String normalizePrice(String value) {
    return value.replaceAll('[^0-9.]', '').trim()
}

String normalizeText(String value) {
    return value.trim().replaceAll('\\s+', ' ')
}

String removeLabel(String value, String label) {
    return normalizeText(value).replaceFirst('(?i)' + java.util.regex.Pattern.quote(label), '').trim()
}

