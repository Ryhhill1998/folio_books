import requests
from bs4 import BeautifulSoup
import uuid, json, random

categories = [
    "fiction/myths-legends", 
    "crime-thrillers-mystery", 
    "science-fiction-fantasy-horror/fantasy", 
    "science-fiction-fantasy-horror/horror-ghost-stories", 
    "science-fiction-fantasy-horror/science-fiction", 
    "fiction/historical-fiction"
]

BASE_URL = "https://www.waterstones.com/category"

USERS_AGENTS = [
    "Mozilla/5.0 (Linux; Android 9; S4-KC) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.101 Mobile Safari/537.36",
    "Mozilla/5.0 (Linux; Android 8.1.0; Redmi Go Build/OPM1.171019.026) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Mobile Safari/537.36 YaApp_Android/10.91 YaSearchBrowser/10.91",
    "Mozilla/5.0 (Linux; Android 7.0; BTV-DL09 Build/HUAWEIBEETHOVEN-DL09) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.93 Mobile Safari/537.36 YaApp_Android/10.91 YaSearchBrowser/10.91",
    "Mozilla/5.0 (iPhone; CPU iPhone OS 14_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.2 Mobile/15E148 DuckDuckGo/7 Safari/605.1.15",
    "Mozilla/5.0 (Linux; Android 10; SM-G965U1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.185 Mobile Safari/537.36",
    "Mozilla/5.0 (Linux; Android 10; ART-L29; HMSCore 5.0.0.304) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 HuaweiBrowser/10.1.2.322 Mobile Safari/537.36",
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.72 Safari/537.36 Edg/89.0.774.45/7vdq8CtK-01",
    "Mozilla/5.0 (Linux; Android 9; KFMAWI) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.116 Safari/537.36 EdgA/45.11.2.5118"
]

json_data = {"books": {
    "myths-legends": [],
    "crime-thrillers-mystery": [],
    "fantasy": [],
    "horror-ghost-stories": [],
    "science-fiction": [],
    "historical-fiction": []
}}

def get_books(url, category):
    random_user_agent = random.choice(USERS_AGENTS)
    html = requests.get(url, headers={"User-Agent": random_user_agent}).text
    soup = BeautifulSoup(html, "html.parser")
    print(soup.prettify())

    book_previews = soup.find_all("div", class_="book-preview")

    for preview in book_previews:
        title = preview.find("a", class_="title").text
        img_src = preview.find("img")["data-src"]
        author = preview.find("a", class_="text-gold").text
        price_container = preview.find("div", class_="book-price")
        price = preview.find_all("span", class_="price")[-1].text.strip().split("\u00a3")[-1]
        star_rating_container = preview.find("div", class_="star-rating")
        stars = 0

        if star_rating_container:
            star_spans = star_rating_container.find_all("span")

            for star in star_spans:
                class_name = star.attrs['class']
                star_type = class_name[-1]

                if star_type == "full":
                    stars += 1
                elif star_type == "half":
                    stars += 0.5
        else:
            stars = None

        categoryName = category.split("/")[-1]

        json_data["data"]["books"][categoryName].append({
            "id": str(uuid.uuid4()),
            "title": title,
            "author": author,
            "image_src": img_src,
            "price": price,
            "stars": stars
        })

for category in categories:
    for page_number in range(1, 11):
        get_books(f"{BASE_URL}/{category}/page/{page_number}", category)
        break

with open("../../books.json", "w+") as file:
    file.write(json.dumps(json_data))
