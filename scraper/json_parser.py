import json
            
with open('books2.json') as file1:
    data = json.load(file1)

    with open('../server/src/main/resources/data.sql', 'w') as file2:
        file2.write("use ecommerce_db;\n\n")
        file2.write('INSERT INTO book(id, title, author, genre, image_src, price, stars) VALUES')
        for e in range(len(data['books'])):
            i=data["books"][e]
            file2.write(f'\n("{i["id"]}", "{i["title"]}", "{i["author"]}", "{i["genre"]}", "{i["image_src"]}", {i["price"]}, {i["stars"] if i["stars"] else "NULL"}){"," if e<len(data["books"])-1 else ";"}')  