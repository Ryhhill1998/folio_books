import "./BookCard.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faStar as fullStarIcon,
  faStarHalfStroke as halfStarIcon,
} from "@fortawesome/free-solid-svg-icons";
import { faStar as emptyStarIcon } from "@fortawesome/free-regular-svg-icons";
import { useState } from "react";
import { useProfileStore } from "../../globalStores/profile";

const BookCard = ({ id, title, imageSrc, author, stars, price }) => {
  const [hoveredId, setHoveredId] = useState(null);

  const addBookToBasket = useProfileStore((store) => store.addBookToBasket);

  const renderStars = () => {
    const numberOfStars = stars ?? 0;
    const fullStars = Math.floor(numberOfStars);
    const halfStars = Math.ceil(numberOfStars - fullStars);
    const emptyStars = Math.floor(5 - numberOfStars);

    return (
      <div className="stars-container">
        {new Array(fullStars).fill(0).map((_, index) => (
          <FontAwesomeIcon
            key={`full-star-${index}`}
            className="icon"
            icon={fullStarIcon}
          />
        ))}

        {new Array(halfStars).fill(0).map((_, index) => (
          <FontAwesomeIcon
            key={`half-star-${index}`}
            className="icon"
            icon={halfStarIcon}
          />
        ))}

        {new Array(emptyStars).fill(0).map((_, index) => (
          <FontAwesomeIcon
            key={`empty-star-${index}`}
            className="icon"
            icon={emptyStarIcon}
          />
        ))}
      </div>
    );
  };

  return (
    <div
      className="book-card"
      onMouseEnter={() => setHoveredId(id)}
      onMouseLeave={() => setHoveredId(null)}
    >
      <div className="image-container">
        <img src={imageSrc} alt="title" />
      </div>

      {id === hoveredId && <div className="overlay"></div>}

      {id === hoveredId && (
        <div className="details-container">
          <div className="details-top">
            <h2 className="title">{title}</h2>

            <p>by {author}</p>

            {renderStars()}

            <p>£{price}</p>
          </div>

          <button
            className="add-button"
            onClick={() =>
              addBookToBasket({ id, title, imageSrc, author, stars, price })
            }
          >
            Add to Basket
          </button>
        </div>
      )}
    </div>
  );
};

export default BookCard;
