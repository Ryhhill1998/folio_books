import "./Carousel.scss";

import {
  faCircleChevronLeft,
  faCircleChevronRight,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import BookCard from "../BookCard/BookCard";
import { useRef } from "react";

const Carousel = ({ books }) => {
  const ref = useRef(null);

  const handleArrowClick = (multiplier) => {
    const { clientWidth, scrollLeft } = ref.current;

    const fullBooksOnShow = Math.floor(clientWidth / 184);

    ref.current.scrollTo({
      left: scrollLeft + multiplier * fullBooksOnShow * 184,
      behavior: "smooth",
    });
  };

  return (
    <div className="carousel">
      <button className="arrow left-arrow" onClick={() => handleArrowClick(-1)}>
        <FontAwesomeIcon icon={faCircleChevronLeft} className="icon" />
      </button>

      <div className="books-container" ref={ref}>
        {books?.map((book) => (
          <BookCard key={book.id} {...book} />
        ))}
      </div>

      <button className="arrow right-arrow" onClick={() => handleArrowClick(1)}>
        <FontAwesomeIcon icon={faCircleChevronRight} className="icon" />
      </button>
    </div>
  );
};

export default Carousel;
