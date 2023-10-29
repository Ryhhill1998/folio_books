import { useEffect } from "react";
import { useSearchStore } from "../../globalStores/search";
import Carousel from "../Carousel/Carousel";
import BookCard from "../BookCard/BookCard";
import { useBooksStore } from "../../globalStores/books";

const HomePage = () => {
  const books = useBooksStore((store) => store.books);
  const getBooks = useBooksStore((store) => store.getBooks);

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
    getBooks();
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

            <Carousel books={books?.romanceBooks} />
          </div>

          <div className="genre-container container">
            <h2 className="section-heading">Crime, Mystery and Thriller</h2>

            <Carousel books={books?.crimeMysteryThrillerBooks} />
          </div>

          <div className="genre-container container">
            <h2 className="section-heading">Science Fiction</h2>

            <Carousel books={books?.scienceFictionBooks} />
          </div>

          <div className="genre-container container">
            <h2 className="section-heading">Graphic Novels</h2>

            <Carousel books={books?.graphicNovelBooks} />
          </div>
        </>
      )}
    </div>
  );
};

export default HomePage;
