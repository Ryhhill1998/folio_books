import { useEffect, useState } from "react";
import "./App.scss";
import BookCard from "./components/BookCard/BookCard";
import Nav from "./components/Nav/Nav";

const App = () => {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8000/books")
      .then((response) => response.json())
      .then((books) => setBooks(books));
  }, []);

  return (
    <div className="App">
      <header>
        <Nav />
      </header>

      <main>
        <div className="books-container container">
          {books.map((book) => (
            <BookCard key={book.id} {...book} />
          ))}
        </div>
      </main>
    </div>
  );
};

export default App;
