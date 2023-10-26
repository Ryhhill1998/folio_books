import { useEffect } from "react";
import "./App.scss";
import Nav from "./components/Nav/Nav";
import { useProfileStore } from "./globalStores/profile";
import Auth from "./components/Auth/Auth";
import HomePage from "./components/HomePage/HomePage";

const App = () => {
  const signedIn = useProfileStore((store) => store.signedIn);
  const clearErrors = useProfileStore((store) => store.clearErrors);

  useEffect(() => clearErrors(), []);

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
