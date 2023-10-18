import "./BookCard.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faStar as fullStarIcon,
  faStarHalfStroke as halfStarIcon,
} from "@fortawesome/free-solid-svg-icons";
import { faStar as emptyStarIcon } from "@fortawesome/free-regular-svg-icons";

const BookCard = ({ title, image_src, author, stars, price }) => {
  const renderStars = () => {
    const fullStars = Math.floor(stars);
    const halfStars = Math.ceil(stars - fullStars);
    const emptyStars = Math.floor(5 - stars);

    return (
      <div className="stars-container">
        {new Array(fullStars).fill(0).map((_, index) => (
          <FontAwesomeIcon key={`full-star-${index}`} icon={fullStarIcon} />
        ))}

        {new Array(halfStars).fill(0).map((_, index) => (
          <FontAwesomeIcon key={`half-star-${index}`} icon={halfStarIcon} />
        ))}

        {new Array(emptyStars).fill(0).map((_, index) => (
          <FontAwesomeIcon key={`empty-star-${index}`} icon={halfStarIcon} />
        ))}
      </div>
    );
  };

  return (
    <div className="book-card">
      <div className="image-container">
        <img src={image_src} alt="title" />
      </div>

      <button className="add-button">Add to Basket</button>

      <h2 className="title">{title}</h2>

      <p>{author}</p>

      {stars > 0 && renderStars()}

      <p>£{price}</p>
    </div>
  );
};

export default BookCard;
