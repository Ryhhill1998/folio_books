import "./BookCard.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCirclePlus,
  faPlus,
  faStar as fullStarIcon,
  faStarHalfStroke as halfStarIcon,
} from "@fortawesome/free-solid-svg-icons";
import { faStar as emptyStarIcon } from "@fortawesome/free-regular-svg-icons";

const BookCard = ({ title, image_src, author, stars, price }) => {
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
    <div className="book-card">
      <div className="image-container">
        <img src={image_src} alt="title" />
      </div>

      <div className="details-container">
        <div className="details-top">
          <h2 className="title">{title}</h2>

          <div className="stars-and-price-container">
            {renderStars()}

            <p>£{price}</p>
          </div>

          <p>{author}</p>
        </div>

        <button className="add-button">
          Add to Basket <FontAwesomeIcon icon={faPlus} />
        </button>
      </div>
    </div>
  );
};

export default BookCard;
