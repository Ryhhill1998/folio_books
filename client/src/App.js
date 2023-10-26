import { useEffect, useState } from "react";
import "./App.scss";
import Nav from "./components/Nav/Nav";
import Carousel from "./components/Carousel/Carousel";
import { useSearchStore } from "./globalStores/search";
import BookCard from "./components/BookCard/BookCard";
import LoginScreen from "./components/Auth/LoginScreen/LoginScreen";
import { useProfileStore } from "./globalStores/profile";
import RegisterScreen from "./components/Auth/RegisterScreen/RegisterScreen";
import Auth from "./components/Auth/Auth";

const App = () => {
  // const [books, setBooks] = useState([]);
  // const [romanceBooks, setRomanceBooks] = useState([]);
  // const [crimeMysteryThrillerBooks, setCrimeMysteryThrillerBooks] = useState(
  //   []
  // );
  // const [scienceFictionBooks, setScienceFictionBooks] = useState([]);
  // const [graphicNovelBooks, setGraphicNovelBooks] = useState([]);

  // const searchIsActive = useSearchStore((store) => store.active);
  // const searchQuery = useSearchStore((store) => store.query);

  // const stringContainsQueryString = (string, queryString) =>
  //   string?.toLowerCase()?.includes(queryString.toLowerCase());

  // const renderFilteredBooks = () => {
  //   const filteredBooks = books.filter((book) => {
  //     const { title, author, genre } = book;

  //     return (
  //       !searchQuery ||
  //       stringContainsQueryString(title, searchQuery) ||
  //       stringContainsQueryString(author, searchQuery) ||
  //       stringContainsQueryString(genre, searchQuery)
  //     );
  //   });

  //   return filteredBooks?.map((book) => <BookCard key={book.id} {...book} />);
  // };

  // useEffect(() => {
  //   fetch("http://localhost:8000/books")
  //     .then((response) => response.json())
  //     .then((books) => {
  //       setBooks(books);

  //       setRomanceBooks(books.filter(({ genre }) => genre === "romance"));
  //       setCrimeMysteryThrillerBooks(
  //         books.filter(({ genre }) => genre === "crime_mystery_thriller")
  //       );
  //       setScienceFictionBooks(
  //         books.filter(({ genre }) => genre === "science_fiction")
  //       );
  //       setGraphicNovelBooks(
  //         books.filter(({ genre }) => genre === "graphic_novels")
  //       );
  //     });
  // }, []);

  const displayName = useProfileStore((store) => store.displayName);
  const signedIn = useProfileStore((store) => store.signedIn);
  const signOut = useProfileStore((store) => store.signOutUser);

  return (
    <div className="App">
      {/* <p>{signedIn ? "Signed in" : "Signed out"}</p>
      {displayName && <p>As {displayName}</p>}
      <button onClick={signOut}>Sign out</button> */}

      <header>
        <Nav />
      </header>

      <main>
        <Auth />
        {/* {searchIsActive && (
          <div className="container">
            <h2>Search Results</h2>

            <div className="search-results">{renderFilteredBooks()}</div>
          </div>
        )}

        {!searchIsActive && (
          <>
            <div className="genre-container container">
              <h2>Romance</h2>

              <Carousel books={romanceBooks} />
            </div>

            <div className="genre-container container">
              <h2>Crime, Mystery and Thriller</h2>

              <Carousel books={crimeMysteryThrillerBooks} />
            </div>

            <div className="genre-container container">
              <h2>Science Fiction</h2>

              <Carousel books={scienceFictionBooks} />
            </div>

            <div className="genre-container container">
              <h2>Graphic Novels</h2>

              <Carousel books={graphicNovelBooks} />
            </div>
          </>
        )} */}
      </main>
    </div>
  );
};

export default App;
