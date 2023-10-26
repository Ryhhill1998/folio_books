import { useEffect, useState } from "react";
import { useSearchStore } from "../../globalStores/search";
import Carousel from "../Carousel/Carousel";
import BookCard from "../BookCard/BookCard";

const HomePage = () => {
  const [books, setBooks] = useState([]);
  const [romanceBooks, setRomanceBooks] = useState([]);
  const [crimeMysteryThrillerBooks, setCrimeMysteryThrillerBooks] = useState(
    []
  );
  const [scienceFictionBooks, setScienceFictionBooks] = useState([]);
  const [graphicNovelBooks, setGraphicNovelBooks] = useState([]);

  const searchIsActive = useSearchStore((store) => store.active);
  const searchQuery = useSearchStore((store) => store.query);

  const stringContainsQueryString = (string, queryString) =>
    string?.toLowerCase()?.includes(queryString.toLowerCase());

  const renderFilteredBooks = () => {
    const filteredBooks = books?.filter((book) => {
      const { title, author, genre } = book;

      return (
        !searchQuery ||
        stringContainsQueryString(title, searchQuery) ||
        stringContainsQueryString(author, searchQuery) ||
        stringContainsQueryString(genre, searchQuery)
      );
    });

    return filteredBooks?.map((book) => <BookCard key={book.id} {...book} />);
  };

  useEffect(() => {
    fetch("http://localhost:8000/books")
      .then((response) => response.json())
      .then((books) => {
        setBooks(books);

        setRomanceBooks(
          books?.length && books?.filter(({ genre }) => genre === "romance")
        );
        setCrimeMysteryThrillerBooks(
          books?.length &&
            books?.filter(({ genre }) => genre === "crime_mystery_thriller")
        );
        setScienceFictionBooks(
          books?.length &&
            books?.filter(({ genre }) => genre === "science_fiction")
        );
        setGraphicNovelBooks(
          books?.length &&
            books?.filter(({ genre }) => genre === "graphic_novels")
        );
      });
  }, []);

  return (
    <div className="home-page-container">
      {searchIsActive && (
        <div className="container">
          <h2 className="section-heading">Search Results</h2>

          <div className="search-results">{renderFilteredBooks()}</div>
        </div>
      )}

      {!searchIsActive && (
        <>
          <div className="genre-container container">
            <h2 className="section-heading">Romance</h2>

            <Carousel books={romanceBooks} />
          </div>

          <div className="genre-container container">
            <h2 className="section-heading">Crime, Mystery and Thriller</h2>

            <Carousel books={crimeMysteryThrillerBooks} />
          </div>

          <div className="genre-container container">
            <h2 className="section-heading">Science Fiction</h2>

            <Carousel books={scienceFictionBooks} />
          </div>

          <div className="genre-container container">
            <h2 className="section-heading">Graphic Novels</h2>

            <Carousel books={graphicNovelBooks} />
          </div>
        </>
      )}
    </div>
  );
};

export default HomePage;
