import time
import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from datetime import datetime, timedelta


@pytest.fixture(scope="module")
def driver():
    options = webdriver.ChromeOptions()
    options.add_argument("--start-maximized")
    driver = webdriver.Chrome(service=Service(), options=options)
    yield driver
    driver.quit()


def test_search_flights_round_trip(driver):
    driver.get("https://www.makemytrip.com/")

    wait = WebDriverWait(driver, 15)

    # Close any pop-up or login dialog (if appears)
  
    # popup_wait = WebDriverWait(driver, 5)

    try:
        # Wait for the Personal Account popup
        personal_account_popup = wait.until(
            EC.visibility_of_element_located((By.XPATH, "//*[contains(text(),'Personal Account')]"))
        )

        if personal_account_popup.is_displayed():
            print("🔔 Personal Account popup detected. Attempting to close it...")

            # Try closing the popup by clicking the close button or any dismissible area
            close_button = driver.find_element(By.XPATH, "//*[@id='SW']/div[1]/div[2]/div[2]/div/section/span")
            close_button.click()
            time.sleep(1)

    except:
        print("✅ No Personal Account popup appeared — continuing with test.")


    # Click Flights tab
    flights_tab = wait.until(EC.element_to_be_clickable((By.XPATH, "//span[text()='Flights']")))
    flights_tab.click()

    # Select Round Trip option
    round_trip = wait.until(EC.element_to_be_clickable((By.XPATH, "//li[@data-cy='roundTrip']")))
    round_trip.click()

    # Enter FROM location
    from_input = wait.until(EC.element_to_be_clickable((By.ID, "fromCity")))
    from_input.click()
    from_input_field = wait.until(EC.element_to_be_clickable((By.XPATH, "//input[@placeholder='From']")))
    from_input_field.clear()
    from_input_field.send_keys("HYD")
    time.sleep(2)  # wait for suggestions
    from_option=wait.until(EC.element_to_be_clickable((By.XPATH, "//*[@id='react-autowhatever-1-section-0-item-0']/div")))
    from_option.click()
    # from_input_field.send_keys(Keys.ENTER)

    # Enter TO location
    to_input = wait.until(EC.element_to_be_clickable((By.ID, "toCity")))
    to_input.click()
    to_input_field = wait.until(EC.element_to_be_clickable((By.XPATH, "//input[@placeholder='To']")))
    to_input_field.clear()
    to_input_field.send_keys("MAA")
    time.sleep(2)  # wait for suggestions
    to_input_option=wait.until(EC.element_to_be_clickable((By.XPATH, '//*[@id="react-autowhatever-1-section-0-item-1"]/div/div/div/div[1]/div/p[text()="Chennai, India"]')))
    to_input_option.click()
    # to_input_field.send_keys(Keys.ENTER)

    # Select Departure Date (tomorrow)
    dep_date = wait.until(EC.element_to_be_clickable(
    (By.XPATH, '//*[@id="top-banner"]/div[2]/div/div/div/div/div[2]/div[1]/div[3]/label/span')
))
    dep_date.click()
    # Note: The date picker on the site uses aria-label with format like "Friday, 23 June 2023"
    dep_date_element = wait.until(
        EC.element_to_be_clickable((By.XPATH, "//div[@aria-label='Wed Jun 25 2025']")))
    dep_date_element.click()

    # Select Return Date (day after tomorrow)
    ret_date_element = wait.until(
        EC.element_to_be_clickable((By.XPATH, "//div[@aria-label='Fri Jun 27 2025']")))
    ret_date_element.click()

    # Click Search button
    search_button = wait.until(EC.element_to_be_clickable((By.XPATH, "//a[text()='Search']")))
    search_button.click()

    # Verify Search results page loaded (example: wait for results container)
    body_text = driver.find_element(By.TAG_NAME, "body").text
    assert "200-OK" in body_text, "❌ '200-OK' not found in body text"

