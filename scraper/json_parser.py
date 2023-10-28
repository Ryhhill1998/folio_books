import json
import random
            
with open('books2.json') as file1:
    data = json.load(file1)

    with open('../server/src/main/resources/data_books.sql', 'w') as file2:
        file2.write("use ecommerceDb;\n\n")
        file2.write('INSERT INTO book(id, title, author, genre, imageSrc, price, stars, stockQuantity) VALUES')
        
        for e in range(len(data['books'])):
            i=data["books"][e]
            random_stock_quantity = random.randint(1,5)
            file2.write(f'''\n(
                        "{i["id"]}", 
                        "{i["title"]}", 
                        "{i["author"]}", 
                        "{i["genre"]}", 
                        "{i["image_src"]}", 
                        {i["price"]}, 
                        {i["stars"] if i["stars"] else "NULL"},
                        {random_stock_quantity}
                        ){"," if e<len(data["books"])-1 else ";"}''')  
