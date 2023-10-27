import { useEffect, useState } from "react";
import "./App.scss";
import Nav from "./components/Nav/Nav";
import { useProfileStore } from "./globalStores/profile";
import Auth from "./components/Auth/Auth";
import HomePage from "./components/HomePage/HomePage";

const App = () => {
  const [showCheckout, setShowCheckout] = useState(false);

  const signedIn = useProfileStore((store) => store.signedIn);
  const clearErrors = useProfileStore((store) => store.clearErrors);

  useEffect(() => clearErrors(), []);

  const params = {
    method: 'GET',
    headers: {
        'accept': 'application/json'
    }
  };

  const fetchData = () =>

  useEffect(() => {
    fetch("http://localhost:8080/get", params)
  }, []);

  return (
    <div className="App">
      {/* <p>{signedIn ? "Signed in" : "Signed out"}</p>
      {displayName && <p>As {displayName}</p>}
      <button onClick={signOut}>Sign out</button> */}

      <header>
        <Nav />
      </header>

      <main>
        {!signedIn && <Auth />}

        {signedIn && <HomePage />}
      </main>
    </div>
  );
};

export default App;
