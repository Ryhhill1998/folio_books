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
        <div className="genre-container container">
          <h2>Romance</h2>

          <div className="books-container">
            {books["romance"]?.slice(0, 10)?.map((book) => (
              <BookCard key={book.id} {...book} />
            ))}
          </div>
        </div>

        <div className="genre-container container">
          <h2>Crime, Mystery and Thriller</h2>

          <div className="books-container">
            {books["crime_mystery_thriller"]?.slice(0, 10)?.map((book) => (
              <BookCard key={book.id} {...book} />
            ))}
          </div>
        </div>

        <div className="genre-container container">
          <h2>Science Fiction</h2>

          <div className="books-container">
            {books["science_fiction"]?.slice(0, 10)?.map((book) => (
              <BookCard key={book.id} {...book} />
            ))}
          </div>
        </div>

        <div className="genre-container container">
          <h2>Graphic Novels</h2>

          <div className="books-container">
            {books["graphic_novels"]?.slice(0, 10)?.map((book) => (
              <BookCard key={book.id} {...book} />
            ))}
          </div>
        </div>
      </main>
    </div>
  );
};

export default App;
