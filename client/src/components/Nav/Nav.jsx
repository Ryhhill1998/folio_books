import "./Nav.scss";
import logoImageSrc from "../../images/logo.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCartShopping,
  faMagnifyingGlass,
} from "@fortawesome/free-solid-svg-icons";
import { useEffect, useState } from "react";

const Nav = () => {
  const [isSearchMode, setIsSearchMode] = useState(false);

  useEffect(() => {
    console.log({ isSearchMode });
  }, [isSearchMode]);

  return (
    <nav className="navigation container">
      {!isSearchMode && (
        <div className="image-container">
          <img src={logoImageSrc} alt="logo" />
        </div>
      )}

      <div className="rhs-container">
        <div className={`search-container${isSearchMode ? " active" : ""}`}>
          <button>
            <FontAwesomeIcon icon={faMagnifyingGlass} className="icon" />
          </button>

          <input
            type="search"
            placeholder="search"
            onFocus={() => setIsSearchMode(true)}
          />
        </div>

        {!isSearchMode && (
          <button>
            <FontAwesomeIcon icon={faCartShopping} className="icon" />
          </button>
        )}

        {isSearchMode && (
          <button onClick={() => setIsSearchMode(false)}>Cancel</button>
        )}
      </div>
    </nav>
  );
};

export default Nav;
