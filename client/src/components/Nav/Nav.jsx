import "./Nav.scss";
import logoImageSrc from "../../images/logo.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCartShopping,
  faMagnifyingGlass,
} from "@fortawesome/free-solid-svg-icons";
import { useState } from "react";

const Nav = () => {
  const [isSearchMode, setIsSearchMode] = useState(false);

  return (
    <nav className="navigation container">
      <div className="image-container">
        <img src={logoImageSrc} alt="logo" />
      </div>

      <div className="rhs-container">
        <div className="search-container">
          <button>
            <FontAwesomeIcon icon={faMagnifyingGlass} className="icon" />
          </button>

          <input type="search" placeholder="search" />
        </div>

        <button>
          <FontAwesomeIcon icon={faCartShopping} className="icon" />
        </button>
      </div>
    </nav>
  );
};

export default Nav;
