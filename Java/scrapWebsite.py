# from selenium import webdriver
# from bs4 import BeautifulSoup
# from urllib.parse import *

# class ScrapWebsite:
#     vistedURL = set()
#     def get_website_content(self, uri, tag_name):
#         try:
#             # Set up a headless Chrome browser (you need to have ChromeDriver installed)
#             options = webdriver.ChromeOptions()
#             options.add_argument('--headless')
#             driver = webdriver.Chrome(options=options)
#             driver.get(uri)
#             page_source = driver.page_source
#             driver.quit()
#             beautified = BeautifulSoup(page_source, 'html.parser')
#             beautified.title
#             elements = beautified.find_all(tag_name)
#             emptyList =[]
#             for t in elements:
#                 href =t.get("href")
#                 if href is not None and href != " ":
#                     emptyList.append(href)

#             for url2 in emptyList:
#                 if url2 not in ScrapWebsite.vistedURL:
#                     ScrapWebsite.vistedURL.add(url2)
#                     absaluteURL = urljoin(uri ,url2)
#                     if "avasoft.com" in absaluteURL:
#                         print(absaluteURL)
#                         sW.get_website_content(absaluteURL, tag_to_extract)

#         except Exception as e:
#             #print( beautified.title.text)
#             print(f"An error occurred: {e}")

# # Example usage
# sW = ScrapWebsite()
# URL = 'https://www.avasoft.com/'
# tag_to_extract = 'a'  # Change this to the desired tag name
# sW.get_website_content(URL, tag_to_extract)

from selenium import webdriver
from bs4 import BeautifulSoup
from urllib.parse import urljoin, urlparse

class ScrapWebsite:
    visitedURL = set()

    def get_website_content(self, uri, tag_name):
        try:
            # Set up a headless Chrome browser (you need to have ChromeDriver installed)
            options = webdriver.ChromeOptions()
            options.add_argument('--headless')
            driver = webdriver.Chrome(options=options)
            driver.get(uri)
            page_source = driver.page_source
            driver.quit()

            beautified = BeautifulSoup(page_source, 'html.parser')
            beautified.title

            elements = beautified.find_all(tag_name)
            urls_to_visit = []

            for t in elements:
                href = t.get("href")
                if href and href.strip():  # Check if href is not None and not an empty string
                    abs_url = urljoin(uri, href)
                    if "avasoft.com" in abs_url and abs_url not in ScrapWebsite.visitedURL:
                        ScrapWebsite.visitedURL.add(abs_url)
                        urls_to_visit.append(abs_url)
                        print(abs_url)

            for url_to_visit in urls_to_visit:
                self.get_website_content(url_to_visit, tag_name)

        except Exception as e:
            print(f"An error occurred: {e}"

# Example usage
sW = ScrapWebsite()
URL = 'https://www.avasoft.com/'
tag_to_extract = 'a'  # Change this to the desired tag name
sW.get_website_content(URL, tag_to_extract)