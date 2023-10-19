import json

# Opening JSON file
with open('books.json') as file1:
    data = json.load(file1)
    
    with open('SQLQuery.txt', 'w') as file2:
        file2.write('INSERT INTO book(id, title, author, image_src, price, stars) VALUES')
        for e in range(len(data['books'])): # e is the index for each book
            i=data["books"][e]
            file2.write(f"\n('{i['id']}', '{i['title']}', '{i['author']}', '{i['image_src']}',
                        {i['price']}, {i['stars'] if i['stars'] else 'NULL'})
                        {',' if e<len(data['books'])-1 else ';'}")


# Opening JSON file
with open('books.json') as file1:
    data = json.load(file1)

    with open('SQLQuery.txt', 'w') as file2:
        for genre in data['books']: # dictionary of books by genre
            file2.write(f"INSERT INTO book_{genre}(id, title, author, image_src, price, stars) VALUES")
            for e in range(len(genre)): # e is the index for each book
                i=genre[e]
                file2.write(f"\n('{i['id']}', '{i['title']}', '{i['author']}', '{i['image_src']}',
                            {i['price']}, {i['stars'] if i['stars'] else 'NULL'})
                            {',' if e<len(data['books'])-1 else ';'}")
        file2.write("\n\n")