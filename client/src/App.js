import { useEffect, useState } from "react";
import "./App.scss";
import Nav from "./components/Nav/Nav";
import Carousel from "./components/Carousel/Carousel";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

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

          <Carousel books={books["romance"]} />
        </div>

        <div className="genre-container container">
          <h2>Crime, Mystery and Thriller</h2>

          <Carousel books={books["crime_mystery_thriller"]} />
        </div>

        <div className="genre-container container">
          <h2>Science Fiction</h2>

          <Carousel books={books["science_fiction"]} />
        </div>

        <div className="genre-container container">
          <h2>Graphic Novels</h2>

          <Carousel books={books["graphic_novels"]} />
        </div>
      </main>
    </div>
  );
};

export default App;
