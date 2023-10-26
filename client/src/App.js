import { useEffect, useState } from "react";
import "./App.scss";
import Nav from "./components/Nav/Nav";
import Carousel from "./components/Carousel/Carousel";
import { useSearchStore } from "./globalStores/search";
import BookCard from "./components/BookCard/BookCard";
import LoginScreen from "./components/Auth/LoginScreen/LoginScreen";
import { useProfileStore } from "./globalStores/profile";
import RegisterScreen from "./components/Auth/RegisterScreen/RegisterScreen";
import Auth from "./components/Auth/Auth";
import HomePage from "./HomePage/HomePage";

const App = () => {
  const signedIn = useProfileStore((store) => store.signedIn);

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
        
        {signedIn && <HomePage/>}
      </main>
    </div>
  );
};

export default App;
