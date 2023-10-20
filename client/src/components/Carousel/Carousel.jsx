import "./Carousel.scss";

import {
  faCircleChevronLeft,
  faCircleChevronRight,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import BookCard from "../BookCard/BookCard";
import { useEffect, useRef, useState } from "react";

const Carousel = ({ books }) => {
  const [showLeftArrow, setShowLeftArrow] = useState(false);
  const [showRightArrow, setShowRightArrow] = useState(true);
  const [scrollPosition, setScrollPosition] = useState(0);

  const ref = useRef(null);

  const handleArrowClick = (multiplier) => {
    const { scrollWidth, clientWidth, scrollLeft } = ref.current;

    const fullBooksOnShow = Math.floor(clientWidth / 184);
    let updatedScrollPosition = scrollLeft + multiplier * fullBooksOnShow * 184;

    if (updatedScrollPosition <= 0) {
      updatedScrollPosition = 0;
      setShowLeftArrow(false);
    } else if (updatedScrollPosition >= scrollWidth - clientWidth) {
      setShowRightArrow(false);
      updatedScrollPosition = scrollWidth - clientWidth;
    } else {
      setShowLeftArrow(true);
      setShowRightArrow(true);
    }

    ref.current.scrollTo({
      left: updatedScrollPosition,
      behavior: "smooth",
    });

    setScrollPosition(updatedScrollPosition);
  };

  return (
    <div className="carousel">
      {showLeftArrow && (
        <button
          className="arrow left-arrow"
          onClick={() => handleArrowClick(-1)}
        >
          <FontAwesomeIcon icon={faCircleChevronLeft} className="icon" />
        </button>
      )}

      <div className="books-container" ref={ref}>
        {books?.map((book) => (
          <BookCard key={book.id} {...book} />
        ))}
      </div>

      {showRightArrow && (
        <button
          className="arrow right-arrow"
          onClick={() => handleArrowClick(1)}
        >
          <FontAwesomeIcon icon={faCircleChevronRight} className="icon" />
        </button>
      )}
    </div>
  );
};

export default Carousel;
