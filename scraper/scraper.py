import requests
from bs4 import BeautifulSoup
import uuid, json

BASE_URL = "https://www.waterstones.com/books/bestsellers"
HEADERS = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36 Edg/117.0.2045.60"}

html = requests.get(BASE_URL, headers=HEADERS).text
soup = BeautifulSoup(html, "html.parser")

book_previews = soup.find_all("div", class_="book-preview")

json_data = {"data": {"books": []}}

for preview in book_previews:
    title = preview.find("a", class_="title").text
    img_src = preview.find("img")["data-src"]
    author = preview.find("a", class_="text-gold").text
    price_container = preview.find("div", class_="book-price")
    price = float(preview.find_all("span", class_="price")[-1].text.strip().split("\u00a3")[-1])
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

    json_data["data"]["books"].append({
        "id": str(uuid.uuid4()),
        "title": title,
        "author": author,
        "image_src": img_src,
        "price": price,
        "stars": stars
    })

with open("../../books.json", "w+") as file:
    file.write(json.dumps(json_data))
