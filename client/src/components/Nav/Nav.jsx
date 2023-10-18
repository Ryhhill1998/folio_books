import "./Nav.scss";
import logoImageSrc from "../../images/logo.png";

const Nav = () => {
  return (
    <nav className="navigation">
      <div className="image-container">
        <img src={logoImageSrc} alt="logo" />
      </div>
    </nav>
  );
};

export default Nav;
