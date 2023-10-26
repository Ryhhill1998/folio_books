import { create } from "zustand";
import { persist } from "zustand/middleware";

export const useProfileStore = create(
  persist(
    (set, get) => ({
      user: null,
      loginError: null,
      registerError: null,
      registerUser: async (displayName, email, password) => {
        try {
          const res = await fetch("http://localhost:8000/users");
          const users = await res.json();

          const foundUserWithEmail = users.find((user) => user.email === email);

          if (foundUserWithEmail) {
            throw new Error("A user already exists with that email.");
          }

          const newUser = {
            id: users.length + 1 + "",
            displayName,
            email,
            password,
          };

          fetch("http://localhost:8000/users", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(newUser),
          });

          set({
            user: newUser,
            signedIn: true,
            loginError: null,
            registerError: null,
          });
        } catch (error) {
          set({
            user: null,
            signedIn: false,
            loginError: null,
            registerError: error,
          });
        }
      },
      signInUser: async (email, password) => {
        try {
          const response = await fetch("http://localhost:8000/users");
          const users = await response.json();

          const foundUser = users.find(
            (user) => user.email === email && user.password === password
          );

          if (foundUser) {
            set({
              user: foundUser,
              signedIn: true,
              loginError: null,
              registerError: null,
            });
          } else {
            throw new Error(
              "Invalid email or password. Try registering instead."
            );
          }
        } catch (error) {
          set({
            user: null,
            signedIn: false,
            loginError: error,
            registerError: null,
          });
        }
      },
      addBookToBasket: (book) => {
        const user = get().user;
        const { basket } = user;
        const updatedBasket = [...basket, book];
        user.basket = updatedBasket;
        console.log({user});

        set({ user });
      },
      signOutUser: () => {
        set({
          user: null,
          signedIn: false,
          loginError: null,
          registerError: null,
        });
      },
      clearErrors: () => set({ loginError: null, registerError: null }),
    }),
    {
      name: "profile-data",
    }
  )
);
