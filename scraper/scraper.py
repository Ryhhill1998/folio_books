import requests
from bs4 import BeautifulSoup
import uuid, json, random

BASE_URL = "https://www.whsmith.co.uk/books/graphic-novels-comic-books-and-manga/bks00884/?p="

HEADERS = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36 Edg/117.0.2045.60"}

json_data = None

with open("../../books.json", "r") as file:
    json_data = json.load(file)

stars_array = [1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5]

def get_books(url, category):
    html = requests.get(url, headers=HEADERS).text
    soup = BeautifulSoup(html, "html.parser")

    book_previews = soup.find_all("div", class_="product-tile")

    for preview in book_previews:
        title = preview.find("h4", class_="ellip").text
        image_src = "https://www.whsmith.co.uk" + preview.find("meta")["content"]
        author_container = preview.find("div", class_="tile-attribute")
        author = None
        if author_container:
            author = author_container.find("p").text
        price = random.randint(4, 19) + random.choice([0.49, 0.99])
        stars = random.choice(stars_array)

        json_data["books"][category].append({
            "id": str(uuid.uuid4()),
            "title": title,
            "author": author,
            "image_src": image_src,
            "price": price,
            "stars": stars
        })

for page_number in range(1, 11):
    get_books(f"{BASE_URL}{page_number}", "graphic_novels")

with open("../../books.json", "w+") as file:
    file.write(json.dumps(json_data))
