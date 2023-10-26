import "./Nav.scss";
import logoImageSrc from "../../images/logo.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCartShopping,
  faMagnifyingGlass,
} from "@fortawesome/free-solid-svg-icons";
import { useSearchStore } from "../../globalStores/search";
import { useProfileStore } from "../../globalStores/profile";

const Nav = () => {
  const activateSearch = useSearchStore((store) => store.activateSearch);
  const deactivateSearch = useSearchStore((store) => store.deactivateSearch);
  const searchIsActive = useSearchStore((store) => store.active);
  const searchQuery = useSearchStore((store) => store.query);
  const setSearchQuery = useSearchStore((store) => store.setSearchQuery);
  const signedIn = useProfileStore((store) => store.signedIn);
  const signOut = useProfileStore((store) => store.signOutUser);

  return (
    <nav className="navigation container">
      {!searchIsActive && (
        <div className="image-container">
          <img src={logoImageSrc} alt="logo" />
        </div>
      )}

      {signedIn && (
        <div className="rhs-container">
          <div className={`search-container${searchIsActive ? " active" : ""}`}>
            <button>
              <FontAwesomeIcon icon={faMagnifyingGlass} className="icon" />
            </button>

            <input
              type="search"
              placeholder="search"
              value={searchQuery}
              onChange={({ target }) => setSearchQuery(target.value)}
              onFocus={activateSearch}
            />
          </div>

          {!searchIsActive && (
            <button>
              <FontAwesomeIcon icon={faCartShopping} className="icon" />
            </button>
          )}

          {!searchIsActive && <button onClick={signOut}>Logout</button>}

          {searchIsActive && <button onClick={deactivateSearch}>Cancel</button>}
        </div>
      )}
    </nav>
  );
};

export default Nav;
