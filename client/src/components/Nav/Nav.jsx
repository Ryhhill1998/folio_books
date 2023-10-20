import "./Nav.scss";
import logoImageSrc from "../../images/logo.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCartShopping,
  faMagnifyingGlass,
} from "@fortawesome/free-solid-svg-icons";
import { useSearchStore } from "../../globalStores/search";

const Nav = () => {
  const activateSearch = useSearchStore((store) => store.activateSearch);
  const deactivateSearch = useSearchStore((store) => store.deactivateSearch);
  const searchIsActive = useSearchStore((store) => store.active);

  return (
    <nav className="navigation container">
      {!searchIsActive && (
        <div className="image-container">
          <img src={logoImageSrc} alt="logo" />
        </div>
      )}

      <div className="rhs-container">
        <div className={`search-container${searchIsActive ? " active" : ""}`}>
          <button>
            <FontAwesomeIcon icon={faMagnifyingGlass} className="icon" />
          </button>

          <input type="search" placeholder="search" onFocus={activateSearch} />
        </div>

        {!searchIsActive && (
          <button>
            <FontAwesomeIcon icon={faCartShopping} className="icon" />
          </button>
        )}

        {searchIsActive && <button onClick={deactivateSearch}>Cancel</button>}
      </div>
    </nav>
  );
};

export default Nav;
